package com.example.mobile_shop.repository.san_pham;

import com.example.mobile_shop.entity.SanPham.Ram;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RamRepository extends JpaRepository<Ram, Integer> {

    List<Ram> findByDeletedFalse();
    Page<Ram> findByDeletedFalse(Pageable pageable);
}
