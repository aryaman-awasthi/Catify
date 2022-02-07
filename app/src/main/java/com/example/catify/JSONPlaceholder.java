package com.example.catify;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface JSONPlaceholder {
    @Headers("x-api-key: 3f4f54e7-ab9e-4281-9856-52642722fd07")
    @GET("v1/breeds")
    Call<List<DataModel>> getData();
}
