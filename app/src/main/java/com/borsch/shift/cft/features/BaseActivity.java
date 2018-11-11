package com.borsch.shift.cft.features;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.borsch.shift.cft.features.account.presentation.AccountView;
import com.borsch.shift.cft.features.borsch.presentation.ContentPresenter;
import com.borsch.shift.cft.features.borsch.presentation.ContentView;

/**
 * Created: samokryl
 * Date: 01.07.18
 * Time: 22:49
 */

public abstract class BaseActivity extends AppCompatActivity {

    protected abstract <T extends MvpView> MvpPresenter<T> getPresenter();
    protected abstract <T extends MvpView> ContentPresenter getContentPresenter();

    protected abstract <T extends MvpView> AccountView getMvpView();
    protected abstract <T extends MvpView> ContentView getContentMvpView();

    private MvpPresenter<MvpView> presenter;
    private MvpPresenter<MvpView> contentPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = getPresenter();
        //contentPresenter = getContentPresenter();
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.attachView(getMvpView());
       // contentPresenter.attachView(getContentMvpView());
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.detachView();
        //contentPresenter.detachView();
    }

}