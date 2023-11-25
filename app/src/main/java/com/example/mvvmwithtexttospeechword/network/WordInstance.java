package com.example.mvvmwithtexttospeechword.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WordInstance {

    //https://screwy-chair.000webhostapp.com/english_to_bangla_word/a_with_word.JSON

    public static String baseUrl = "https://screwy-chair.000webhostapp.com/english_to_bangla_word/";

    private static Retrofit retrofit;

    public static Retrofit getWord() {
        if (retrofit == null){

            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
