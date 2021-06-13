package com.example.footballshopwebapp.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PeopleResponseAdmin {
    private Long id;
    private String name;
    private int age;
    private String nameProvince;
    private String status;
    private boolean type;
}
