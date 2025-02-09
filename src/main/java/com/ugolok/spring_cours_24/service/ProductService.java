package com.ugolok.spring_cours_24.service;

import com.ugolok.spring_cours_24.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> findAllProducts();
    Product createProduct(String title, String details);
    Optional<Product> findProduct(Integer productId);
    void updateProduct(Integer id, String title, String details);
    void deleteProduct(Integer id);
}
