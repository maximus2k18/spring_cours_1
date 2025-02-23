package com.ugolok.service_client.service;


import com.ugolok.service_client.entity.Product;
import com.ugolok.service_client.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DefaultProductService implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Iterable<Product> findAllProducts() {
        return this.productRepository.findAll();
    }

    @Override
    public Product createProduct(String title, String details) {
        return this.productRepository.save(new Product(null, title,  details));
    }

    @Override
    public Optional<Product> findProduct(long productId) {
        return this.productRepository.findById(productId);
    }

    @Override
    public void updateProduct(long id, String title, String details) {
        this.productRepository.findById(id)
                .ifPresentOrElse(product -> {
                    product.setTitle(title);
                    product.setDetails(details);
                    this.productRepository.save(product);
                        }, () -> {
                            throw new NoSuchElementException();
                        }
                );
    }

    @Override
    public void deleteProduct(long id) {
        this.productRepository.deleteById(id);
    }

}
