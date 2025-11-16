package com.example.mobile_shop.service.hoa_don;

import com.example.mobile_shop.dto.hoa_don.LichSuHoaDonDTO;
import com.example.mobile_shop.entity.LichSuHoaDon;
import com.example.mobile_shop.repository.hoa_don.LichSuHoaDonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LichSuHoaDonService {
    private final LichSuHoaDonRepository lichSuHoaDonRepository;

    public LichSuHoaDonService(LichSuHoaDonRepository lichSuHoaDonRepository) {
        this.lichSuHoaDonRepository = lichSuHoaDonRepository;
    }

    public List<LichSuHoaDonDTO> getAllDataLSHD() {
        return lichSuHoaDonRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList()
                ;
    }

    private LichSuHoaDonDTO toDTO(LichSuHoaDon lichSuHoaDon) {
        return new LichSuHoaDonDTO(
                lichSuHoaDon.getId(),
                lichSuHoaDon.getHoaDon().getId(),
                lichSuHoaDon.getIdNhanVien(),
                lichSuHoaDon.getMa(),
                lichSuHoaDon.getHanhDong(),
                lichSuHoaDon.getThoiGian()
        );
    }
}
