package com.example.footballshopwebapp.dto.response;

import lombok.Data;

@Data
public class DeclareResponse {
    private Long questionId;
    private String name;

    private String updateAt;

    private String phone;

    private String provinceName;
    private String districtName;
    private String communeName;

}
