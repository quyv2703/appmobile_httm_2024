package com.ltq27.baotrimaylanh.retrofit2;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitClient {
    
    //private static final String BASE_URL = "http://192.168.2.11:8080/btml/api/";
    // ip lab 2b13
    private static final String BASE_URL = "http://192.168.1.7:8080/btml/api/";
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}