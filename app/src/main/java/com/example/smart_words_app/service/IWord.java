package com.example.smart_words_app.service;
import com.example.smart_words_app.model.WordResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IWord {
    @GET("words")
    Call<WordResponse> getWords(@Query("filters[collection][id][$eq]=") int collectionId);
}
