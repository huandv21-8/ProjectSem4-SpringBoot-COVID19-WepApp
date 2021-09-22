package com.example.footballshopwebapp.dto;

import javax.validation.constraints.Size;

public class SmsPojo {
    @Size(min = 12, max = 13, message = "SĐT phải có 10 kí tự")
    private String phone;


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = "+84" + phone;
    }
}
