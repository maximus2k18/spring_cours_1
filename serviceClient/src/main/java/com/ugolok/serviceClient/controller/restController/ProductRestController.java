package com.ugolok.serviceClient.controller.restController;



import com.ugolok.serviceClient.controller.payload.ProductForm;
import com.ugolok.serviceClient.entity.Product;
import com.ugolok.serviceClient.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
@RequestMapping("catalog-api/products/{productId:\\d+}")
public class ProductRestController {
    private final ProductService productService;
    private final MessageSource messageSource;

    @ModelAttribute
    public Product getProduct(@PathVariable int productId) {
        return productService.findProduct(productId)
                .orElseThrow(()->new NoSuchElementException("catalog.errors.product.not_found"));
    }

    @GetMapping
    public Product findProduct(@ModelAttribute Product product) {
        return product;
    }

    @PatchMapping
    public ResponseEntity<?> updateProduct(@PathVariable int productId,
                                           @Valid @RequestBody ProductForm productForm,
                                           BindingResult bindingResult) throws BindException {
        if (bindingResult.hasErrors()) {
            if (bindingResult instanceof BindException bindException) {
                throw bindException;
            }
            else throw new BindException(bindingResult);
        } else {
            productService.updateProduct(productId, productForm.title(), productForm.details());
            return ResponseEntity.noContent().build(); //noContent, в ответе не будет тела
        }
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteProduct(@PathVariable("productId") int productId) {
        this.productService.deleteProduct(productId);
        return ResponseEntity.noContent()
                .build();
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ProblemDetail> handleNoSuchElementException(NoSuchElementException exception,
                                                                      Locale locale) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND,
                        this.messageSource.getMessage(exception.getMessage(), new Object[0],
                                exception.getMessage(), locale)));
    }


}

