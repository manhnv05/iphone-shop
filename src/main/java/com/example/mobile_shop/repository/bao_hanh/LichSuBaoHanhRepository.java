package com.example.mobile_shop.repository.bao_hanh;

import com.example.mobile_shop.entity.LichSuBaoHanh;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LichSuBaoHanhRepository extends JpaRepository<LichSuBaoHanh, Integer> {
}
