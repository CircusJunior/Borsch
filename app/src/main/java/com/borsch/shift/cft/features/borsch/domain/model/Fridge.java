package com.borsch.shift.cft.features.borsch.domain.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public final class Fridge {
    private ArrayList<Product> products = new ArrayList<>();

    public Fridge(ArrayList<Product> products) {
        this.products.addAll(products);
    }

    public ArrayList<Product> getProducts() {
        return products;
    }
}
