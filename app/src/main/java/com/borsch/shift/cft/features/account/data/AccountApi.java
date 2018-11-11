package com.borsch.shift.cft.features.account.data;

import com.borsch.shift.cft.features.account.domain.model.Account;
import com.borsch.shift.cft.features.borsch.domain.model.Success;
import com.borsch.shift.cft.features.account.domain.model.UserLogin;
import com.borsch.shift.cft.features.account.domain.model.UserValidInfo;
import com.borsch.shift.cft.features.borsch.domain.model.Wrapper;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface AccountApi {

    @POST("users/login")
    Call<Wrapper<UserValidInfo>> checkLogIn(@Body UserLogin userLogin);

    @POST("users/registration")
    Call<Wrapper<Success>> setRegistration(@Body UserLogin userLogin);

    @GET("users/logout/")
    Call<Wrapper<Success>> closeAccount(@Header ("Token")String token,
                                        @Header("Login") String login);

    @GET("users/{userId}")
    Call<Wrapper<Account>> getAccount(@Header ("Token")String token,
                                      @Header("Login") String login,
                                      @Header("idUser") String id,
                                      @Path ("userId") String userId);

    @PATCH("users")
    Call<Wrapper<Success>> createAccount(@Header ("Token")String token,
                                         @Header("Login") String login,
                                         @Header("idUser") String id,
                                         @Body Account account);

}
