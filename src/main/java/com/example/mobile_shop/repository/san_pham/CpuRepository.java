package com.example.mobile_shop.repository.san_pham;

import com.example.mobile_shop.entity.SanPham.Cpu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CpuRepository extends JpaRepository<Cpu, Integer> {

    List<Cpu> findByDeletedFalse();

    Page<Cpu> findByDeletedFalse(Pageable pageable);
}
