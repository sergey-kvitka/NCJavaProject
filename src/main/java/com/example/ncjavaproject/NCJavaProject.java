package com.example.ncjavaproject;

import com.example.ncjavaproject.models.DBValue;
import com.example.ncjavaproject.models.LinkValue;
import com.example.ncjavaproject.models.ObjectDB;
import com.example.ncjavaproject.models.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories
public class NCJavaProject {

    public static void main(String[] args) {
        SpringApplication.run(NCJavaProject.class, args);
    }
}
