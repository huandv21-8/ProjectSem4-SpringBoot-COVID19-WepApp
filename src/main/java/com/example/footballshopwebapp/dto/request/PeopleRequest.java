package com.example.footballshopwebapp.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PeopleRequest {

    @NotNull(message = "Name không được null")
    private String name;

    @Past
    private Date birthDay;

    private String cmt;

    @NotNull(message = "Gender không được null")
    private boolean gender;

    @Size(min = 10, max = 10, message = "SĐT phải có 10 kí tự")
    @NotNull(message = "Phone không được null")
    private String phone;
    private String schedule;

    @NotNull(message = "Status không được null")
    private String status;

    private boolean type;

    @NotNull(message = "idProvince không được null")
    private Long idProvince;
    @NotNull(message = "idDistrict không được null")
    private Long idDistrict;
    @NotNull(message = "idCommune không được null")
    private Long idCommune;


}
