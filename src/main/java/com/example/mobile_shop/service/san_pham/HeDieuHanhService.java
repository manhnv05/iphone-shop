package com.example.mobile_shop.service.san_pham;

import com.example.mobile_shop.dto.san_pham.HeDieuHanhDTO;
import com.example.mobile_shop.dto.san_pham.HoTroCongNgheSacDTO;
import com.example.mobile_shop.entity.SanPham.HeDieuHanh;
import com.example.mobile_shop.repository.san_pham.HeDieuHanhRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HeDieuHanhService {

    private final HeDieuHanhRepository heDieuHanhRepository;

    public HeDieuHanhService(HeDieuHanhRepository heDieuHanhRepository) {
        this.heDieuHanhRepository = heDieuHanhRepository;
    }

    public Page<HeDieuHanhDTO> getAllHeDieuHanh(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return heDieuHanhRepository.findByDeletedFalse(pageable).map(this::toDTO);
    }

    public List<HeDieuHanhDTO> getAllHeDieuHanhList() {
        return heDieuHanhRepository.findByDeletedFalse().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    private HeDieuHanhDTO toDTO(HeDieuHanh entity) {
        return new HeDieuHanhDTO(entity.getId(), entity.getMa(), entity.getHeDieuHanh(), entity.getPhienBan(), entity.getDeleted());
    }
}
