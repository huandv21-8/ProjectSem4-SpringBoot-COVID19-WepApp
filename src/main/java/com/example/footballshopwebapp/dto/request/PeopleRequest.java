package com.example.footballshopwebapp.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PeopleRequest {

    @NotNull(message = "Name không được null")
    private String name;

    @NotNull(message = "age không được null")
    @Min(value = 1, message = "Tối thiểu là 1 tuổi")
    @Max(value = 150, message = "Tối đa là 5 tuổi")
    private int age;

    @NotNull(message = "Gender không được null")
    private boolean gender;

    @Size(min = 10, max = 10, message = "SĐT phải có 10 kí tự")
    @NotNull(message = "Phone không được null")
    private String phone;

    @NotNull(message = "Schedule không được null")
    private String schedule;

    @NotNull(message = "Status không được null")
    private String status;

    private boolean type;

    @NotNull(message = "Sourced không được null")
    private Long idSourced;

    @NotNull(message = "idProvince không được null")
    private Long idProvince;
    @NotNull(message = "idDistrict không được null")
    private Long idDistrict;
    @NotNull(message = "idCommune không được null")
    private Long idCommune;

}
