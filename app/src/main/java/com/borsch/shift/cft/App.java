package com.borsch.shift.cft;

import android.app.Application;
import android.content.Context;

import com.borsch.shift.cft.features.account.data.UserLocalRepositoryFactory;
import com.borsch.shift.cft.network.RetrofitProvider;

/**
 * Created: samokryl
 * Date: 01.07.18
 * Time: 22:48
 */

public final class App extends Application {

    private RetrofitProvider retrofitProvider;

    public static RetrofitProvider getRetrofitProvider(Context context) {
        return getApp(context).retrofitProvider;
    }

    private static App getApp(Context context) {
        return (App) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        retrofitProvider = new RetrofitProvider(UserLocalRepositoryFactory.create(this));
    }
}