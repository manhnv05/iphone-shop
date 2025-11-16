package com.example.mobile_shop.repository.hoa_don;

import com.example.mobile_shop.entity.HoaDonChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HoaDonChiTietRepository extends JpaRepository<HoaDonChiTiet, Integer> {
    List<HoaDonChiTiet> findByIdChiTietSanPhamIdAndDeletedFalse(Integer chiTietSanPhamId);

}
