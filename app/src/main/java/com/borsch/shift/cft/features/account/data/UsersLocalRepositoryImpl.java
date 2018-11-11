package com.borsch.shift.cft.features.account.data;


public final class UsersLocalRepositoryImpl implements UsersLocalRepository {
    private final UsersLocalDataSource dataSource;

    public UsersLocalRepositoryImpl(UsersLocalDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public String getUserToken() {
        String str = dataSource.getString("Token");
        return dataSource.getString("Token");
    }

    @Override
    public String getUser() {
        //return dataSource.getUser("User");
        return dataSource.getString("Login");
    }

    @Override
    public void setUser(String user) {
     //   dataSource.putUser("Login", user);
        dataSource.putString("Login", user);
    }

    @Override
    public String getId() {
        //return dataSource.getId("idUser");
        return dataSource.getString("idUser");
    }

    @Override
    public void setId(String id) {
       // dataSource.putId("idUser", id);
        dataSource.putString("idUser", id);
    }

    @Override
    public void setUserToken(String token) {
        dataSource.putString("Token", token);
    }
}
