package com.example.footballshopwebapp.dto.response;


import lombok.Data;

@Data
public class QuestionResponse {



        private String name;


        private String birthDay;

        private String cmt;

        private boolean gender;

        private String phone;


        private String provinceName;
        private String communeName;
        private String districtName;

        private String address;
        private String updateAt;

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
