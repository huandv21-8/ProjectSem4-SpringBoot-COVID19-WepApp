package com.example.footballshopwebapp.dto.request;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
public class ChangePassword {

    @Email
    @NotNull
    private String email;

    @NotNull
    @Size(min = 6, max =50, message = "Password must be between 6 and 50 characters")
    private String password;

    @NotNull
    private String checkPassword;

}
