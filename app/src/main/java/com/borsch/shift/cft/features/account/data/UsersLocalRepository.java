package com.borsch.shift.cft.features.account.data;

import com.borsch.shift.cft.features.account.domain.model.UserValidInfo;

public interface UsersLocalRepository {
    String getUserToken();
    void setUserToken(String token);

    String getUser();
    void setUser(String user);

    String getId();
    void setId(String id);
}
