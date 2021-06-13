package com.example.footballshopwebapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import java.time.Instant;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class People {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long peopleId;

    @NotBlank(message = "Name is required")
    private String name;

    @NonNull
    private int age;
    @NonNull
    private boolean gender;
    @NotBlank(message = "Phone is required")
    private String phone;
    @NotBlank(message = "Schedule is required")
    private String schedule;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "provinceId", referencedColumnName = "provinceId")
    private Province province;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "districtId", referencedColumnName = "districtId")
    private District district;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "communeId", referencedColumnName = "communeId")
    private Commune commune;

    private Instant time;

}
