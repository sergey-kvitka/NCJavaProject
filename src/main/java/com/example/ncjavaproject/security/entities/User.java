package com.example.ncjavaproject.security.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity(name = "all_users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(name = "login")
    String username;

    @Column(name = "password")
    String password;

    @Column(name = "firstname")
    String firstName;

    @Column(name = "lastname")
    String lastName;

    @Column(name = "date_of_birth")
    Date dateOfBirth;

    @Column(name = "role")
    String role;
}
