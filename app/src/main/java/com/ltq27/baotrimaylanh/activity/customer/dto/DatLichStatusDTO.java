package com.ltq27.baotrimaylanh.activity.customer.dto;

import java.time.LocalDate;

public class DatLichStatusDTO {
    private Long id;
    private String ngayDat;
    private String ngayThucHien;
    private String diaChiThucHien;
    private int trangThaiDuyet;
    private String tenGoiDichVu;
    private String tenLoaiMayLanh;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNgayDat() {
        return ngayDat;
    }

    public void setNgayDat(String ngayDat) {
        this.ngayDat = ngayDat;
    }

    public String getNgayThucHien() {
        return ngayThucHien;
    }

    public void setNgayThucHien(String ngayThucHien) {
        this.ngayThucHien = ngayThucHien;
    }

    public String getDiaChiThucHien() {
        return diaChiThucHien;
    }

    public void setDiaChiThucHien(String diaChiThucHien) {
        this.diaChiThucHien = diaChiThucHien;
    }

    public int getTrangThaiDuyet() {
        return trangThaiDuyet;
    }

    public void setTrangThaiDuyet(int trangThaiDuyet) {
        this.trangThaiDuyet = trangThaiDuyet;
    }

    public String getTenGoiDichVu() {
        return tenGoiDichVu;
    }

    public void setTenGoiDichVu(String tenGoiDichVu) {
        this.tenGoiDichVu = tenGoiDichVu;
    }

    public String getTenLoaiMayLanh() {
        return tenLoaiMayLanh;
    }

    public void setTenLoaiMayLanh(String tenLoaiMayLanh) {
        this.tenLoaiMayLanh = tenLoaiMayLanh;
    }
}
