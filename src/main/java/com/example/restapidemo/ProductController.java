package com.example.restapidemo;

import com.example.restapidemo.dto.ProductDto;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//Controller -> endpointy zwracają widoki (np. html)
//RestController -> endpointy zwracają dane (np. json)
public class ProductController {

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

    @GetMapping("/product3")
    public ResponseEntity<ProductDto> getDummyProduct3(){
        ProductDto productDto = new ProductDto(2,"chleb",100,4.5);
        return ResponseEntity
                .status(201)
                .header("myCustomHeader", "value")
                .body(productDto);

    }



}
