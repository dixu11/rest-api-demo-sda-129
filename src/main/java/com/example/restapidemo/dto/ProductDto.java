package com.example.restapidemo.dto;

public class ProductDto { //dto - data transfer object -> na strukturę jsona
    private int id;
    private String name;
    private int amount;
    private double price;

    public ProductDto() {
    }

    public ProductDto(int id, String name, int amount, double price) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.price = price;
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }

    public double getPrice() {
        return price;
    }
}
