package com.example.footballshopwebapp.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PeopleResponseAdmin {
    private Long idPeople;
    private String name;
    private String birthDay;
    private String nameProvince;
    private String status;
    private boolean type;
    private Long idStatusByTime;
}
