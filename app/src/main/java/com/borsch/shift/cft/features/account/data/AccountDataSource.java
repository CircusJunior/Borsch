package com.borsch.shift.cft.features.account.data;

import com.borsch.shift.cft.features.account.domain.model.Account;
import com.borsch.shift.cft.features.borsch.domain.model.Success;
import com.borsch.shift.cft.features.account.domain.model.UserLogin;
import com.borsch.shift.cft.features.account.domain.model.UserValidInfo;
import com.borsch.shift.cft.features.borsch.domain.model.Wrapper;
import com.borsch.shift.cft.network.Carry;

public interface AccountDataSource {

    void getAccount(UserValidInfo userValidInfo, Carry<Account> carry);

    void createAccount(UserValidInfo userValidInfo, Account account, Carry<Success> carry);

    void closeAccount(UserValidInfo userValidInfo, Carry<Success> carry);

    void checkLogIn(UserLogin userLogin, Carry<UserValidInfo> carry);

    void setRegistration(UserLogin userLogin, Carry<Success> carry);
}
