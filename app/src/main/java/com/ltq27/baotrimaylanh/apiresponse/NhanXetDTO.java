package com.ltq27.baotrimaylanh.apiresponse;

public class NhanXetDTO {
    private Long goiDichVuId;
    private Long customerId;
    private int start;
    private String comment;
    private String label;

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Long getGoiDichVuId() {
        return goiDichVuId;
    }

    public void setGoiDichVuId(Long goiDichVuId) {
        this.goiDichVuId = goiDichVuId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }



    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}

