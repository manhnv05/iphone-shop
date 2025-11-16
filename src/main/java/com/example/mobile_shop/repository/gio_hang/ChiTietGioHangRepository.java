package com.example.mobile_shop.repository.gio_hang;

import com.example.mobile_shop.entity.ChiTietGioHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChiTietGioHangRepository extends JpaRepository<ChiTietGioHang, Integer> {
}
