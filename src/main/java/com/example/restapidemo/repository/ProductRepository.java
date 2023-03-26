package com.example.restapidemo.repository;

import com.example.restapidemo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {

    List<Product> findAllByPriceIsLessThanEqual(double price);
}
