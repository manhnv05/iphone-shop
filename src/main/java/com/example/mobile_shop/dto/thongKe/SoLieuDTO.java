package com.example.mobile_shop.dto.thongKe;

import java.math.BigDecimal;

public class SoLieuDTO {
    private BigDecimal doanhThu;
    private Long sanPhamDaBan;
    private Integer tongSoDonHang;

    public SoLieuDTO() {
    }

    public SoLieuDTO(BigDecimal doanhThu, Long sanPhamDaBan, Integer tongSoDonHang) {
        this.doanhThu = doanhThu;
        this.sanPhamDaBan = sanPhamDaBan;
        this.tongSoDonHang = tongSoDonHang;
    }

    public BigDecimal getDoanhThu() {
        return doanhThu;
    }

    public void setDoanhThu(BigDecimal doanhThu) {
        this.doanhThu = doanhThu;
    }

    public Long getSanPhamDaBan() {
        return sanPhamDaBan;
    }

    public void setSanPhamDaBan(Long sanPhamDaBan) {
        this.sanPhamDaBan = sanPhamDaBan;
    }

    public Integer getTongSoDonHang() {
        return tongSoDonHang;
    }

    public void setTongSoDonHang(Integer tongSoDonHang) {
        this.tongSoDonHang = tongSoDonHang;
    }
}
