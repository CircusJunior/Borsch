package com.borsch.shift.cft.features.borsch.domain;

import com.borsch.shift.cft.features.account.domain.model.UserValidInfo;
import com.borsch.shift.cft.features.borsch.data.ContentRepository;
import com.borsch.shift.cft.features.borsch.domain.model.Food;
import com.borsch.shift.cft.features.borsch.domain.model.Fridge;
import com.borsch.shift.cft.features.borsch.domain.model.Product;
import com.borsch.shift.cft.features.borsch.domain.model.Recipe;
import com.borsch.shift.cft.features.borsch.domain.model.Success;
import com.borsch.shift.cft.network.Carry;

import java.util.ArrayList;

public final class ContentInteractorImpl implements ContentInteractor{

    private final ContentRepository repository;

    public ContentInteractorImpl(ContentRepository repository) {
        this.repository = repository;
    }

    @Override
    public void loadFridge( Carry<ArrayList<Product>> carry) {
        repository.loadFridge( carry);
    }

    @Override
    public void loadRecipe(String id, Carry<ArrayList<Product>> carry) {
        repository.loadRecipe(id, carry);
    }

    @Override
    public void loadRecipe(Carry<ArrayList<Product>> carry) {
        repository.loadRecipe(carry);
    }

    @Override
    public void recipeAddProduct(Product product, Carry<Success> carry) {
        repository.recipeAddProduct(product, carry);
    }

    @Override
    public void deleteProduct(Product id, Carry<Success> carry) {
        repository.deleteProduct(id, carry);
    }

    @Override
    public void changeProduct(Carry<ArrayList<Food>> carry) {
        repository.changeProduct(carry);
    }

    @Override
    public void fridgeAddProduct(Product product, Carry<Success> carry) {
        repository.fridgeAddProduct(product, carry);
    }
}
