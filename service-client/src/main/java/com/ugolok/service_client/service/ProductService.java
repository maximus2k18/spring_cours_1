package com.ugolok.service_client.service;

import com.ugolok.service_client.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    Iterable<Product> findAllProducts();

    Product createProduct(String title, String details);

    Optional<Product> findProduct(long productId);

    void updateProduct(long id, String title, String details);

    void deleteProduct(long id);
}
