package com.example.ncjavaproject.security.controllers;

import com.example.ncjavaproject.security.dto.AuthenticationRequestDto;
import com.example.ncjavaproject.security.dto.RegistrationRequestDto;
import com.example.ncjavaproject.security.entities.User;
import com.example.ncjavaproject.security.jwt.JwtTokenProvider;
import com.example.ncjavaproject.security.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/auth")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AuthenticationRestController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    public AuthenticationRestController(AuthenticationManager authenticationManager,
                                        JwtTokenProvider tokenProvider,
                                        UserService userService
    ) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = tokenProvider;
        this.userService = userService;
    }

    @PostMapping("login")
    public ResponseEntity<Map<Object, Object>> login(@RequestBody AuthenticationRequestDto requestDto) {
        try {
            String username = requestDto.getUsername();

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, requestDto.getPassword())
            );
            User user = userService.findByUsername(username);
            if (user == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//                throw new UsernameNotFoundException("User with name " + username + " not found");
            }
            String token = jwtTokenProvider.createToken(username, user.getRole());

            Map<Object, Object> response = new HashMap<>();
            response.put("username", username);
            response.put("token", token);

            return ResponseEntity.ok(response);

        } catch (AuthenticationException e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            throw new BadCredentialsException("Invalid username or password");
        }
    }

    @PostMapping("register")
    public ResponseEntity<Map<Object, Object>> register(@RequestBody RegistrationRequestDto requestDto) {
        String username = requestDto.getUsername();
        if (userService.existsByUsername(username)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        userService.register(
                User.builder()
                        .username(username)
                        .password(requestDto.getPassword())
                        .firstName(requestDto.getFirstname())
                        .lastName(requestDto.getLastname())
                        .dateOfBirth(requestDto.getDateOfBirth())
                        .build()
        );
        AuthenticationRequestDto authenticationRequestDto =
                new AuthenticationRequestDto(username, requestDto.getPassword());
        return login(authenticationRequestDto);
    }
}
