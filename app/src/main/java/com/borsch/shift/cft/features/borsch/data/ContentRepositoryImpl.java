package com.borsch.shift.cft.features.borsch.data;

import com.borsch.shift.cft.features.account.domain.model.UserValidInfo;
import com.borsch.shift.cft.features.borsch.domain.model.Food;
import com.borsch.shift.cft.features.borsch.domain.model.Fridge;
import com.borsch.shift.cft.features.borsch.domain.model.Product;
import com.borsch.shift.cft.features.borsch.domain.model.Recipe;
import com.borsch.shift.cft.features.borsch.domain.model.Success;
import com.borsch.shift.cft.network.Carry;

import java.util.ArrayList;

public final class ContentRepositoryImpl implements ContentRepository{

    private final ContentDataSource dataSource;

    public ContentRepositoryImpl(ContentDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void loadFridge(Carry<ArrayList<Product>> carry) {
        dataSource.getFridge( carry);
    }

    @Override
    public void loadRecipe(String id, Carry<ArrayList<Product>> carry) {
        dataSource.getRecipe(id, carry);
    }

    @Override
    public void loadRecipe(Carry<ArrayList<Product>> carry) {
        dataSource.getRecipe( carry);
    }

    @Override
    public void recipeAddProduct(Product product, Carry<Success> carry) {
        dataSource.recipeAddProduct(product, carry);
    }

    @Override
    public void deleteProduct(Product id, Carry<Success> carry) {
        dataSource.deleteProduct( id, carry);
    }

    @Override
    public void changeProduct(Carry<ArrayList<Food>> carry) {
        dataSource.changeProduct( carry);
    }

    @Override
    public void fridgeAddProduct(Product product, Carry<Success> carry) {
        dataSource.fridgeAddProduct(product, carry);
    }
}
