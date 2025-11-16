package com.example.mobile_shop.controller.san_pham;

import com.example.mobile_shop.dto.san_pham.MauSacDTO;
import com.example.mobile_shop.dto.san_pham.SimDTO;
import com.example.mobile_shop.dto.san_pham.ThietKeDTO;
import com.example.mobile_shop.service.san_pham.SimService;
import com.example.mobile_shop.service.san_pham.ThietKeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/thiet-ke")
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
public class ThietKeController {
    private final ThietKeService thietKeService;

    @Autowired
    public ThietKeController(ThietKeService thietKeService) {
        this.thietKeService = thietKeService;
    }

    @GetMapping
    public ResponseEntity<Page<ThietKeDTO>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        return ResponseEntity.ok(thietKeService.getAllThietKe(page, size));
    }

    @GetMapping("/all")
    public ResponseEntity<List<ThietKeDTO>> getAllThuocTinh() {
        return ResponseEntity.ok(thietKeService.getAllThietKeList());
    }
}