package com.example.mobile_shop.controller.tai_khoan;

import com.example.mobile_shop.dto.tai_khoan.TaiKhoanDTO;
import com.example.mobile_shop.entity.TaiKhoan;
import com.example.mobile_shop.service.tai_khoan.TaiKhoanServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Map;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:8080"})
@RequestMapping("tai-khoan")
public class TaiKhoanController {
    private final TaiKhoanServices taiKhoanServices;
    // We are using a custom authentication logic now, so AuthenticationManager is not directly needed here.

    public TaiKhoanController(TaiKhoanServices taiKhoanServices) {
        this.taiKhoanServices = taiKhoanServices;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody TaiKhoanDTO loginRequest) {
        TaiKhoan authenticatedUser = taiKhoanServices.authenticateAndUpgradePassword(
                loginRequest.getTenDangNhap(),
                loginRequest.getMatKhau()
        );

        if (authenticatedUser != null) {
            // Manually create an Authentication object for Spring Security context
            String role = "CUSTOMER"; // Default
            if (authenticatedUser.getIdQuyenHan() != null && authenticatedUser.getIdQuyenHan().getMa() != null) {
                role = authenticatedUser.getIdQuyenHan().getMa();
            }
            // Normalize role to avoid double ROLE_ prefix
            role = role.replaceFirst("^ROLE_", "");

            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    authenticatedUser.getTenDangNhap(),
                    null, // Do not store password in the Authentication object returned to FE
                    Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role))
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Build a safe response payload (no password)
            Map<String, Object> body = new java.util.HashMap<>();
            body.put("id", authenticatedUser.getId());
            body.put("ma", authenticatedUser.getMa());
            body.put("tenDangNhap", authenticatedUser.getTenDangNhap());
            body.put("email", authenticatedUser.getEmail());
            // Minimal role object to satisfy FE `idQuyenHan.ma`
            Map<String, Object> roleObj = new java.util.HashMap<>();
            roleObj.put("ma", role);
            body.put("idQuyenHan", roleObj);

            return ResponseEntity.ok(body);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    @PostMapping("/add")
    public TaiKhoan addTaiKhoan(@RequestBody TaiKhoan taiKhoan){
        return taiKhoanServices.add(taiKhoan);
    }

    @PostMapping("/requestOtp")
    public ResponseEntity<?> requestOtp(@RequestBody TaiKhoanDTO taiKhoanDTO) {
        try {
            String message = taiKhoanServices.requestOTP(taiKhoanDTO);
            return new ResponseEntity<>(message, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/requestOtpMk")
    public ResponseEntity<?> requestOtpMk(@RequestBody TaiKhoanDTO taiKhoanDTO) {
        try {
            String message = taiKhoanServices.requestOTPMk(taiKhoanDTO);
            return new ResponseEntity<>(message, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/verifyOtp")
    public ResponseEntity<String> verifyOtp(@RequestBody TaiKhoanDTO taiKhoanDTO, @RequestParam("otp") String otp) {
        try {
            String response = taiKhoanServices.verifyOTP(taiKhoanDTO, otp);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/addTk")
    public ResponseEntity<?> addTk(@RequestBody Map<String, String> request) {
        try {
            TaiKhoanDTO taiKhoanDTO = new TaiKhoanDTO();
            taiKhoanDTO.setTenDangNhap(request.get("tenDangNhap"));
            taiKhoanDTO.setEmail(request.get("email"));
            taiKhoanDTO.setMatKhau(request.get("matKhau"));
            String otp = request.get("otp");

            TaiKhoan tk = taiKhoanServices.addTK(taiKhoanDTO, otp);
            return new ResponseEntity<>(tk, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Lỗi khi thêm tài khoản: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/update-tk")
    public ResponseEntity<?> updateTaiKhoan(@RequestBody TaiKhoanDTO taiKhoanDTO) {
        try {
            TaiKhoan updatedTaiKhoan = taiKhoanServices.UpdateTK(taiKhoanDTO.getEmail(), taiKhoanDTO);
            return ResponseEntity.ok("Cập nhật tài khoản thành công!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/me")
    public ResponseEntity<?> me(Principal principal) {
        if (principal == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Chưa đăng nhập");
        }
        String username = principal.getName();
        TaiKhoan user = taiKhoanServices.findByTenDangNhap(username);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Không tìm thấy người dùng");
        }
        String role = "CUSTOMER";
        if (user.getIdQuyenHan() != null && user.getIdQuyenHan().getMa() != null) {
            role = user.getIdQuyenHan().getMa().replaceFirst("^ROLE_", "");
        }
        Map<String, Object> body = new java.util.HashMap<>();
        body.put("id", user.getId());
        body.put("ma", user.getMa());
        body.put("tenDangNhap", user.getTenDangNhap());
        body.put("email", user.getEmail());
        Map<String, Object> roleObj = new java.util.HashMap<>();
        roleObj.put("ma", role);
        body.put("idQuyenHan", roleObj);
        return ResponseEntity.ok(body);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        try {
            request.logout();
        } catch (Exception ignored) {}
        try {
            if (request.getSession(false) != null) {
                request.getSession(false).invalidate();
            }
        } catch (Exception ignored) {}
        SecurityContextHolder.clearContext();
        return ResponseEntity.ok("Đã đăng xuất");
    }
}