package com.ltq27.baotrimaylanh.model;

public class LoaiMayLanhGia {
    private Long loaiMayLanhId;
    private String tenLoaiMayLanh;
    private Double gia;

    public Long getLoaiMayLanhId() {
        return loaiMayLanhId;
    }

    public void setLoaiMayLanhId(Long loaiMayLanhId) {
        this.loaiMayLanhId = loaiMayLanhId;
    }

    public String getTenLoaiMayLanh() {
        return tenLoaiMayLanh;
    }

    public void setTenLoaiMayLanh(String tenLoaiMayLanh) {
        this.tenLoaiMayLanh = tenLoaiMayLanh;
    }

    public Double getGia() {
        return gia;
    }

    public void setGia(Double gia) {
        this.gia = gia;
    }
}
