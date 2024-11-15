package com.ltq27.baotrimaylanh.apiresponse;

import com.ltq27.baotrimaylanh.model.Employee;

import java.util.List;

public class ListEmployeeResponse {
    private int code;
    private List<Employee> result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<Employee> getResult() {
        return result;
    }

    public void setResult(List<Employee> result) {
        this.result = result;
    }
}
