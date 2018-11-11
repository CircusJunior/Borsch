package com.borsch.shift.cft.features.borsch.data;

import com.borsch.shift.cft.features.borsch.domain.model.Food;
import com.borsch.shift.cft.features.borsch.domain.model.Fridge;
import com.borsch.shift.cft.features.borsch.domain.model.Product;
import com.borsch.shift.cft.features.borsch.domain.model.Recipe;
import com.borsch.shift.cft.features.borsch.domain.model.Request;
import com.borsch.shift.cft.features.borsch.domain.model.Success;
import com.borsch.shift.cft.features.borsch.domain.model.Wrapper;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ContentApi {
    @GET("fridge")
    Call<Wrapper<ArrayList<Product>>> getFridge();

    @POST("fridge")
    Call<Wrapper<Fridge>> createFridge(@Body Fridge fridge);


    @HTTP(method = "DELETE", path = "fridge", hasBody = true)
    Call<Wrapper<Success>> deleteProduct(@Body Product id);

    @POST("fridge")
    Call<Wrapper<Fridge>> fridgeAddProduct(@Body Product product);

    @PUT("recipe/{id}")
    Call<Wrapper<Success>> recipeAddProduct(@Path("id") String id,
                                            @Body String foodIf, String foodName, String allWeight, String reservedWeight);

    @GET("food")
    Call<Wrapper<ArrayList<Food>>> changeProduct();

    @GET("recipes/{recipeId}")
    Call<Wrapper<ArrayList<Product>>> getRecipe(@Path ("recipeId") String id);

    @GET("recipes")
    Call<Wrapper<ArrayList<Product>>> getRecipe();


}
