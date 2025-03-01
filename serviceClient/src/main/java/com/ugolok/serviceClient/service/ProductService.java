package com.ugolok.serviceClient.service;

import com.ugolok.serviceClient.entity.Product;

import java.util.Optional;

public interface ProductService {

    Iterable<Product> findAllProducts(String filter);

    Iterable<Product> findByDetailsIgnoreCase(String details);

    Iterable<Product> findByTitleIgnoreCase(String details);

    Product createProduct(String title, String details);

    Optional<Product> findProduct(long productId);

    void updateProduct(long id, String title, String details);

    void deleteProduct(long id);

}
