package com.example.ncjavaproject.security.services;

import com.example.ncjavaproject.security.entities.Order;
import com.example.ncjavaproject.security.repositories.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getOrdersByUserId(Long id) {
        return orderRepository.findAllByUserId(id);
    }

    public Order getById(Long orderId) {
        return orderRepository.findById(orderId).orElse(null);
    }

    public void deleteById(Long id) {
        orderRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return orderRepository.existsById(id);
    }
}
