package com.borsch.shift.cft.features.borsch.presentation;


import com.borsch.shift.cft.features.MvpPresenter;
import com.borsch.shift.cft.features.borsch.domain.ContentInteractor;
import com.borsch.shift.cft.features.borsch.domain.model.Food;
import com.borsch.shift.cft.features.borsch.domain.model.Request;
import com.borsch.shift.cft.features.borsch.domain.model.Success;
import com.borsch.shift.cft.network.Carry;

import java.util.ArrayList;

public final class RequestPresenter extends MvpPresenter<PlaceholderFragment> {

    ContentInteractor interactor;


    protected RequestPresenter(ContentInteractor interactor) {
        this.interactor = interactor;
    }

    @Override
    public void onViewReady() {
        loadRequest();
    }

    private void loadRequest() {

        interactor.loadRequest( new Carry<ArrayList<Request>>() {

            @Override
            public void onSuccess(ArrayList<Request> result) {
                view.showRequest(result);
            }

            @Override
            public void onFailure(Throwable throwable) {
                 view.showError(throwable.getMessage());
            }

        });

    }

    public void onRequestClickDelete(Request request){
        Request requests = new Request(request.getId());

        interactor.deleteRequest(requests, new Carry<Success>() {

            @Override
            public void onSuccess(Success result) {
                loadRequest();
            }

            @Override
            public void onFailure(Throwable throwable) {
                view.showError(throwable.getMessage());
            }
        });
    }

    public void requestChangeRequest() {
        interactor.changeRequest(new Carry<ArrayList<Food>>() {

            @Override
            public void onSuccess(ArrayList<Food> result) {
                view.showRequestSpiner(result);
            }

            @Override
            public void onFailure(Throwable throwable) {
                view.showError(throwable.getMessage());
            }
        });
    }

    public void requestAddRequest(final int numberRequest) {
        interactor.changeRequest(new Carry<ArrayList<Food>>() {
            @Override
            public void onSuccess(ArrayList<Food> result) {
                Request request = new Request(result.get(numberRequest).getId(), result.get(numberRequest). getName());
                interactor.requestAddRequest(request,new Carry<Success>() {
                    @Override
                    public void onSuccess(Success result) {
                        loadRequest();
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
