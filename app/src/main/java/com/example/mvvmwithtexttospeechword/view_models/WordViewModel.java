package com.example.mvvmwithtexttospeechword.view_models;


import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mvvmwithtexttospeechword.model.WordModel;

import java.util.List;

public class WordViewModel extends ViewModel {

    private WordRepo wordRepo;
    private MutableLiveData<List<WordModel>> mutableLiveData;

    public WordViewModel() {
        wordRepo = new WordRepo();
    }

    public MutableLiveData<List<WordModel>> getMutableLiveData() {
        if (mutableLiveData == null){
                mutableLiveData = wordRepo.requestWord();
        }
        return mutableLiveData;
    }
}
