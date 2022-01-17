package com.example.ncjavaproject.security.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity(name = "order_item")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(name = "order_id")
    Long orderId;

    @Column(name = "product_id")
    Long productId;

    @Column(name = "amount")
    Long amount;
}
