package com.example.footballshopwebapp.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckEmailResetPassword {
    @Email(message = "Email is required")
    private String email;
}
