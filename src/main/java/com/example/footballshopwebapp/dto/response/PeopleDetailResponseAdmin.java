package com.example.footballshopwebapp.dto.response;

import com.example.footballshopwebapp.entity.Commune;
import com.example.footballshopwebapp.entity.District;
import com.example.footballshopwebapp.entity.Province;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PeopleDetailResponseAdmin {
    private Long idPeople;
    private String name;
    private int age;
    private String phone;
    private boolean gender;
    private String schedule;
    private String province;
    private String district;
    private String commune;
    private Date time;
    private String status;
    private boolean type;
    private Long idSource;
    private String nameSource;
}
