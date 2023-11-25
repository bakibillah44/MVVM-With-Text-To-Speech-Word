package com.example.mvvmwithtexttospeechword.view_models;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.mvvmwithtexttospeechword.model.AllWordModel;
import com.example.mvvmwithtexttospeechword.model.WordModel;
import com.example.mvvmwithtexttospeechword.network.IWordAPI;
import com.example.mvvmwithtexttospeechword.network.WordInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WordRepo {
    private final String TAG = getClass().getSimpleName();
    List<WordModel> arrayList= new ArrayList<>();
    public MutableLiveData<List<WordModel>> requestWord() {
        final MutableLiveData<List<WordModel>> mutableLiveData = new MutableLiveData<>();
        mutableLiveData.setValue(arrayList);

        IWordAPI iWordAPI= WordInstance.getWord().create(IWordAPI.class);
        Call<AllWordModel> listCall = iWordAPI.getWordModel();

        listCall.enqueue(new Callback<AllWordModel>() {
            @Override
            public void onResponse(Call<AllWordModel> call, Response<AllWordModel> response) {
                if (response.isSuccessful()) {
                    // Handle successful response
                    arrayList.addAll(response.body().getWords());
                    mutableLiveData.setValue(arrayList);
                    Log.e(TAG, "WordSuccessful "+response.body().getWords().get(0).getEnglish());
                } else {
                    // Handle unsuccessful response
                    Log.e(TAG, "Error: " + response.code() + " " + response.message());
                }
            }

            @Override
            public void onFailure(Call<AllWordModel> call, Throwable t) {
                Log.d(TAG,"unsuccessful " + t.toString());

            }
        });

        return mutableLiveData;
    }
}
