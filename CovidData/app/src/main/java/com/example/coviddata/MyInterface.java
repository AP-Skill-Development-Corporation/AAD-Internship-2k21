package com.example.coviddata;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MyInterface {
    @GET("dayone/country/{input}")
    Call<String> getValue(@Path("input") String i);

}
