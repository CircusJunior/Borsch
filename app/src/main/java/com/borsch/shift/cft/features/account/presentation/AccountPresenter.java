package com.borsch.shift.cft.features.account.presentation;

import android.content.SharedPreferences;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import com.borsch.shift.cft.features.MvpPresenter;
import com.borsch.shift.cft.features.account.data.UsersLocalRepository;
import com.borsch.shift.cft.features.account.domain.AccountInteractor;
import com.borsch.shift.cft.features.account.domain.model.Account;
import com.borsch.shift.cft.features.account.domain.model.UserLogin;
import com.borsch.shift.cft.features.borsch.domain.model.Success;
import com.borsch.shift.cft.features.account.domain.model.UserValidInfo;
import com.borsch.shift.cft.network.Carry;

public final class AccountPresenter extends MvpPresenter<AccountView> {

    private final AccountInteractor interactor;
   // private final UsersLocalRepository repositorySource;


    AccountPresenter(AccountInteractor interactor) {
        this.interactor = interactor;
    }

    @Override
    protected void onViewReady(UserValidInfo userValidInfo) {
        loadAccount(userValidInfo);
    }

    private void loadAccount(UserValidInfo userValidInfo) {
        interactor.loadAccount(userValidInfo, new Carry<Account>() {

            @Override
            public void onSuccess(Account result) {
                view.showAccount(result);
            }

            @Override
            public void onFailure(Throwable throwable) {
                view.showError(throwable.getMessage());
            }

        });
    }

    public void onExitClicked(UserValidInfo userValidInfo){
        interactor.closeAccount(userValidInfo, new Carry<Success>() {

            @Override
            public void onSuccess(Success result) {
                view.exitActivity();
            }

            @Override
            public void onFailure(Throwable throwable) {
                view.showError(throwable.getMessage());
            }
        });
    }

    public void setRegistration(final AutoCompleteTextView mEmailView, final EditText mPasswordView, final SharedPreferences mSettings,
                                final String APP_PREFERENCES_NAME, final String APP_PREFERENCES_TOKEN){
        UserLogin userLogin = new UserLogin (mEmailView.getText().toString(), mPasswordView.getText().toString());
        interactor.setRegistration(userLogin, new Carry<Success>() {
            @Override
            public void onSuccess(Success result) {
                checkLogIn(mEmailView, mPasswordView, mSettings, APP_PREFERENCES_NAME,APP_PREFERENCES_TOKEN);
            }

            @Override
            public void onFailure(Throwable throwable) {
                view.showError(throwable.getMessage());
            }
        });
    }

    public void checkLogIn(AutoCompleteTextView mEmailView, EditText mPasswordView, final SharedPreferences mSettings,
                           final String APP_PREFERENCES_NAME, final String APP_PREFERENCES_TOKEN) {
        final UserLogin userLogin = new UserLogin (mEmailView.getText().toString(), mPasswordView.getText().toString());

        interactor.checkLogIn(userLogin, new Carry<UserValidInfo>() {

            @Override
            public void onSuccess(UserValidInfo result) {
                view.hideActivity("Account", result);
            }

            @Override
            public void onFailure(Throwable throwable) {
                view.showError(throwable.getMessage());
            }
        });
    }

    public void onCloseClicked(UserValidInfo userValidInfo) {
        view.hideActivity("Account", userValidInfo);

    }

    public void onEditClick(UserValidInfo userValidInfo) {
        view.hideActivity("Edit", userValidInfo);
    }



    public void onCreateAccountClicked(final UserValidInfo userValidInfo,
                                       EditText editFirstName,
                                       EditText editSecondName, EditText editCity,
                                       EditText editUniversity, EditText editDormitory,
                                       EditText editRoom, EditText editVkontakte,
                                       EditText editTelegram, EditText editEmail) {

        Account account = new Account( editFirstName.getText().toString(),
                editSecondName.getText().toString(),
                editCity.getText().toString(), editUniversity.getText().toString(), editDormitory.getText().toString(),
                editRoom.getText().toString(), editVkontakte.getText().toString(), editTelegram.getText().toString(), editEmail.getText().toString());

        interactor.createAccount(userValidInfo, account, new Carry<Success>() {
            @Override
            public void onSuccess(Success result) {
                view.hideActivity("Account", userValidInfo);
            }

            @Override
            public void onFailure(Throwable throwable) {
                view.showError(throwable.getMessage());
            }
        });
    }


}