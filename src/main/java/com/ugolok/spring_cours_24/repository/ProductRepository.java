package com.ugolok.spring_cours_24.repository;

import com.ugolok.spring_cours_24.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    List<Product> findAll();
    Product save(Product product);
    Optional<Product> findById(Integer productId);

    void deleteById(Integer id);
}
