package com.example.ncjavaproject.security.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Map;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CatalogProductDto {
    Long productId;
    String productName;
    String productTypeName;
    Map<String, String[]> attributesAndValues;
    Long amount;
    Double price;
}
