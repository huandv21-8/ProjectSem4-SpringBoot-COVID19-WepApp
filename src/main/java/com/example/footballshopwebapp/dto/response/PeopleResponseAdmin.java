package com.example.footballshopwebapp.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PeopleResponseAdmin {
    private Long idPeople;
    private String name;
    private int age;
    private String nameProvince;
    private String status;
    private boolean type;
}
