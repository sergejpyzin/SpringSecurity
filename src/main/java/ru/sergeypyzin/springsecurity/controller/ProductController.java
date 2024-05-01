package ru.sergeypyzin.springsecurity.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.sergeypyzin.springsecurity.service.ProductService;


@Controller
@AllArgsConstructor
public class ProductController {

   private final ProductService service;

   @GetMapping("/")
   public String getAllProducts (Model model) {
       model.addAttribute("products", service.getAllProduct());
       return "guestPage";
   }

   @GetMapping("/login")
    public String getLogin () {
       return "authorization";
   }
}

