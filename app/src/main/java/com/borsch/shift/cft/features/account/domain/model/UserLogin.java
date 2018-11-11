package com.borsch.shift.cft.features.account.domain.model;

public final class UserLogin {
    private String userName;
    private String password;

    public UserLogin(String userName, String password){
        this.userName = userName;
        this.password = password;
    }

    public String getUserName(){return this.userName;}
}
