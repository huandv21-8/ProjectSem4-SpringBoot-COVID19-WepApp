package com.example.footballshopwebapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Died {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long diedId;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sickId", referencedColumnName = "sickId")
    private Sick sick;

    private Date time;
    private boolean active;

}
