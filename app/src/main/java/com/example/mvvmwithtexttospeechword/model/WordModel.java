package com.example.mvvmwithtexttospeechword.model;

import com.google.gson.annotations.SerializedName;

public class WordModel {


        @SerializedName("english")
        String english;

        @SerializedName("bengali")
        String bengali;

        public void setEnglish(String english) {
            this.english = english;
        }
        public String getEnglish() {
            return english;
        }

        public void setBengali(String bengali) {
            this.bengali = bengali;
        }
        public String getBengali() {
            return bengali;
        }


}
