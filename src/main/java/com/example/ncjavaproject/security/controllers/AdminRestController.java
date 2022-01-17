package com.example.ncjavaproject.security.controllers;

import com.example.ncjavaproject.security.dto.UserDto;
import com.example.ncjavaproject.security.entities.User;
import com.example.ncjavaproject.security.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/admin/")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AdminRestController {

    private final UserService userService;

    public AdminRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "users/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable(name = "id") Long id) {
        User user = userService.findById(id);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        UserDto result = UserDto.fromUser(user);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
