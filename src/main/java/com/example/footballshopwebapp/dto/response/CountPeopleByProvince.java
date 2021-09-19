package com.example.footballshopwebapp.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CountPeopleByProvince {
    private Long provinceId;
    private String provinceName;
    private Long countPeopleDied;
    private Long countPeopleSick;
    private Long countPeopleCured;

}
