package com.ltq27.baotrimaylanh.apiresponse;

import com.ltq27.baotrimaylanh.model.Employee;
import com.ltq27.baotrimaylanh.model.ThongTinDatLich;
import com.ltq27.baotrimaylanh.retrofit2.TrangThaiPhanCong;

public class DanhSachDuocPhanCong {
    private Long id;
    private ThongTinDatLich thongTinDatLich;
    private Employee nhanVien;
    private String trangThai;

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

    public Employee getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(Employee nhanVien) {
        this.nhanVien = nhanVien;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
}
