package com.example.mobile_shop.repository.san_pham;

import com.example.mobile_shop.entity.HoaDon;
import com.example.mobile_shop.entity.SanPham.ImelDaBan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImelDaBanRepository extends JpaRepository<ImelDaBan, Integer> {
    Page<ImelDaBan> findByDeletedFalse(Pageable pageable);

    @Query("SELECT MAX(i.ma) FROM ImelDaBan i WHERE i.ma LIKE 'IMDB%'")
    String findMaxMa();
}