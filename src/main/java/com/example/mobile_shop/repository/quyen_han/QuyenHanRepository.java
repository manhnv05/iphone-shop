package com.example.mobile_shop.repository.quyen_han;

import com.example.mobile_shop.entity.QuyenHan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuyenHanRepository extends JpaRepository<QuyenHan, Integer> {
    QuyenHan findByCapQuyenHan(Integer capQuyenHan);
}
