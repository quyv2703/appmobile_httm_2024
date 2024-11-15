package com.ltq27.baotrimaylanh.model;

import java.util.List;

public class GoiDichVu {
    private Long goiDichVuId;
    private String tenGoi;
    private  String description;
    private List<LoaiMayLanhGia> loaiMayLanhGiaList;

    public Long getGoiDichVuId() {
        return goiDichVuId;
    }

    public void setGoiDichVuId(Long goiDichVuId) {
        this.goiDichVuId = goiDichVuId;
    }

    public String getTenGoi() {
        return tenGoi;
    }

    public void setTenGoi(String tenGoi) {
        this.tenGoi = tenGoi;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<LoaiMayLanhGia> getLoaiMayLanhGiaList() {
        return loaiMayLanhGiaList;
    }

    public void setLoaiMayLanhGiaList(List<LoaiMayLanhGia> loaiMayLanhGiaList) {
        this.loaiMayLanhGiaList = loaiMayLanhGiaList;
    }
}
