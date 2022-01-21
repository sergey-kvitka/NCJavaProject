package com.example.ncjavaproject.security.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductAmountDto {
    Long productId;
    Long amount;
}
