package com.example.ncjavaproject.security.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.persistence.*;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(name = "user_id")
    Long userId;

    @Column(name = "order_date")
    Date orderDate;
}