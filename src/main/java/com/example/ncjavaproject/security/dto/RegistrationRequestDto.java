package com.example.ncjavaproject.security.dto;

import lombok.Data;

import java.util.Date;

@Data
public class RegistrationRequestDto {
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private Date dateOfBirth;
}
