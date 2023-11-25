package com.example.mvvmwithtexttospeechword.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class AllWordModel {

    @SerializedName("words")
    List<WordModel> words;


    public void setWords(List<WordModel> words) {
        this.words = words;
    }
    public  List<WordModel> getWords() {
        return words;
    }
}
