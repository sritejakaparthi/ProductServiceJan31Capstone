package com.scaler.productservicejan31capstone.repositories;

import com.scaler.productservicejan31capstone.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long>
{
    Product save(Product product);

    List<Product> findAll();

    Optional<Product> findById(Long id);
}
