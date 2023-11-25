package com.example.mvvmwithtexttospeechword.network;

import com.example.mvvmwithtexttospeechword.model.AllWordModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IWordAPI {


    @GET("a_with_word.JSON")
    Call<AllWordModel> getWordModel();
}
