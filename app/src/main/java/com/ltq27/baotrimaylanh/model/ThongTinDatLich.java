package com.ltq27.baotrimaylanh.model;

import com.ltq27.baotrimaylanh.activity.employee.HoaDonResponse;

public class ThongTinDatLich {
    private Long id;
    private Customer customer;
    // đây là mã giá
    private  DonGia donGia;
    // hôm nay đặt
    private  String ngayDat;
    private  String ngayThucHien;
    private  String diaChiThucHien;
    private   int trangThaiDuyet;

    private  Employee managerId;
    private HoaDonResponse lichSuHoaDon;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public DonGia getDonGia() {
        return donGia;
    }

    public void setDonGia(DonGia donGia) {
        this.donGia = donGia;
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

    public Employee getManagerId() {
        return managerId;
    }

    public void setManagerId(Employee managerId) {
        this.managerId = managerId;
    }

    public HoaDonResponse getLichSuHoaDon() {
        return lichSuHoaDon;
    }

    public void setLichSuHoaDon(HoaDonResponse lichSuHoaDon) {
        this.lichSuHoaDon = lichSuHoaDon;
    }
}
