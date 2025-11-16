package com.example.mobile_shop.controller.san_pham;

import com.example.mobile_shop.dto.san_pham.CpuDTO;
import com.example.mobile_shop.dto.san_pham.CumCameraDTO;
import com.example.mobile_shop.dto.san_pham.PinDTO;
import com.example.mobile_shop.service.san_pham.CpuService;
import com.example.mobile_shop.service.san_pham.PinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cpu")
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
public class CpuController {
    private final CpuService cpuService;

    @Autowired
    public CpuController(CpuService cpuService) {
        this.cpuService = cpuService;
    }

    @GetMapping
    public ResponseEntity<Page<CpuDTO>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        return ResponseEntity.ok(cpuService.getAllCpu(page, size));
    }

    @GetMapping("/all")
    public ResponseEntity<List<CpuDTO>> getAllThuocTinh() {
        return ResponseEntity.ok(cpuService.getAllCpuList());
    }
}