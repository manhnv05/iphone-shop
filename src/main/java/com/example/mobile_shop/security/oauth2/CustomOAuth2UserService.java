package com.example.mobile_shop.security.oauth2;

import com.example.mobile_shop.entity.QuyenHan;
import com.example.mobile_shop.entity.TaiKhoan;
import com.example.mobile_shop.repository.tai_khoan.TaiKhoanRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final TaiKhoanRepository taiKhoanRepository;
    private final PasswordEncoder passwordEncoder;

    public CustomOAuth2UserService(TaiKhoanRepository taiKhoanRepository,
                                   PasswordEncoder passwordEncoder) {
        this.taiKhoanRepository = taiKhoanRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        if (!"google".equalsIgnoreCase(registrationId)) {
            return oAuth2User; // only handle google specifically for now
        }

        Map<String, Object> attributes = new HashMap<>(oAuth2User.getAttributes());
        String email = (String) attributes.getOrDefault("email", "");
        String name = (String) attributes.getOrDefault("name", "");
        if (email == null || email.isEmpty()) {
            throw new OAuth2AuthenticationException("Google account does not provide an email");
        }

        // Find existing user by email or create a new CUSTOMER
        TaiKhoan user = taiKhoanRepository.findByEmail(email).orElse(null);
        if (user == null) {
            user = new TaiKhoan();
            user.setEmail(email);
            user.setDeleted(false);

            // Set role CUSTOMER by default (id=3 in current system convention)
            QuyenHan quyenHan = new QuyenHan();
            quyenHan.setId(3);
            user.setIdQuyenHan(quyenHan);

            // Generate unique ma (TK code)
            user.setMa(generateMaTaiKhoan());

            // Generate a username from email prefix; ensure uniqueness
            String baseUsername = email.substring(0, email.indexOf('@'));
            String uniqueUsername = ensureUniqueUsername(baseUsername);
            user.setTenDangNhap(uniqueUsername);

            // Phone is required and unique in schema; generate a synthetic one
            user.setSoDienThoai(generateUniquePhone());

            // Set a random password (not used for Google login)
            user.setMatKhau(passwordEncoder.encode(UUID.randomUUID().toString()));

            user = taiKhoanRepository.save(user);
        }

        // Build authorities from role
        String role = Optional.ofNullable(user.getIdQuyenHan())
                .map(QuyenHan::getMa)
                .orElse("CUSTOMER");
        role = role.replaceFirst("^ROLE_", "");

        List<SimpleGrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role));

        // Expose principal name as user's username (tenDangNhap) for consistency with existing code
        Map<String, Object> principalAttributes = new HashMap<>();
        principalAttributes.put("username", user.getTenDangNhap());
        principalAttributes.put("email", user.getEmail());

        return new DefaultOAuth2User(authorities, principalAttributes, "username");
    }

    private String generateMaTaiKhoan() {
        String maxMaTK = taiKhoanRepository.findMaxMaTK();
        if (maxMaTK != null) {
            int nextNumber = Integer.parseInt(maxMaTK.substring(2)) + 1;
            return String.format("TK%05d", nextNumber);
        }
        return "TK00001";
    }

    private String ensureUniqueUsername(String base) {
        String username = base;
        int suffix = 0;
        while (taiKhoanRepository.findBytenDangNhap(username).isPresent()) {
            suffix++;
            username = base + suffix;
        }
        return username;
    }

    private String generateUniquePhone() {
        // Generate a pseudo phone like 09 + 8 random digits; ensure uniqueness
        Random rnd = new Random();
        String phone;
        int attempts = 0;
        do {
            phone = "09" + String.format("%08d", rnd.nextInt(100_000_000));
            attempts++;
        } while (taiKhoanRepository.existsBySoDienThoai(phone) && attempts < 10_000);
        return phone;
    }
}
