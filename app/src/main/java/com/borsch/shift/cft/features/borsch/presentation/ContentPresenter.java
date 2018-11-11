package com.borsch.shift.cft.features.borsch.presentation;


import android.app.Fragment;

import com.borsch.shift.cft.features.MvpPresenter;
import com.borsch.shift.cft.features.borsch.domain.ContentInteractor;
import com.borsch.shift.cft.features.borsch.domain.model.ChangeProduct;
import com.borsch.shift.cft.features.borsch.domain.model.Food;
import com.borsch.shift.cft.features.borsch.domain.model.Fridge;
import com.borsch.shift.cft.features.borsch.domain.model.Product;
import com.borsch.shift.cft.features.borsch.domain.model.Success;
import com.borsch.shift.cft.network.Carry;

import java.util.ArrayList;

public final class ContentPresenter extends MvpPresenter<PlaceholderFragment> {

    ContentInteractor interactor;


    protected ContentPresenter(ContentInteractor interactor) {
        this.interactor = interactor;
    }

    @Override
    public void onViewReady() {
        loadFridge();
    }

    private void loadFridge() {

        interactor.loadFridge( new Carry<ArrayList<Product>>() {

            @Override
            public void onSuccess(ArrayList<Product> result) {
                view.showFridge(result);
            }

            @Override
            public void onFailure(Throwable throwable) {
                 view.showError(throwable.getMessage());
            }

        });

    }

    public void onProductClickDelete(Product product){
        Product products = new Product(product.getId());

        interactor.deleteProduct(products, new Carry<Success>() {

            @Override
            public void onSuccess(Success result) {
                loadFridge();
            }

            @Override
            public void onFailure(Throwable throwable) {
                view.showError(throwable.getMessage());
            }
        });
    }

    public void fridgeChangeProduct() {
        interactor.changeProduct(new Carry<ArrayList<Food>>() {

            @Override
            public void onSuccess(ArrayList<Food> result) {
                view.showFridgeSpiner(result);
            }

            @Override
            public void onFailure(Throwable throwable) {
                view.showError(throwable.getMessage());
            }
        });
    }

    public void fridgeAddProduct(final int numberProduct) {
        interactor.changeProduct(new Carry<ArrayList<Food>>() {
            @Override
            public void onSuccess(ArrayList<Food> result) {
                Product product = new Product(result.get(numberProduct).getId(), result.get(numberProduct). getName());
                interactor.fridgeAddProduct(product,new Carry<Success>() {
                    @Override
                    public void onSuccess(Success result) {
                        loadFridge();
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
