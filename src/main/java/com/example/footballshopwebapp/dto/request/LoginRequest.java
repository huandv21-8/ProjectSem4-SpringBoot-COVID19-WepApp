package com.example.footballshopwebapp.dto.request;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {


    @NotNull
    @Email(message = "Email is required")
    private String email;

    @NotNull
    @Size(min = 6, max =50, message
            = "Password must be between 6 and 50 characters")
    private String password;
}
