package com.ugolok.service_client.repository;


import com.ugolok.service_client.entity.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {

    Iterable<Product> findAllByTitleLikeIgnoreCase(String filter);

    Iterable<Product> findByDetailsIgnoreCase(String filter);

    Iterable<Product> findByTitleIgnoreCase(String details);
}
