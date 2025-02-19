package com.ugolok.manager_app.repository;

import com.ugolok.manager_app.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.IntStream;

@Repository
public class InMemoryProductRepositoryImpl implements ProductRepository {

    private final List<Product> products = Collections.synchronizedList(new LinkedList<>());

    public InMemoryProductRepositoryImpl() {
        IntStream.range(1,4)
                .forEach(i->this.products.add(new Product(i,"Товар №%d".formatted(i),
                        "Описание товара №%d".formatted(i))));
    }

    @Override
    public List<Product> findAll() {
        return Collections.unmodifiableList(products);
    }

    // Добавляет товар с максимальным id на единицу больше
    @Override
    public Product save(Product product) {
        product.setId(this.products.stream()
                .max(Comparator.comparingInt(Product::getId))
                .map(Product::getId)
                .orElse(0)  +1
        );
        products.add(product);
        return product;
    }

    @Override
    public Optional<Product> findById(Integer productId) {
        return this.products.stream()
                .filter(product -> product.getId().equals(productId))
                .findFirst();
    }

    @Override
    public void deleteById(Integer id) {
        this.products.removeIf(product -> product.getId().equals(id));
    }
}
