package com.yesandroid.makeup;

import com.google.gson.JsonArray;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Get_Interface {

    @GET("api/v1/products.json?brand=maybelline")
    Call<JsonArray> getsocial();
}

