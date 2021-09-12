package com.example.footballshopwebapp.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SituationResponse {
    private String content;
    private Date updatedAt;
}
