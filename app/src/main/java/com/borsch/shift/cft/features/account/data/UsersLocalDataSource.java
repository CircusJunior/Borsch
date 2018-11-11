package com.borsch.shift.cft.features.account.data;

import com.borsch.shift.cft.features.account.domain.model.UserValidInfo;

public interface UsersLocalDataSource {

    String getString(final String key);

    void putString(final String key, final String value);

    String getUser(final String key);

    void putUser(final String key, String user);

    void putId(final String key, String id);

    String getId(final String key);
}