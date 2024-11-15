package com.ltq27.baotrimaylanh.model;

public class DatLichDto {
    private Long customerId;
    private Long donGiaId;
    private String ngayThucHien;
    private String diaChiThucHien;

    public DatLichDto() {
    }

    public DatLichDto(Long customerId, Long donGiaId, String ngayThucHien, String diaChiThucHien) {
        this.customerId = customerId;
        this.donGiaId = donGiaId;
        this.ngayThucHien = ngayThucHien;
        this.diaChiThucHien = diaChiThucHien;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getDonGiaId() {
        return donGiaId;
    }

    public void setDonGiaId(Long donGiaId) {
        this.donGiaId = donGiaId;
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
}
