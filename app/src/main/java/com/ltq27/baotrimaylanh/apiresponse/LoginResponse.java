package com.ltq27.baotrimaylanh.apiresponse;

import com.ltq27.baotrimaylanh.model.Role;

public class LoginResponse {

    private String message;
    private Role role;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
