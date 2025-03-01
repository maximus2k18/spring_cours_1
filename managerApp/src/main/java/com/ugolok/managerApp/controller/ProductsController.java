package com.ugolok.managerApp.controller;


import com.ugolok.managerApp.client.BadRequestException;
import com.ugolok.managerApp.client.ProductsRestClient;
import com.ugolok.managerApp.controller.payload.NewProductForm;
import com.ugolok.managerApp.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("catalog/products")
public class ProductsController {

    private final ProductsRestClient productsRestClient;

    @GetMapping("list")
    public String getProductsList(Model model, @RequestParam(required = false) String filter,
                                               @RequestParam(required = false) String filter2) {
        if (filter != null && !filter.isBlank()) {
            model.addAttribute("productsByTitle", this.productsRestClient.findAllProducts(filter));
        }
        if (filter2 != null && !filter2.isBlank()) {
            model.addAttribute("productsByDetails", this.productsRestClient.findByDetailsIgnoreCase(filter2));
        }
        model.addAttribute("filter", filter);
        model.addAttribute("filter2", filter2);
        return "catalog/products/list";
    }

    @GetMapping("create")
    public String getNewProductPage() {
        return "catalog/products/new_product";
    }

    @PostMapping("create")
    public String createProduct(NewProductForm payload,
                                Model model) {
        try {
            Product product = this.productsRestClient.createProduct(payload.title(), payload.details());
            return "redirect:/catalog/products/%d".formatted(product.getId());
        } catch (BadRequestException exception) {
            model.addAttribute("payload", payload);
            model.addAttribute("errors", exception.getErrors());
            return "catalog/products/new_product";
        }
    }


}
