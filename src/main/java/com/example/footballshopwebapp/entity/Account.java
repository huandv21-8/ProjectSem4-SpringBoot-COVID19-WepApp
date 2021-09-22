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
public class Account {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long accountId;

    @NotBlank(message = "Name is required")
    private String name;

    @NonNull
    private Date birthDay;

    @NonNull
    private boolean gender;

    @Column(unique = true)
    private String phone;

    private String  cmt;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "communeId", referencedColumnName = "communeId")
    private Commune commune;

    @NonNull
    private boolean active;

    private Date time;
    private String address;

}
