package com.example.mobile_shop.repository.thongKe;

import com.example.mobile_shop.entity.SanPham.ChiTietSanPham;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CTSPForThongKe extends JpaRepository<ChiTietSanPham,Integer> {
}
