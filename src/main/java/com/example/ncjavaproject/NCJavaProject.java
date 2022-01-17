package com.example.ncjavaproject;

import com.example.ncjavaproject.repositories.ObjectTypeRepository;
import com.example.ncjavaproject.security.entities.User;
import com.example.ncjavaproject.security.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories
public class NCJavaProject {

    public static void main(String[] args) {
        SpringApplication.run(NCJavaProject.class, args);

    }

    @Bean
    public CommandLineRunner runner(@Autowired ObjectTypeRepository repository, @Autowired UserRepository userRepository) {
        return args -> {

        };
    }
}

