package com.example.footballshopwebapp.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cured implements Serializable {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long curedId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "peopleId", referencedColumnName = "peopleId")
    private People people;

    private boolean status;
    private Instant time;
}