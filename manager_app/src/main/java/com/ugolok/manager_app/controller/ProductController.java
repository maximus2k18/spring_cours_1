package com.ugolok.manager_app.controller;


import com.ugolok.manager_app.controller.payload.UpdateProduct;
import com.ugolok.manager_app.entity.Product;
import com.ugolok.manager_app.service.ProductService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.NoSuchElementException;

@Controller
@RequiredArgsConstructor
@RequestMapping("catalog/products/{productId:\\d+}") //  \\d+ match only digits

public class ProductController {

    private final ProductService productService;
    private final MessageSource messageSource;

    @ModelAttribute("product") // срабатывает перед всеми   @GetMapping(), @PostMapping()...
    public Product product(@PathVariable("productId") int productId) {
        System.out.println("Получен ID: " + productId);
        return this.productService.findProduct(productId).orElseThrow(
                ()->new NoSuchElementException("catalog.errors.product.not_found"));
    }

    @GetMapping()
    public String getProduct() {
        return "catalog/products/product";
    }

    @GetMapping("edit")
    public String getProductEditPage() {
        return "catalog/products/edit";
    }

    @PostMapping("edit")
    public String updateProduct(@ModelAttribute(name = "product", binding = false) Product product,
                                @Valid UpdateProduct payload,
                                BindingResult bindingResult,
                                Model model) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();
            bindingResult.getFieldErrors()
                    .forEach(error -> errorMap
                            .put(error.getField(), error.getDefaultMessage()));

            model.addAttribute("payload", payload);
            model.addAttribute("errors", errorMap);
            return "catalog/products/edit";
        } else {
            this.productService.updateProduct(product.getId(), payload.title(), payload.details());
            return "redirect:/catalog/products/%d".formatted(product.getId());
        }
    }

    @PostMapping("delete")
    public String deleteProduct(@ModelAttribute("product") Product product) {
        this.productService.deleteProduct(product.getId());
        return "redirect:/catalog/products/list";
    }

    @ExceptionHandler(NoSuchElementException.class)
    public String handleNoSuchElementException(NoSuchElementException exception, Model model,
                                               HttpServletResponse response, Locale locale) {
        response.setStatus(HttpStatus.NOT_FOUND.value());
        model.addAttribute("error", this.messageSource.getMessage(exception.getMessage(), null, locale));
        return "errors/404";
    }
}
