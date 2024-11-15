package com.ltq27.baotrimaylanh.model;

import com.ltq27.baotrimaylanh.activity.employee.GoiDichVuResponse;

public class DonGia {
    private Long id;
    private GoiDichVuResponse goiDichVu;
    private  LoaiMayLanh loaiMayLanh;
    private Double price;

    public DonGia() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GoiDichVuResponse getGoiDichVu() {
        return goiDichVu;
    }

    public void setGoiDichVu(GoiDichVuResponse goiDichVu) {
        this.goiDichVu = goiDichVu;
    }

    public LoaiMayLanh getLoaiMayLanh() {
        return loaiMayLanh;
    }

    public void setLoaiMayLanh(LoaiMayLanh loaiMayLanh) {
        this.loaiMayLanh = loaiMayLanh;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
