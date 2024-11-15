package com.ltq27.baotrimaylanh.activity.customer.dto;

public class DangKiDto {
    private String username;
    private String password;
    private String name;
    private String phoneNumber;
    private String address;
    private String birthYear;
    private String gender;

    public DangKiDto() {
    }

    public DangKiDto(String username, String password, String name, String phoneNumber, String address, String birthYear, String gender) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.birthYear = birthYear;
        this.gender = gender;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(String birthYear) {
        this.birthYear = birthYear;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
