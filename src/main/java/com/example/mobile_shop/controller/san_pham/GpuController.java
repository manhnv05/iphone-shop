package com.example.mobile_shop.controller.san_pham;

import com.example.mobile_shop.dto.san_pham.CpuDTO;
import com.example.mobile_shop.dto.san_pham.GpuDTO;
import com.example.mobile_shop.dto.san_pham.HeDieuHanhDTO;
import com.example.mobile_shop.service.san_pham.CpuService;
import com.example.mobile_shop.service.san_pham.GpuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gpu")
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
public class GpuController {
    private final GpuService gpuService;

    @Autowired
    public GpuController(GpuService gpuService) {
        this.gpuService = gpuService;
    }

    @GetMapping
    public ResponseEntity<Page<GpuDTO>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        return ResponseEntity.ok(gpuService.getAllGpu(page, size));
    }

    @GetMapping("/all")
    public ResponseEntity<List<GpuDTO>> getAllThuocTinh() {
        return ResponseEntity.ok(gpuService.getAllGpuList());
    }
}