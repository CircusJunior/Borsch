package com.borsch.shift.cft.features.account.domain;

import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import com.borsch.shift.cft.features.account.data.AccountRepository;
import com.borsch.shift.cft.features.account.data.UsersLocalRepository;
import com.borsch.shift.cft.features.account.domain.model.Account;
import com.borsch.shift.cft.features.borsch.domain.model.Success;
import com.borsch.shift.cft.features.account.domain.model.UserLogin;
import com.borsch.shift.cft.features.account.domain.model.UserValidInfo;
import com.borsch.shift.cft.network.Carry;

public final class AccountInteractorImpl implements AccountInteractor{

    private final AccountRepository repository;
    private final UsersLocalRepository repositoryLocal;

    public AccountInteractorImpl(AccountRepository repository, UsersLocalRepository repositoryLocal) {
        this.repository = repository;
        this.repositoryLocal = repositoryLocal;
    }

    @Override
    public void loadAccount(UserValidInfo userValidInfo, Carry<Account> carry) {
        repositoryLocal.setUserToken(userValidInfo.getToken());
        repositoryLocal.setUser(userValidInfo.getUser());
        repositoryLocal.setId(userValidInfo.getId());
        repository.loadAccount(userValidInfo, carry);
    }

    @Override
    public void loadAccount(String id, Carry<Account> carry) {
        repository.loadAccount(id, carry);
    }

    @Override
    public void createAccount(UserValidInfo userValidInfo, Account account, Carry<Success> carry) {
        repository.createAccount(userValidInfo, account, carry);
    }


    @Override
    public void checkLogIn(UserLogin userLogin,  Carry<UserValidInfo> carry) {

        repository.checkLogIn(userLogin, carry);
    }

    @Override
    public void closeAccount(UserValidInfo userValidInfo, Carry<Success> carry) {
        repository.closeAccount(userValidInfo, carry);
    }

    @Override
    public void setRegistration(UserLogin userLogin, Carry<Success> carry) {
        repository.setRegistration(userLogin, carry);
    }
}
