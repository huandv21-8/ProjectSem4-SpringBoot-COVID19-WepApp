package com.example.footballshopwebapp.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CountPeopleByStatusAboutProvince {
   private String nameProvince;
   private Long countPeopleByStatusCured;
   private Long countPeopleByStatusDied;
}
