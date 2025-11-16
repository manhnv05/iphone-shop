package com.example.mobile_shop.repository.khach_hang;

import com.example.mobile_shop.entity.DiaChiKhachHang;
import com.example.mobile_shop.entity.KhachHang;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DiaChiKhachHangRepo extends JpaRepository<DiaChiKhachHang, Integer> {
    // Sử dụng cú pháp PostgreSQL: LENGTH và CAST AS INTEGER
    @Query("SELECT MAX(CAST(SUBSTRING(n.ma, 5, LENGTH(n.ma) - 4) AS INTEGER)) FROM DiaChiKhachHang n WHERE LENGTH(n.ma) >= 5")
    Integer findMaxMa();

    List<DiaChiKhachHang> findAllByIdKhachHangId(Integer idKhachHang);

    List<DiaChiKhachHang> findByIdKhachHang(@NotNull KhachHang idKhachHang);
}