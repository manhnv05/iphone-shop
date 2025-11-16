package com.example.mobile_shop.service.san_pham;

import com.example.mobile_shop.dto.san_pham.PinDTO;
import com.example.mobile_shop.dto.san_pham.RamDTO;
import com.example.mobile_shop.entity.SanPham.HeDieuHanh;
import com.example.mobile_shop.entity.SanPham.Pin;
import com.example.mobile_shop.repository.san_pham.PinRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PinService {

    private final PinRepository pinRepository;

    public PinService(PinRepository pinRepository) {
        this.pinRepository = pinRepository;
    }

    public Page<PinDTO> getAllPin(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return pinRepository.findByDeletedFalse(pageable).map(this::toDTO);
    }

    public List<PinDTO> getAllPinList() {
        return pinRepository.findByDeletedFalse().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    private PinDTO toDTO(Pin entity) {
        return new PinDTO(entity.getId(), entity.getMa(), entity.getLoaiPin(), entity.getDungLuongPin(), entity.getDeleted());
    }
}
