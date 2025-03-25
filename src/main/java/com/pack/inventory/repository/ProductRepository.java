package com.pack.inventory.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.pack.inventory.model.Product;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
