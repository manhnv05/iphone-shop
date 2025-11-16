package com.example.mobile_shop.dto.phieu_bao_hanh;

import com.example.mobile_shop.entity.PhieuBaoHanh;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LichSuBaoHanhDTO {
    private PhieuBaoHanh idPhieuBaoHanh;
    private String ma;
    private String moTaLoi;
    private String phuongThucSuaChua;
    private Double chiPhi;
    private String trangThai;
    private String ghiChu;

    public LichSuBaoHanhDTO(PhieuBaoHanh idPhieuBaoHanh, String ma, String moTaLoi, String phuongThucSuaChua, Double chiPhi, String trangThai, String ghiChu) {
        this.idPhieuBaoHanh = idPhieuBaoHanh;
        this.ma = ma;
        this.moTaLoi = moTaLoi;
        this.phuongThucSuaChua = phuongThucSuaChua;
        this.chiPhi = chiPhi;
        this.trangThai = trangThai;
        this.ghiChu = ghiChu;
    }
}
