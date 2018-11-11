package com.borsch.shift.cft.features.account.data;

import android.content.Context;

    public final class  UserLocalRepositoryFactory{
        public static UsersLocalRepository create(final Context context){
            final UsersLocalDataSource dataSource = new UsersLocalDataSourceImpl(context);
            return new UsersLocalRepositoryImpl(dataSource);
        }
    }
