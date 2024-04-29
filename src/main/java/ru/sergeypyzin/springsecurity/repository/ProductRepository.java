package ru.sergeypyzin.springsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sergeypyzin.springsecurity.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
