package com.ltq27.baotrimaylanh.model;

public class LoaiMayLanh {
    private Long id;
    private String name;

    @Override
    public String toString() {
        return this.getName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
