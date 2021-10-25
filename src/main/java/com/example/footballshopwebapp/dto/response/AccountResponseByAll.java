package com.example.footballshopwebapp.dto.response;

import lombok.Data;

@Data
public class AccountResponseByAll {

    private Long accountId;
    private String name;

    private String birthDay;

    private boolean gender;

    private String phone;

    private String  cmt;

    private String provinceName;
    private String districtName;
    private String communeName;

    private String address;

    private int ratio;
    private String dateDeclare;
}
