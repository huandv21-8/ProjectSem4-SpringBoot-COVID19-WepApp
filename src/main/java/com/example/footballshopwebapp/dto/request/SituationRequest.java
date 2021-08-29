package com.example.footballshopwebapp.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SituationRequest {
    @NotNull(message = "Content không được null")
    private String content;
}
