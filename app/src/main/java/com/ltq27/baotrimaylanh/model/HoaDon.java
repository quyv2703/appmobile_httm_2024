package com.ltq27.baotrimaylanh.model;

import java.time.LocalDate;

public class HoaDon {
    private Long id;
    private ThongTinDatLich thongTinDatLich;

    private LocalDate ngayThanhToan;
    private int trangThaiThanhToan;

    private Double tongTien;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ThongTinDatLich getThongTinDatLich() {
        return thongTinDatLich;
    }

    public void setThongTinDatLich(ThongTinDatLich thongTinDatLich) {
        this.thongTinDatLich = thongTinDatLich;
    }

    public LocalDate getNgayThanhToan() {
        return ngayThanhToan;
    }

    public void setNgayThanhToan(LocalDate ngayThanhToan) {
        this.ngayThanhToan = ngayThanhToan;
    }

    public int getTrangThaiThanhToan() {
        return trangThaiThanhToan;
    }

    public void setTrangThaiThanhToan(int trangThaiThanhToan) {
        this.trangThaiThanhToan = trangThaiThanhToan;
    }

    public Double getTongTien() {
        return tongTien;
    }

    public void setTongTien(Double tongTien) {
        this.tongTien = tongTien;
    }
}
