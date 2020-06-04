package com.example.grocerylistapp.model;

public class ProductOrder {
    private String product;

    public ProductOrder() {
    }

    public ProductOrder(String product) {
        this.product = product;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }
}
