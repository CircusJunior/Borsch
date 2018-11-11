package com.borsch.shift.cft.features.account.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.borsch.shift.cft.R;
import com.borsch.shift.cft.features.account.domain.model.UserValidInfo;
import com.google.gson.Gson;

public final class UsersLocalDataSourceImpl implements UsersLocalDataSource {

    private SharedPreferences sharedPreferences;

    public UsersLocalDataSourceImpl(Context context) {
        sharedPreferences = context.getSharedPreferences(context.getString(R.string.user_settings_key),
                Context.MODE_PRIVATE);
    }

    @Override
    public String getString(String key) {
        return sharedPreferences.getString(key, "");
    }

    @Override
    public void putString(String key, String value) {
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();

        prefsEditor.putString(key, value);
        prefsEditor.apply();
    }

    @Override
    public void putUser(final String key, String user) {
        Gson gson = new Gson();
        String json = gson.toJson(user);
        putString(key, json);
    }

    @Override
    public void putId(String key, String id) {
        Gson gson = new Gson();
        String json = gson.toJson(id);
        putString(key, json);
    }

    @Override
    public String getId(String key) {
        Gson gson = new Gson();
        String json = getString(key);
        String idUser = gson.fromJson(json, String.class);
        return idUser;
    }

    @Override
    public String getUser(final String key) {
        Gson gson = new Gson();
        String json = getString(key);
        String user = gson.fromJson(json, String.class);
        return user;
    }
}
