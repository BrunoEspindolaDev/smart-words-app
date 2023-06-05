package com.example.smart_words_app.service;

import com.example.smart_words_app.model.Collection;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ICollection {

    @GET("collections")
    Call<Collection> getCollections();

}
