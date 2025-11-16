package com.example.mobile_shop.service.san_pham;

import com.example.mobile_shop.dto.san_pham.CumCameraDTO;
import com.example.mobile_shop.dto.san_pham.GpuDTO;
import com.example.mobile_shop.entity.SanPham.CumCamera;
import com.example.mobile_shop.repository.san_pham.CumCameraRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CumCameraService {

    private final CumCameraRepository cumCameraRepository;

    public CumCameraService(CumCameraRepository cumCameraRepository) {
        this.cumCameraRepository = cumCameraRepository;
    }

    public Page<CumCameraDTO> getAllCumCamera(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return cumCameraRepository.findByDeletedFalse(pageable).map(this::toDTO);
    }

    public List<CumCameraDTO> getAllCumCameraList() {
        return cumCameraRepository.findByDeletedFalse().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    private CumCameraDTO toDTO(CumCamera entity) {
        CumCameraDTO dto = new CumCameraDTO();
        dto.setId(entity.getId());
        dto.setMa(entity.getMa());
        dto.setCameraTruoc(entity.getCameraTruoc());
        dto.setCameraSau(entity.getCameraSau());
        dto.setDeleted(entity.getDeleted());
        return dto;
    }
}