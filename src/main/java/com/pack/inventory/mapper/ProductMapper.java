package com.pack.inventory.mapper;

import java.time.LocalDateTime;

import com.pack.inventory.dto.ProductRequestDto;
import com.pack.inventory.model.Product;

public class ProductMapper {
  public static Product toEntity(ProductRequestDto dto) {
    Product product = new Product();
    product.setName(dto.getName());
    product.setPrice(dto.getPrice());
    product.setStock(dto.getStock());
    product.setCreatedAt(LocalDateTime.now());
    product.setProductLimit(dto.getProductLimit());
    return product;
  }
}
