package com.example.dictionaryapp;

import com.example.dictionaryapp.Models.DictionaryModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    String Base_URL="https://api.dictionaryapi.dev/api/v2/entries/";

    @GET("en/{word}")
    Call<ArrayList<DictionaryModel>> searchWord(
            @Path("word") String word
    );
}
