package com.example.footballshopwebapp.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeclareRequest {


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


    @NotNull(message = "idCommune không được null")
    private Long idCommune;

    private String address;

    private boolean exposureToF0;
    private boolean comeBackFromEpidemicArea;
    private boolean contactWithPeopleReturningFromEpidemicAreas;
    private boolean fever;
    private boolean cough;
    private boolean shortnessOfBreath;
    private boolean pneumonia;
    private boolean soreThroat;
    private boolean tired;
    private boolean chronicLiverDisease;
    private boolean chronicBloodDisease;
    private boolean chronicLungDisease;
    private boolean chronicKideyDisease;
    private boolean heartRelatedDiseaes;
    private boolean highBloodPressure;
    private boolean hivOrImmunocompromised;
    private boolean organTransplantRecipient;
    private boolean diabetes;
    private boolean cancer;
    private boolean pregnant;
    private String travelSchedule;

}
