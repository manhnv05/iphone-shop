package com.example.mobile_shop.repository.san_pham;

import com.example.mobile_shop.entity.SanPham.Gpu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GpuRepository extends JpaRepository<Gpu, Integer> {

    List<Gpu> findByDeletedFalse();

    Page<Gpu> findByDeletedFalse(Pageable pageable);
}
