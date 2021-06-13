package com.example.footballshopwebapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Sick implements Serializable {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long sickId;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "peopleId", referencedColumnName = "peopleId")
    private People people;
    private boolean type;
    private String status;
    private Instant time;

    private Long idSickSource;
}
