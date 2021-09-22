package com.example.footballshopwebapp.dto;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
public class Otp {

    @Min(100000)
    @Max(999999)
    private int otp;
}
