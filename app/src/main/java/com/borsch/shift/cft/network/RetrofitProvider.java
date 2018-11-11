package com.borsch.shift.cft.network;

import com.borsch.shift.cft.features.account.data.UsersLocalRepository;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class RetrofitProvider {

    private static final String BASE_URL = "http://520e8918.ngrok.io/api/v1/";

    private final Retrofit retrofit;

    public RetrofitProvider(final UsersLocalRepository repository) {


        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .client(createClient(repository))
                .build();
    }

    private OkHttpClient createClient(final UsersLocalRepository repository) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor();
        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        builder.addInterceptor(logInterceptor);

        builder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                HttpUrl originalHttpUrl = original.url();


                HttpUrl url = originalHttpUrl.newBuilder()
//                        .addQueryParameter("Token", repository.getUserToken())
//                        .addQueryParameter("Login", repository.getUser())
//                        .addQueryParameter("idUser", repository.getId())
                        //.addEncodedPathSegments("/") // TODO: remove it for production api
                        .build();

                // Request customization: add request headers
                Request.Builder requestBuilder = original.newBuilder()
                        .header("Token", repository.getUserToken())
                        .header("Login", repository.getUser())
                        .header("idUser", repository.getId())
                        .url(url);

                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });

        return builder.build();
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }
}