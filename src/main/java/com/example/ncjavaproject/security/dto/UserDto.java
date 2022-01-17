package com.example.ncjavaproject.security.dto;

import com.example.ncjavaproject.security.entities.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {
    private Long id;
    private String username;
    private String firstname;
    private String lastname;
    private Date dateOfBirth;

    public User toUser() {
        User user = new User();
        user.setId(id);
        user.setUsername(username);
        user.setFirstName(firstname);
        user.setLastName(lastname);
        user.setDateOfBirth(dateOfBirth);
        return user;
    }

    public static UserDto fromUser(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setFirstname(user.getFirstName());
        userDto.setLastname(user.getLastName());
        userDto.setDateOfBirth(user.getDateOfBirth());
        return userDto;
    }
}

