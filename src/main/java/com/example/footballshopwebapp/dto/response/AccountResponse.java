package com.example.footballshopwebapp.dto.response;
import lombok.Data;

import java.util.Date;
@Data
public class AccountResponse {
    private String name;

    private Date birthDay;

    private boolean gender;

    private String phone;

    private String  cmt;

    private Long communeId;

    private String address;
}
