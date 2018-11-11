package com.borsch.shift.cft.features.borsch.presentation;


import com.borsch.shift.cft.features.MvpPresenter;
import com.borsch.shift.cft.features.borsch.domain.ContentInteractor;
import com.borsch.shift.cft.features.borsch.domain.model.Food;
import com.borsch.shift.cft.features.borsch.domain.model.Fridge;
import com.borsch.shift.cft.features.borsch.domain.model.Product;
import com.borsch.shift.cft.features.borsch.domain.model.Recipe;
import com.borsch.shift.cft.features.borsch.domain.model.Success;
import com.borsch.shift.cft.network.Carry;

import java.util.ArrayList;

public final class RecipePresenter extends MvpPresenter<PlaceholderFragment> {

    ContentInteractor interactor;


    protected RecipePresenter(ContentInteractor interactor) {
        this.interactor = interactor;
    }

    @Override
    public void onViewReady() {
        loadRecipe();
    }

    private void loadRecipe() {

        interactor.loadRecipe( new Carry<ArrayList<Product>>() {

            @Override
            public void onSuccess(ArrayList<Product> result) {
                view.showRecipe(result);
            }

            @Override
            public void onFailure(Throwable throwable) {
                 view.showError(throwable.getMessage());
            }
        });
    }

    public void recipeChangeProduct() {
        interactor.changeProduct(new Carry<ArrayList<Food>>() {

            @Override
            public void onSuccess(ArrayList<Food> result) {
                view.showRecipeSpiner(result);
            }

            @Override
            public void onFailure(Throwable throwable) {
                view.showError(throwable.getMessage());
            }
        });
    }


   /* public void onProductClickDelete(Product product){
        interactor.deleteProduct(product.getId(), new Carry<Success>() {

            @Override
            public void onSuccess(Success result) {
                loadRecipe();
            }

            @Override
            public void onFailure(Throwable throwable) {
                view.showError(throwable.getMessage());
            }
        });
    }*/

    private void contentRecipe(){
    }

    public void recipeAddProduct(final int numberProduct) {
        interactor.changeProduct(new Carry<ArrayList<Food>>() {
            @Override
            public void onSuccess(ArrayList<Food> result) {
                Product product = new Product(result.get(numberProduct).getId(), result.get(numberProduct). getName());
                interactor.recipeAddProduct(product,new Carry<Success>() {
                    @Override
                    public void onSuccess(Success result) {
                        loadRecipe();
                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        view.showError(throwable.getMessage());
                    }
                });
            }

            @Override
            public void onFailure(Throwable throwable) {
                view.showError(throwable.getMessage());
            }
        });
    }
}
