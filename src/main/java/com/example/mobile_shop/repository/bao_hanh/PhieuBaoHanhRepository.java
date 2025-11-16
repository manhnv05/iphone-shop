package com.example.mobile_shop.repository.bao_hanh;

import com.example.mobile_shop.entity.PhieuBaoHanh;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhieuBaoHanhRepository extends JpaRepository<PhieuBaoHanh, Integer> {
}
