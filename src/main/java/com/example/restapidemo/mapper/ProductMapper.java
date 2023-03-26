package com.example.restapidemo.mapper;

import com.example.restapidemo.dto.ProductDto;
import com.example.restapidemo.entity.Product;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductMapper {


    public List<ProductDto> mapToDtos(List<Product> products) {
        return products.stream()
                .map(p -> mapToDto(p))
                .toList();
    }

    public List<Product> mapToEntities(List<ProductDto> productDtos) {
        return productDtos.stream()
                .map(p -> mapToEntity(p))
                .toList();
    }


    public ProductDto mapToDto(Product product) {
        return new ProductDto(product.getId(),product.getName(),product.getAmount(),product.getPrice());
    }

    public Product mapToEntity(ProductDto productDto) {
        return new Product(productDto.getName(), productDto.getAmount(), productDto.getPrice());
    }


}
