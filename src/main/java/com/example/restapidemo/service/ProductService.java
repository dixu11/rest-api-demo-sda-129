package com.example.restapidemo.service;

import com.example.restapidemo.dto.ProductDto;
import com.example.restapidemo.entity.Product;
import com.example.restapidemo.mapper.ProductMapper;
import com.example.restapidemo.repository.ProductRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private ProductRepository productRepository;
    private ProductMapper mapper;

    public ProductService(ProductRepository productRepository, ProductMapper mapper) {
        this.productRepository = productRepository;
        this.mapper = mapper;
    }

    @PostConstruct
    public void createDummyData() {
        if (productRepository.count() > 0) {
            return;
        }

        List<ProductDto> productDtos = List.of(
                new ProductDto(1, "mleko", 1000, 3.5),
                new ProductDto(2, "chleb", 100, 4.5),
                new ProductDto(3, "masło", 200, 8)
        );

        productRepository.saveAll(mapper.mapToEntities(productDtos));
    }


    public void addProduct(ProductDto dto) {
        Product product = mapper.mapToEntity(dto);
        productRepository.save(product);
    }


    public ProductDto findById(long id) {
        Product product = productRepository.findById(id)
                .orElseThrow();
        return mapper.mapToDto(product);

     /*   return findAll().stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElseThrow();*/
    }

    public List<ProductDto> getProducts(int maxPrice) {
        List<Product> products = productRepository.findAllByPriceIsLessThanEqual(maxPrice);
        return mapper.mapToDtos(products);


       /* return findAll().stream()
                .filter(p -> p.getPrice()<= maxPrice)
                .toList();*/
    }
    public List<ProductDto> getProducts() {
        List<Product> products = productRepository.findAll();
        return mapper.mapToDtos(products);
    }

    private List<ProductDto> findAll() { //zaślepka na bazę danych i metodę findAll
        return List.of(
                new ProductDto(1, "mleko", 1000, 3.5),
                new ProductDto(2, "chleb", 100, 4.5),
                new ProductDto(3, "masło", 200, 8)
        );
    }
}
