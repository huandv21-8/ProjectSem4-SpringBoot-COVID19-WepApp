package com.example.footballshopwebapp.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import java.io.Serializable;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Commune implements Serializable {

    @Id
    @GeneratedValue(strategy =   IDENTITY)
    private Long communeId;
    @NotBlank(message = "Name commune is required")
    private String communeName;
    private String type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "districtId", referencedColumnName = "districtId")
    private District district;
}
