package com.example.mobile_shop.repository.hinh_thuc_thanh_toan_repo;

import com.example.mobile_shop.entity.HinhThucThanhToan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HinhThucThanhToanRepository extends JpaRepository<HinhThucThanhToan, Integer> {
}
