package com.example.ncjavaproject.security.services;

import com.example.ncjavaproject.security.entities.OrderItem;
import com.example.ncjavaproject.security.repositories.OrderItemRepository;
import com.example.ncjavaproject.security.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;

    public OrderItemService(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    public List<OrderItem> getAllByOrderId(Long id) {
        return orderItemRepository.findAllByOrderId(id);
    }

    public OrderItem addOrderItem(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }
}
