package com.example.ncjavaproject.security.jwt;

import com.example.ncjavaproject.security.entities.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class JwtUserFactory {
    public JwtUserFactory() {
    }

    public static JwtUser create(User user) {
        return new JwtUser(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getFirstName(),
                user.getLastName(),
                user.getDateOfBirth(),
                List.of(new SimpleGrantedAuthority(user.getRole()))
//                mapToGrantedAuthorities(user.getRole())
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(String role) {
        List<String> rolesByPrivileges = List.of(
                "customer", "admin"
        );
        return rolesByPrivileges
                .subList(0, rolesByPrivileges.indexOf(role) + 1)
                .stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}
