package com.pack.inventory.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pack.inventory.dto.ProductRequestDto;
import com.pack.inventory.response.StandardApiResponse;
import com.pack.inventory.service.IProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("${api.prefix}/products")
@RequiredArgsConstructor
@Validated
@Tag(name = "Products", description = "Apis to manage the different products")
public class ProductController {
  private final IProductService productService;

  @PostMapping("/create")
  @Operation(summary = "Endpoint to create a product")
  public ResponseEntity<StandardApiResponse> createProduct(@Valid @RequestBody ProductRequestDto dto) {
    return ResponseEntity
        .ok(new StandardApiResponse("Product created successfully", productService.createProduct(dto)));
  }

  @GetMapping
  @Operation(summary = "Endpoint to get all the products")
  public ResponseEntity<StandardApiResponse> getAllProducts() {
    return ResponseEntity.ok(new StandardApiResponse("Products fetched successfully", productService.getAllProducts()));

  }

  @PutMapping("/update")
  @Operation(summary = "Endpoint to update a product")
  public ResponseEntity<StandardApiResponse> updateProduct(@RequestParam Long productId,
      @Valid @RequestBody ProductRequestDto dto) throws Exception {
    return ResponseEntity
        .ok(new StandardApiResponse("Product updated successfully", productService.updateProduct(productId, dto)));
  }

  @DeleteMapping("/delete")
  @Operation(summary = "Endpoint to delete a product")
  public ResponseEntity<StandardApiResponse> deleteProductById(@RequestParam Long productId) throws Exception {
    productService.deleteProduct(productId);
    return ResponseEntity.ok(new StandardApiResponse("Product Deleted successfully", null));
  }

  @GetMapping("/low-stock")
  @Operation(summary = "Endpoint to get all the products that are low on stock")
  public ResponseEntity<StandardApiResponse> getLowStockProducts() {
    return ResponseEntity
        .ok(new StandardApiResponse("Low Stock products fetched", productService.getLowStockProducts()));
  }
}
