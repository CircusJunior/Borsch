package com.borsch.shift.cft.features.account.data;

import com.borsch.shift.cft.features.account.domain.model.Account;
import com.borsch.shift.cft.features.borsch.domain.model.Success;
import com.borsch.shift.cft.features.account.domain.model.UserLogin;
import com.borsch.shift.cft.features.account.domain.model.UserValidInfo;
import com.borsch.shift.cft.features.borsch.domain.model.Wrapper;
import com.borsch.shift.cft.network.Carry;
import com.borsch.shift.cft.network.DefaultCallback;

public final class AccountDataSourceImpl implements AccountDataSource{

    private AccountApi accountApi;
    private Account accountCarry;

    public AccountDataSourceImpl(AccountApi accountApi) {
        this.accountApi = accountApi;
    }

    @Override
    public void getAccount(UserValidInfo userValidInfo, final Carry<Account> carry) {
        accountApi.getAccount(userValidInfo.getToken(), userValidInfo.getUser(), userValidInfo.getId(), userValidInfo.getId()).enqueue(new DefaultCallback(carry));
        //accountCarry = new Account("Petr", "Petrov", "Moscow", "MSU", "",
          //      "","", "", "");
        //carry.onSuccess(accountCarry);
    }

    @Override
    public void createAccount(UserValidInfo userValidInfo, Account account, Carry<Success> carry) {
        accountApi.createAccount(userValidInfo.getToken(), userValidInfo.getUser(), userValidInfo.getId(), account).enqueue(new DefaultCallback(carry));
    }

    @Override
    public void closeAccount(UserValidInfo userValidInfo, Carry<Success> carry) {
        accountApi.closeAccount(userValidInfo.getToken(),userValidInfo.getId()).enqueue(new DefaultCallback(carry));
    }

    @Override
    public void checkLogIn(UserLogin userLogin, Carry<UserValidInfo> carry) {
        //accountApi.checkLogIn(userLogin).enqueue(new DefaultCallback(carry));
        carry.onSuccess(new UserValidInfo("000000", "000000", "000000"));
    }

    @Override
    public void setRegistration(UserLogin userLogin, Carry<Success> carry) {
        accountApi.setRegistration(userLogin).enqueue(new DefaultCallback(carry));
    }
}
