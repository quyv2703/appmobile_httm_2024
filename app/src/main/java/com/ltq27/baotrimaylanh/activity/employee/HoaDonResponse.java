package com.ltq27.baotrimaylanh.activity.employee;

public class HoaDonResponse {
  private   Long id;

    private String ngayThanhToan;
    private int trangThaiThanhToan;
    private Double tongTien;

    public HoaDonResponse(Long id, String ngayThanhToan, int trangThaiThanhToan, Double tongTien) {
        this.id = id;

        this.ngayThanhToan = ngayThanhToan;
        this.trangThaiThanhToan = trangThaiThanhToan;
        this.tongTien = tongTien;
    }

    public HoaDonResponse() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getNgayThanhToan() {
        return ngayThanhToan;
    }

    public void setNgayThanhToan(String ngayThanhToan) {
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
