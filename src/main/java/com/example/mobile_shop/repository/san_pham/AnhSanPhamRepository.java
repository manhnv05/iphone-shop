package com.example.mobile_shop.repository.san_pham;

import com.example.mobile_shop.entity.SanPham.AnhSanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnhSanPhamRepository extends JpaRepository<AnhSanPham, Integer> {
    @Query("SELECT a FROM AnhSanPham a ORDER BY a.id DESC")
    List<AnhSanPham> findTopNByOrderByIdDesc(int limit);

    Optional<AnhSanPham> findByHashAndProductGroupKey(String hash, String productGroupKey);

    Optional<AnhSanPham> findByDuongDan(String duongDan); // Add this to check by URL
}