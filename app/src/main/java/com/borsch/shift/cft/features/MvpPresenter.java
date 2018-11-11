package com.borsch.shift.cft.features;

import com.borsch.shift.cft.features.account.domain.model.UserValidInfo;

/**
 * Created: samokryl
 * Date: 02.07.18
 * Time: 0:07
 */

public class MvpPresenter<View extends MvpView> {

    protected View view;

    public void attachView(View view) {
        this.view = view;

    }

    public void detachView() {
        view = null;
    }

    protected void onViewReady(UserValidInfo userValidInfo) {
        // override if need
    }

    protected void onViewReady() {
        // override if need
    }
}
