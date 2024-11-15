package com.ltq27.baotrimaylanh.apiresponse;

import java.time.LocalDate;

public class DanhSachDonDatLich {
    private Long id;
    private String tenKhachHang;
    private String goiDichVu;
    private String ngayThucHien;
    private String diaChiThucHien;
    private String trangThaiDuyet;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public String getGoiDichVu() {
        return goiDichVu;
    }

    public void setGoiDichVu(String goiDichVu) {
        this.goiDichVu = goiDichVu;
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

    public String getTrangThaiDuyet() {
        return trangThaiDuyet;
    }

    public void setTrangThaiDuyet(String trangThaiDuyet) {
        this.trangThaiDuyet = trangThaiDuyet;
    }
}