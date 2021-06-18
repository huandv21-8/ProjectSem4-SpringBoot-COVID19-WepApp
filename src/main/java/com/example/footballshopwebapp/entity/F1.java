package com.example.footballshopwebapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Date;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class F1 implements Serializable {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long f1Id;
    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "peopleId", referencedColumnName = "peopleId")
    private People people;
    private boolean type;
    private String status;
    private Date time;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sickId", referencedColumnName = "sickId")
    private Sick sickSource;
    private boolean active;

}
