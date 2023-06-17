package com.example.smart_words_app.retrofit;;

import com.example.smart_words_app.service.ICollection;
import com.example.smart_words_app.service.IWord;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RetrofitInitializer {
    private final Retrofit retrofit;

    public RetrofitInitializer() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        retrofit = new Retrofit.Builder().baseUrl("https://api-smart-words.onrender.com/api/")
                .addConverterFactory(JacksonConverterFactory.create(getObjectMapper()))
                .client(client)
                .build();

    }

    private ObjectMapper getObjectMapper() {
        return new ObjectMapper()
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                .setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
    }

    public ICollection serviceCollections()     {
        return retrofit.create(ICollection.class);
    }

    public IWord serviceWord()     {
        return retrofit.create(IWord.class);
    }

}



