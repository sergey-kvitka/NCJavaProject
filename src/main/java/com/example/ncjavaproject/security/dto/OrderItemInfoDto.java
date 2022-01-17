package com.example.ncjavaproject.security.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderItemInfoDto {
    Long orderItemId;
    Long orderId;
    Long productId;
    String productName;
    Long amount;
    Double totalPrice;
}
