package com.example.ncjavaproject;

import com.example.ncjavaproject.models.DBValue;
import com.example.ncjavaproject.models.LinkValue;
import com.example.ncjavaproject.models.ObjectDB;
import com.example.ncjavaproject.models.Value;
import com.example.ncjavaproject.repositories.ObjectTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;


@SpringBootApplication
@EnableJpaRepositories
public class NCJavaProject {

    public static void main(String[] args) {
        SpringApplication.run(NCJavaProject.class, args);
    }

    @Bean
    public CommandLineRunner runner(@Autowired ObjectTypeRepository repository) {
        return args -> {

        };
    }
}

