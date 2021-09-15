package com.example.footballshopwebapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Question {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long questionId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accountId", referencedColumnName = "accountId")
    private Account account;

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
    private boolean HighBloodPressure;
    private boolean hivOrImmunocompromised;
    private boolean organTransplantRecipient;
    private boolean diabetes;
    private boolean cancer;
    private boolean pregnant;
    private String travelSchedule;


}
