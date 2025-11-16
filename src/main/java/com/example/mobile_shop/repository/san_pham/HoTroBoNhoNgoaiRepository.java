package com.example.mobile_shop.repository.san_pham;

import com.example.mobile_shop.entity.SanPham.HoTroBoNhoNgoai;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HoTroBoNhoNgoaiRepository extends JpaRepository<HoTroBoNhoNgoai, Integer> {
}
