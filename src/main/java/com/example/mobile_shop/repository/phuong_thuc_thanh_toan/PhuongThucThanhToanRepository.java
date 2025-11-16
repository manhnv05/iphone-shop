package com.example.mobile_shop.repository.phuong_thuc_thanh_toan;

import com.example.mobile_shop.entity.PhuongThucThanhToan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PhuongThucThanhToanRepository extends JpaRepository<PhuongThucThanhToan, Integer> {
    @Query("SELECT p FROM PhuongThucThanhToan p WHERE p.kieuThanhToan = :kieuThanhToan AND p.deleted = false")
    Optional<PhuongThucThanhToan> findByKieuThanhToan(String kieuThanhToan);
}
