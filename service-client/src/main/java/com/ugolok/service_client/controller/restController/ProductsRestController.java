package com.ugolok.service_client.controller.restController;


import com.ugolok.service_client.controller.payload.ProductForm;
import com.ugolok.service_client.entity.Product;
import com.ugolok.service_client.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

@RequestMapping("/catalog-api/products")
@RequiredArgsConstructor
@RestController
public class ProductsRestController {

    private final ProductService productService;



    @GetMapping
    public Iterable<Product> findProducts(@RequestParam(name = "filter", required = false) String details) {
        return this.productService.findAllProducts(details);
    }

    @GetMapping("by-details")
    public Iterable<Product> findProductsByDetailsIgnoreCase(@RequestParam  String filter) {
        return this.productService.findByDetailsIgnoreCase(filter);
    }

    @GetMapping("by-title")
    public Iterable<Product> findProductsByTitleIgnoreCase(@RequestParam  String filter) {
        return this.productService.findByTitleIgnoreCase(filter);
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
