package com.ugolok.serviceClient.repository;


import com.ugolok.serviceClient.entity.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {

    Iterable<Product> findAllByTitleLikeIgnoreCase(String filter);

    Iterable<Product> findByDetailsIgnoreCase(String filter);

    Iterable<Product> findByTitleIgnoreCase(String details);
}
