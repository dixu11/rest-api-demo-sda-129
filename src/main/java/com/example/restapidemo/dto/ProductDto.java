package com.example.restapidemo.dto;

import lombok.*;

//@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@Builder
public class ProductDto { //dto - data transfer object -> na strukturę jsona
    private long id;
    private String name;
    private int amount;
    private double price;


    //getter customowy ma pierwszeństwo przed generowanymi
    public String getName() {
        System.out.println(name);
        return name;
    }
}

