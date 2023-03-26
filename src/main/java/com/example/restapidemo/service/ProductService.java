package com.example.restapidemo.service;

import com.example.restapidemo.dto.ProductDto;
import com.example.restapidemo.entity.Product;
import com.example.restapidemo.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {


    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void addProduct(ProductDto request) {
        Product product = new Product(request.getName(),request.getAmount(),request.getPrice());
        productRepository.save(product);
    }


    public ProductDto findById(long id) {
        Product product = productRepository.findById(id)
                .orElseThrow();
        ProductDto productDto = new ProductDto(product.getId(),product.getName()
                ,product.getAmount(),product.getPrice());
        return productDto;

     /*   return findAll().stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElseThrow();*/
    }

    public List<ProductDto> getProducts(int maxPrice) {
        return productRepository.findAllByPriceIsLessThanEqual(maxPrice)
                .stream()
                .map(p -> new ProductDto(p.getId(), p.getName()
                        , p.getAmount(), p.getPrice()))
                .toList();

       /* return findAll().stream()
                .filter(p -> p.getPrice()<= maxPrice)
                .toList();*/
    }


    private List<ProductDto> findAll() { //zaślepka na bazę danych i metodę findAll
        return List.of(
                new ProductDto(1, "mleko", 1000, 3.5),
                new ProductDto(2, "chleb", 100, 4.5),
                new ProductDto(3, "masło", 200, 8)
        );
    }
}
