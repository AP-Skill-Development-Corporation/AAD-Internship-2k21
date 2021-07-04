package com.example.exampleapplication;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MyInterface {
    @GET("search?term=mj")
    Call<String> value();
}
