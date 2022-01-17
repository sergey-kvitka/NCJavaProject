package com.example.ncjavaproject.security.repositories;

import com.example.ncjavaproject.security.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByUserId(Long id);
}
