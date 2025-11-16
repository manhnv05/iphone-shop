package com.example.mobile_shop.repository.san_pham;

import com.example.mobile_shop.entity.SanPham.HeDieuHanh;
import com.example.mobile_shop.entity.SanPham.Pin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PinRepository extends JpaRepository<Pin, Integer> {

    List<Pin> findByDeletedFalse();

    Page<Pin> findByDeletedFalse(Pageable pageable);
}
