package com.example.ncjavaproject;

import com.example.ncjavaproject.models.Attribute;
import com.example.ncjavaproject.models.AttributeType;
import com.example.ncjavaproject.models.ObjectType;
import com.example.ncjavaproject.models.Value;
import com.example.ncjavaproject.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories
public class NCJavaProject {

    public static void main(String[] args) {

        SpringApplication.run(NCJavaProject.class, args);
    }

    //@Bean
    public CommandLineRunner runner(
            @Autowired AttributeRepository attributeRepository,
            @Autowired AttributeTypeRepository attributeTypeRepository,
            @Autowired LinkValueRepository linkValueRepository,
            @Autowired ValueRepository valueRepository,
            @Autowired ObjectRepository objectRepository,
            @Autowired ObjectTypeRepository objectTypeRepository
            ) {
        return args -> {

        };
    }

}
