package com.example.footballshopwebapp.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressRequest {

//    @NotNull(message = "Address không được null")
    private int idAddress;
//    @NotNull(message = "idProvince không được null")
    private int idProvince;
//    @NotNull(message = "idDistrict không được null")
    private int idDistrict;
//    @NotNull(message = "idCommune không được null")
    private int idCommune;


}
