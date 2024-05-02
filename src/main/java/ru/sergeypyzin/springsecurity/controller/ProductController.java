package ru.sergeypyzin.springsecurity.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.sergeypyzin.springsecurity.model.Product;
import ru.sergeypyzin.springsecurity.service.ProductService;


@Controller
@AllArgsConstructor
public class ProductController {

    private final ProductService service;

    @GetMapping("/")
    public String getAllProducts(Model model) {
        model.addAttribute("products", service.getAllProduct());
        return "guestPage";
    }

    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }

    @GetMapping("/public-data")
    public String getPublicData(Model model) {
        model.addAttribute("products", service.getAllProduct());
        return "publicDataPage";
    }

    @GetMapping("/private-data")
    public String getPrivateData(Model model) {
        model.addAttribute("products", service.getAllProduct());
        return "privateDataPage";
    }

    @PostMapping("/add-product")
    public String addProduct(@RequestParam String productName, @RequestParam Double price) {
        Product product = new Product();
        product.setProductName(productName);
        product.setPrice(price);
        service.addProduct(product);
        return "redirect:/guestPage";
    }

    @PostMapping("/delete-product")
    public String deleteProduct(@RequestParam Long id) {
        service.deleteProduct(id);
        return "redirect:/guestPage";
    }

}

