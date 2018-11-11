package com.borsch.shift.cft.features.account.domain.model;

import android.os.Parcel;
import android.os.Parcelable;

public final class UserValidInfo implements Parcelable {
    private String idUser;
    private String login;
    private String token;

    public UserValidInfo(String id, String user, String token){
        this.idUser = id;
        this.login = user;
        this.token = token;
    }

    public UserValidInfo(Parcel in) {
        String[] data = new String[3];
        in.readStringArray(data);
        idUser = data[0];
        login = data[1];
        token = data[2];
    }

    public String getToken() {
        return token;
    }

    public String getUser() {
        return login;
    }

    public String getId() {
        return idUser;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[] { idUser, login, token });
    }

    public static final Parcelable.Creator<UserValidInfo> CREATOR = new Parcelable.Creator<UserValidInfo>() {

        @Override
        public UserValidInfo createFromParcel(Parcel source) {
            return new UserValidInfo(source);
        }

        @Override
        public UserValidInfo[] newArray(int size) {
            return new UserValidInfo[size];
        }
    };


}
