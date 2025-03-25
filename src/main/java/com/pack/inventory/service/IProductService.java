package com.pack.inventory.service;

import java.util.List;
import java.util.Set;

import com.pack.inventory.dto.ProductRequestDto;
import com.pack.inventory.model.Product;

public interface IProductService {
  Product createProduct(ProductRequestDto dto);

  List<Product> getAllProducts();

  Product updateProduct(Long productId, ProductRequestDto dto);

  void deleteProduct(Long productId);

  Set<Product> getLowStockProducts();
}
