package com.example.footballshopwebapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import java.io.Serializable;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Province implements Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long provinceId;
    @NotBlank(message = "Name province is required")
    private String provinceName;
    private String type;
    private String slug;

}
