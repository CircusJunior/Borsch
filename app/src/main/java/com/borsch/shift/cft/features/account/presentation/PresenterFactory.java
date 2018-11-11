package com.borsch.shift.cft.features.account.presentation;

import android.content.Context;

import com.borsch.shift.cft.App;
import com.borsch.shift.cft.features.account.data.AccountApi;
import com.borsch.shift.cft.features.account.data.AccountDataSource;
import com.borsch.shift.cft.features.account.data.AccountDataSourceImpl;
import com.borsch.shift.cft.features.account.data.AccountRepository;
import com.borsch.shift.cft.features.account.data.AccountRepositoryImpl;
import com.borsch.shift.cft.features.account.data.UsersLocalDataSource;
import com.borsch.shift.cft.features.account.data.UsersLocalDataSourceImpl;
import com.borsch.shift.cft.features.account.data.UsersLocalRepository;
import com.borsch.shift.cft.features.account.data.UsersLocalRepositoryImpl;
import com.borsch.shift.cft.features.account.domain.AccountInteractor;
import com.borsch.shift.cft.features.account.domain.AccountInteractorImpl;

public final class PresenterFactory {
    static AccountPresenter createPresenter(Context context) {
        final AccountApi api = App.getRetrofitProvider(context)
                .getRetrofit()
                .create(AccountApi.class);

        final UsersLocalDataSource dataSourceLocal = new UsersLocalDataSourceImpl(context);
        final UsersLocalRepository repositoryLocal = new UsersLocalRepositoryImpl(dataSourceLocal);

        final AccountDataSource dataSource = new AccountDataSourceImpl(api);
        final AccountRepository repository = new AccountRepositoryImpl(dataSource);
        final AccountInteractor interactor = new AccountInteractorImpl(repository, repositoryLocal);

        return new AccountPresenter(interactor);
    }
}
