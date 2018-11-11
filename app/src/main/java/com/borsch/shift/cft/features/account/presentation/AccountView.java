package com.borsch.shift.cft.features.account.presentation;

import com.borsch.shift.cft.features.MvpView;
import com.borsch.shift.cft.features.account.domain.model.Account;
import com.borsch.shift.cft.features.account.domain.model.UserValidInfo;

public interface AccountView extends MvpView {

    void showAccount(Account account);

    void showError(String message);

    void hideActivity(String active, UserValidInfo user);

    void exitActivity();
}
