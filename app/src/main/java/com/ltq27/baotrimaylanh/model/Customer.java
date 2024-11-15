package com.ltq27.baotrimaylanh.model;

public class Customer {

   private Long id;

    private Account account;
    private String name;
    private String phoneNumber;
    private String address;
    private String birthYear;
    private String gender;

    public Customer() {
    }

    public Customer(Long id, Account account, String name, String phoneNumber, String address, String birthYear, String gender) {
        this.id = id;
        this.account = account;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.birthYear = birthYear;
        this.gender = gender;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
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
