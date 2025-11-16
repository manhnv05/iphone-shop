package com.example.mobile_shop.repository.san_pham;

import com.example.mobile_shop.entity.SanPham.SanPham;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SanPhamRepository extends JpaRepository<SanPham, Integer> {
    Page<SanPham> findAll(Specification<SanPham> spec, Pageable pageable);
    Page<SanPham> findByDeletedFalse(Pageable pageable);
    SanPham findByIdAndDeletedFalse(Integer id);
    Optional<SanPham> findByTenSanPhamAndDeletedFalse(String tenSanPham);
}