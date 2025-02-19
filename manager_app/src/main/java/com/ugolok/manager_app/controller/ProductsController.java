package com.ugolok.manager_app.controller;


import com.ugolok.manager_app.controller.payload.ProductForm;
import com.ugolok.manager_app.entity.Product;
import com.ugolok.manager_app.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("catalog/products")
public class ProductsController {
    private final ProductService productService;

    @GetMapping("list")
    public String getProductsList(Model model) {
        model.addAttribute("products", this.productService.findAllProducts());
        return "catalog/products/list";
    }

    @GetMapping("create")
    public String getNewProduct() {
        return "catalog/products/new_product";
    }

    @PostMapping("create")
    public String createProduct(@Valid ProductForm payload,
                                BindingResult bindingResult, Model model) { // Model позволяет добавить параметры,
        // которые пропадают при обновлении страницы, а bindingResult позволяет вывести ошибки на страницу
        if (bindingResult.hasErrors()) {
            model.addAttribute("payload", payload);
            model.addAttribute("errors", bindingResult.getAllErrors().stream()
                    .map(ObjectError::getDefaultMessage)
                    .toList());
            return "catalog/products/new_product";
        }
        else {
            Product product = this.productService.createProduct(payload.title(), payload.details());
            return "redirect:/catalog/products/%d".formatted(product.getId());
        }
    }


}
