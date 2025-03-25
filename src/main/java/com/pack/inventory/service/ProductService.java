package com.pack.inventory.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.pack.inventory.dto.ProductRequestDto;
import com.pack.inventory.exception.ResourceNotFoundException;
import com.pack.inventory.mapper.ProductMapper;
import com.pack.inventory.model.Product;
import com.pack.inventory.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService implements IProductService {
  private final ProductRepository productRepository;

  @Override
  public Product createProduct(ProductRequestDto dto) {
    checkStockAndAlert(ProductMapper.toEntity(dto));
    return productRepository.save(ProductMapper.toEntity(dto));
  }

  @Override
  public List<Product> getAllProducts() {
    return productRepository.findAll();
  }

  @Override
  public Product updateProduct(Long productId, ProductRequestDto dto) {
    Product productToUpdate = productRepository.findById(productId)
        .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
    productToUpdate.setName(dto.getName());
    productToUpdate.setPrice(dto.getPrice());
    productToUpdate.setStock(dto.getStock());
    checkStockAndAlert(productToUpdate);
    return productRepository.save(productToUpdate);
  }

  @Override
  public void deleteProduct(Long productId) {
    Product productToDelete = productRepository.findById(productId)
        .orElseThrow(() -> new ResourceNotFoundException("Product to delete not found"));
    productRepository.delete(productToDelete);
  }

  private void checkStockAndAlert(Product product) {
    if (product.getStock() < 5) {
      log.warn("⚠️ Alert Stock is Low : Product '{}' (ID: {}) - Only {} units left",
          product.getName(),
          product.getId(),
          product.getStock());
    }
  }

  @Override
  public Set<Product> getLowStockProducts() {
    List<Product> list = productRepository.findAll();
    Set<Product> lowProducts = new HashSet<>();
    list.forEach(product -> {
      if (product.getStock() < product.getProductLimit()) {
        lowProducts.add(product);
      }
    });
    return lowProducts;
  }

}
