package ru.sergeypyzin.springsecurity.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sergeypyzin.springsecurity.model.Product;
import ru.sergeypyzin.springsecurity.repository.ProductRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> getAllProduct () {
        return productRepository.findAll();
    }


}
