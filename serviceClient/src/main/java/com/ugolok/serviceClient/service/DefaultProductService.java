package com.ugolok.serviceClient.service;


import com.ugolok.serviceClient.entity.Product;
import com.ugolok.serviceClient.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DefaultProductService implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Iterable<Product> findByDetailsIgnoreCase(String details) {
        if (details != null && !details.isBlank()) {
            return this.productRepository.findByDetailsIgnoreCase(details);
        } else {
            return this.productRepository.findAll();
        }
    }

    @Override
    public Iterable<Product> findByTitleIgnoreCase(String details) {
        if (details != null && !details.isBlank()) {
            return this.productRepository.findByTitleIgnoreCase(details);
        } else {
            return this.productRepository.findAll();
        }
    }

    @Override
    public Iterable<Product> findAllProducts(String filter) {
        if (filter != null && !filter.isBlank()) {
            return this.productRepository.findAllByTitleLikeIgnoreCase("%" + filter + "%");
        } else {
            return this.productRepository.findAll();
        }
    }

    @Override
    @Transactional
    public Product createProduct(String title, String details) {
        return this.productRepository.save(new Product(null, title, details));
    }

    @Override
    public Optional<Product> findProduct(long productId) {
        return this.productRepository.findById(productId);
    }

    @Override
    @Transactional
    public void updateProduct(long id, String title, String details) {
        this.productRepository.findById(id)
                .ifPresentOrElse(product -> {
                    product.setTitle(title);
                    product.setDetails(details);
                }, () -> {
                    throw new NoSuchElementException();
                });
    }

    @Override
    @Transactional
    public void deleteProduct(long id) {
        this.productRepository.deleteById(id);
    }

}
