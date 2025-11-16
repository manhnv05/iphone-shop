package com.example.mobile_shop.repository.hoa_don;

import com.example.mobile_shop.entity.LichSuHoaDon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LichSuHoaDonRepository extends JpaRepository<LichSuHoaDon, Integer> {

        @Query("SELECT MAX(id) FROM LichSuHoaDon")
        Integer findMaxId();
}
