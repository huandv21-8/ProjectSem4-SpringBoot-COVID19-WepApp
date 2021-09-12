package com.example.footballshopwebapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatusByTime {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long statusByTimeId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "peopleId", referencedColumnName = "peopleId")
    private People people;

    @NotBlank
    private String status;

    @NonNull
    private boolean type;

    @NonNull
    private Date updatedAt;

    private String travelSchedule;

    private Long id_source;


}
