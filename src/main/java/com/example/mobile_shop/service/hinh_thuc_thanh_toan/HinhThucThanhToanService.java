package com.example.mobile_shop.service.hinh_thuc_thanh_toan;

import com.example.mobile_shop.dto.hinh_thuc_thanh_toan.HinhThucThanhToanDTO;
import com.example.mobile_shop.entity.HinhThucThanhToan;
import com.example.mobile_shop.repository.hinh_thuc_thanh_toan_repo.HinhThucThanhToanRepository;

import java.util.List;

public class HinhThucThanhToanService {
    private final HinhThucThanhToanRepository hinhThucThanhToanRepository;

    public HinhThucThanhToanService(HinhThucThanhToanRepository hinhThucThanhToanRepository) {
        this.hinhThucThanhToanRepository = hinhThucThanhToanRepository;
    }

    public List<HinhThucThanhToanDTO> getAllData() {
        return hinhThucThanhToanRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList()
                ;
    }

    private HinhThucThanhToanDTO toDTO(HinhThucThanhToan httt) {
        return new HinhThucThanhToanDTO(
                httt.getId(),
                httt.getHoaDon().getId(),
                httt.getIdPhuongThucThanhToan(),
                httt.getTienChuyenKhoan(),
                httt.getTienMat(),
                httt.getMa(),
                httt.getDeleted()
        );
    }
}
