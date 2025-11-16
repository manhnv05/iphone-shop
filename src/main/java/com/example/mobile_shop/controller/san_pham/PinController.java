package com.example.mobile_shop.controller.san_pham;

import com.example.mobile_shop.dto.san_pham.HeDieuHanhDTO;
import com.example.mobile_shop.dto.san_pham.PinDTO;
import com.example.mobile_shop.dto.san_pham.RamDTO;
import com.example.mobile_shop.service.san_pham.HeDieuHanhService;
import com.example.mobile_shop.service.san_pham.PinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pin")
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
public class PinController {
    private final PinService pinService;

    @Autowired
    public PinController(PinService pinService) {
        this.pinService = pinService;
    }

    @GetMapping
    public ResponseEntity<Page<PinDTO>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        return ResponseEntity.ok(pinService.getAllPin(page, size));
    }

    @GetMapping("/all")
    public ResponseEntity<List<PinDTO>> getAllThuocTinh() {
        return ResponseEntity.ok(pinService.getAllPinList());
    }
}