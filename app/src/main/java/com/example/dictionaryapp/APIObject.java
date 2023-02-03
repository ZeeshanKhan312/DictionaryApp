package com.example.dictionaryapp;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIObject {
    private static Retrofit retrofit=null;  //creating Retrofit object

    public static ApiInterface getApiInterface(){

        if(retrofit==null) {
            retrofit = new Retrofit.Builder().baseUrl(ApiInterface.Base_URL).addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit.create(ApiInterface.class);
    }
}
