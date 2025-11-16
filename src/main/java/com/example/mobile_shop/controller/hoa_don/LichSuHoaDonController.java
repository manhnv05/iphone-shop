package com.example.mobile_shop.controller.hoa_don;

import com.example.mobile_shop.dto.hoa_don.LichSuHoaDonDTO;
import com.example.mobile_shop.entity.LichSuHoaDon;
import com.example.mobile_shop.service.hoa_don.LichSuHoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/lich-su-hoa-don")
public class LichSuHoaDonController {
    @Autowired
    private LichSuHoaDonService lichSuHoaDonService;
    @GetMapping("/home")
    public ResponseEntity<List<LichSuHoaDonDTO>> getDataTableLSHD(){
        return ResponseEntity.ok(lichSuHoaDonService.getAllDataLSHD());
    }
}
