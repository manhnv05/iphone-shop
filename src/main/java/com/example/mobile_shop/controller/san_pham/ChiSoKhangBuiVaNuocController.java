package com.example.mobile_shop.controller.san_pham;

import com.example.mobile_shop.dto.san_pham.ChiSoKhangBuiVaNuocDTO;
import com.example.mobile_shop.dto.san_pham.CongNgheMangDTO;
import com.example.mobile_shop.dto.san_pham.GpuDTO;
import com.example.mobile_shop.service.san_pham.ChiSoKhangBuiVaNuocService;
import com.example.mobile_shop.service.san_pham.GpuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chi-so-khang-bui-va-nuoc")
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
public class ChiSoKhangBuiVaNuocController {
    private final ChiSoKhangBuiVaNuocService service;

    @Autowired
    public ChiSoKhangBuiVaNuocController(ChiSoKhangBuiVaNuocService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Page<ChiSoKhangBuiVaNuocDTO>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        return ResponseEntity.ok(service.getAllChiSoKhangBuiVaNuoc(page, size));
    }

    @GetMapping("/all")
    public ResponseEntity<List<ChiSoKhangBuiVaNuocDTO>> getAllThuocTinh() {
        return ResponseEntity.ok(service.getAllChiSoKhangBuiVaNuocList());
    }
}