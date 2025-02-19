package com.ugolok.manager_app.controller.restController;

import com.ugolok.manager_app.controller.payload.ProductForm;
import com.ugolok.manager_app.entity.Product;
import com.ugolok.manager_app.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Map;

@RequestMapping("catalog-api/products")
@RequiredArgsConstructor
@RestController
public class ProductsRestController {

    private final ProductService productService;

    @GetMapping
    public List<Product> getAllProduct() {
        return this.productService.findAllProducts();
    }

    @PostMapping
    public ResponseEntity<?> createProduct(@Valid @RequestBody ProductForm payload,
                                            BindingResult bindingResult,
                                            UriComponentsBuilder uriComponentsBuilder) throws BindException{
        if (bindingResult.hasErrors()) {
            if (bindingResult instanceof BindException bindException) {
                throw bindException;
            }
            else throw new BindException(bindingResult);
        } else {
            Product product = this.productService.createProduct(payload.title(), payload.details()); return ResponseEntity
                    .created(uriComponentsBuilder
                            .replacePath("/catalogue-api/products/{productId}")
                            . build(Map.of("productId", product.getId())))
                    .body(product);
        }
    }
}
