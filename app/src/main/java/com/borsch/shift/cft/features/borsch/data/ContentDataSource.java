package com.borsch.shift.cft.features.borsch.data;

import com.borsch.shift.cft.features.account.domain.model.UserValidInfo;
import com.borsch.shift.cft.features.borsch.domain.model.Food;
import com.borsch.shift.cft.features.borsch.domain.model.Fridge;
import com.borsch.shift.cft.features.borsch.domain.model.Product;
import com.borsch.shift.cft.features.borsch.domain.model.Recipe;
import com.borsch.shift.cft.features.borsch.domain.model.Success;
import com.borsch.shift.cft.network.Carry;

import java.util.ArrayList;

public interface ContentDataSource {

    void getFridge( Carry<ArrayList<Product>> carry);

    void getRecipe(String id, Carry<ArrayList<Product>> carry);

    void getRecipe(Carry<ArrayList<Product>> carry);

    void recipeAddProduct(Product product, Carry<Success> carry);

    void deleteProduct(Product id, Carry<Success> carry);

    void changeProduct(Carry<ArrayList<Food>> carry);

    void fridgeAddProduct(Product product, Carry<Success> carry);
}
