package com.borsch.shift.cft.features.account.data;

import com.borsch.shift.cft.features.account.domain.model.Account;
import com.borsch.shift.cft.features.borsch.domain.model.Success;
import com.borsch.shift.cft.features.account.domain.model.UserLogin;
import com.borsch.shift.cft.features.account.domain.model.UserValidInfo;
import com.borsch.shift.cft.network.Carry;

public final class AccountRepositoryImpl implements AccountRepository{

    private final AccountDataSource dataSource;

    public AccountRepositoryImpl(AccountDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void loadAccount(UserValidInfo userValidInfo, Carry<Account> carry) {
        dataSource.getAccount(userValidInfo, carry);
    }

    @Override
    public void loadAccount(String id, Carry<Account> carry) {
        //dataSource.getAccount(id, carry);
    }

    @Override
    public void createAccount(UserValidInfo userValidInfo, Account account, Carry<Success> carry) {
        dataSource.createAccount(userValidInfo, account, carry);
    }

    @Override
    public void closeAccount(UserValidInfo userValidInfo, Carry<Success> carry) {
        dataSource.closeAccount(userValidInfo, carry);

    }

    @Override
    public void setRegistration(UserLogin userLogin, Carry<Success> carry) {
        dataSource.setRegistration(userLogin, carry);
    }

    @Override
    public void checkLogIn(UserLogin userLogin, Carry<UserValidInfo> carry) {
        dataSource.checkLogIn(userLogin, carry);
    }
}
