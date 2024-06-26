package ru.sergeypyzin.springsecurity.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sergeypyzin.springsecurity.annotation.TrackUserAction;
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

    @TrackUserAction
    public Product addProduct (Product product) {
        return productRepository.save(product);
    }

    @TrackUserAction
    public String deleteProduct (Long id) {
        productRepository.deleteById(id);
        return "Товар с идентификатором " + id + " был удален";
    }




}
