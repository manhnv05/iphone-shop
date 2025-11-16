package com.example.mobile_shop.controller.san_pham;

import com.example.mobile_shop.dto.san_pham.HeDieuHanhDTO;
import com.example.mobile_shop.dto.san_pham.HoTroCongNgheSacDTO;
import com.example.mobile_shop.service.san_pham.HeDieuHanhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/he-dieu-hanh")
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
public class HeDieuHanhController {
    private final HeDieuHanhService heDieuHanhService;

    @Autowired
    public HeDieuHanhController(HeDieuHanhService heDieuHanhService) {
        this.heDieuHanhService = heDieuHanhService;
    }

    @GetMapping
    public ResponseEntity<Page<HeDieuHanhDTO>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        return ResponseEntity.ok(heDieuHanhService.getAllHeDieuHanh(page, size));
    }

    @GetMapping("/all")
    public ResponseEntity<List<HeDieuHanhDTO>> getAllThuocTinh() {
        return ResponseEntity.ok(heDieuHanhService.getAllHeDieuHanhList());
    }
}