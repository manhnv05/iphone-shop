package com.example.mobile_shop.service.hoa_don;

import com.example.mobile_shop.dto.hoa_don.HoaDonChiTietDTO;
import com.example.mobile_shop.entity.HoaDonChiTiet;
import com.example.mobile_shop.repository.hoa_don.HoaDonChiTietRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HoaDonChiTietService {
    private final HoaDonChiTietRepository hoaDonChiTietRepository;

    @Autowired
    public HoaDonChiTietService(HoaDonChiTietRepository hoaDonChiTietRepository) {
        this.hoaDonChiTietRepository = hoaDonChiTietRepository;
    }

    public List<HoaDonChiTietDTO> getAllDataHDCT() {

        return hoaDonChiTietRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList()
                ;
    }

    private HoaDonChiTietDTO toDTO(HoaDonChiTiet hoaDonChiTiet) {
        return new HoaDonChiTietDTO(
                hoaDonChiTiet.getId(),
                hoaDonChiTiet.getHoaDon().getId(),
                hoaDonChiTiet.getIdChiTietSanPham(),
                hoaDonChiTiet.getIdImelDaBan(),
                hoaDonChiTiet.getMa(),
                hoaDonChiTiet.getGia(),
                hoaDonChiTiet.getTrangThai(),
                hoaDonChiTiet.getGhiChu(),
                hoaDonChiTiet.getId(),
                hoaDonChiTiet.getMa(),
                hoaDonChiTiet.getMa(),
                hoaDonChiTiet.getMa(),
                hoaDonChiTiet.getMa()
        );
    }
}
