package com.example.restapidemo.controller;

import com.example.restapidemo.dto.ProductDto;
import com.example.restapidemo.service.ProductService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//Controller -> endpointy zwracają widoki (np. html)
//RestController -> endpointy zwracają dane (np. json)
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/api/products")
    public List<ProductDto> getAllProducts( int maxPrice) {
        return productService.getProducts(maxPrice);
    }



    //przesyłanie danych w ścieżce wymaga @PathVariable
    @GetMapping("/api/products/{id}")
    public ProductDto findById(@PathVariable int id){
        return productService.findById(id);
    }

    //przesyłanie jsona w ciele zapytania wymaga złapania go na odpowiadający obiekt + @RequestBody
    @PostMapping("/api/products")
    public void addProduct(@RequestBody ProductDto request) {
        productService.addProduct(request);
    }

    @GetMapping("/header/practice")
    public List<ProductDto> getAllProducts2(@RequestHeader(name="max") int maxPrice) {
        return productService.getProducts(maxPrice);
    }






























    //creating response practise


    @GetMapping("/product")
    //@ResponseStatus(HttpStatus.I_AM_A_TEAPOT) // jest możliwość zmiany domyślnego statusu odpowiedzi na dowolny
    public String getDummyProduct() {
        return "mleko";
    }

    //ResponseEntity - obiekt opakowujący body, reprezentujący odpowiedź i pozwalający na konfigurowanie jej parametrów
    // takich jak statusy czy headery
    @GetMapping("/product2")
    public ResponseEntity<ProductDto> getDummyProduct2(){
        ProductDto productDto = new ProductDto(1,"mleko",1000,3.5);
        return new ResponseEntity<>(productDto, HttpStatusCode.valueOf(200));
    }

    //budowanie ResponseEntity przy wykorzystaniu buildera a nie konstruktora
    @GetMapping("/product3")
    public ResponseEntity<ProductDto> getDummyProduct3(){
        ProductDto productDto = new ProductDto(2,"chleb",100,4.5);
        return ResponseEntity
                .status(201)
                .header("myCustomHeader", "value")
                .body(productDto);
    }

   //budowanie ResponseEntity przy wykorzystaniu dedykowanych scenariuszy
    @GetMapping("/product4")
    public ResponseEntity<ProductDto> getDummyProduct4() {
        ProductDto productDto = new ProductDto(3,"masło",200,8);
        return ResponseEntity.ok(productDto);
    }

    //zwracanie obiektu bez wykorzystania ResponseEntity
    @GetMapping("/product5")
    public ProductDto getDummyProduct5() {
       return new ProductDto(3,"masło",200,8);
    }

}
