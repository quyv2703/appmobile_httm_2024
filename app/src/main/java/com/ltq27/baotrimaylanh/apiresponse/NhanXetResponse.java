package com.ltq27.baotrimaylanh.apiresponse;

import com.ltq27.baotrimaylanh.activity.employee.GoiDichVuResponse;
import com.ltq27.baotrimaylanh.model.Customer;

public class NhanXetResponse {
    private String customerName;
    private String servicePackageName;
    private int start;
    private String comment;
    private String label;


    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getServicePakageName() {
        return servicePackageName;
    }

    public void setServicePakageName(String servicePakageName) {
        this.servicePackageName = servicePakageName;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
