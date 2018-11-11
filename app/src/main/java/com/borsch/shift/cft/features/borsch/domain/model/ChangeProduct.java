package com.borsch.shift.cft.features.borsch.domain.model;

import java.util.ArrayList;

public final class ChangeProduct {
    private ArrayList<Product> products = new ArrayList<>();

    public ChangeProduct(Product products) {
        this.products.add(products);
    }

    public ArrayList<Product> getProducts() {
        return products;
    }
}
