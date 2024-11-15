package com.ltq27.baotrimaylanh.model;

import com.ltq27.baotrimaylanh.activity.employee.GoiDichVuResponse;

public class NhanXet {
    private Long id;
    private GoiDichVuResponse goiDichVu;
    private Customer customer;
    private int star;
    private String comment;

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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
