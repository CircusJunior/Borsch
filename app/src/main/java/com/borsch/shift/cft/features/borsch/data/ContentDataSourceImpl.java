package com.borsch.shift.cft.features.borsch.data;

import com.borsch.shift.cft.features.account.domain.model.UserValidInfo;
import com.borsch.shift.cft.features.borsch.domain.model.Food;
import com.borsch.shift.cft.features.borsch.domain.model.Fridge;
import com.borsch.shift.cft.features.borsch.domain.model.Product;
import com.borsch.shift.cft.features.borsch.domain.model.Recipe;
import com.borsch.shift.cft.features.borsch.domain.model.Success;
import com.borsch.shift.cft.network.Carry;
import com.borsch.shift.cft.network.DefaultCallback;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.IntToDoubleFunction;

public final class ContentDataSourceImpl implements ContentDataSource{

    private final ContentApi contentApi;

    public ContentDataSourceImpl(ContentApi contentApi) {
        this.contentApi = contentApi;
    }

    @Override
    public void getFridge( final Carry<ArrayList<Product>> carry) {
        contentApi.getFridge().enqueue(new DefaultCallback(carry));
    }

    @Override
    public void getRecipe(String id, final Carry<ArrayList<Product>> carry) {
        contentApi.getRecipe(id).enqueue(new DefaultCallback(carry));
    }

    @Override
    public void getRecipe(final Carry<ArrayList<Product>> carry) {
        contentApi.getRecipe().enqueue(new DefaultCallback(carry));
    }

    @Override
    public void recipeAddProduct(Product product, Carry<Success> carry) {
        contentApi.recipeAddProduct(product.getId(), product.getFoodId(), product.getFoodName(),
                    product.getAllWeight(), product.getReservedWeight()).enqueue(new DefaultCallback(carry));
    }

    @Override
    public void deleteProduct(Product id, Carry<Success> carry) {
        contentApi.deleteProduct(id).enqueue(new DefaultCallback(carry));
    }

    @Override
    public void changeProduct(Carry<ArrayList<Food>> carry) {
        contentApi.changeProduct().enqueue(new DefaultCallback(carry));
    }

    @Override
    public void fridgeAddProduct(Product product, Carry<Success> carry) {
        contentApi.fridgeAddProduct(product).enqueue(new DefaultCallback(carry));
    }
}
