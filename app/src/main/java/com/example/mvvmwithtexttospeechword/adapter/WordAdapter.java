package com.example.mvvmwithtexttospeechword.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvmwithtexttospeechword.R;
import com.example.mvvmwithtexttospeechword.model.WordModel;

import java.util.List;
import java.util.Locale;

public class WordAdapter extends RecyclerView.Adapter<WordAdapter.MyViewHolder> implements TextToSpeech.OnInitListener {

    private Context context;
    private List<WordModel> dataItems;
    private TextToSpeech textToSpeech;
    private float speechRate = 0.5f;



    public WordAdapter(Context context, List<WordModel> dataItems) {
        this.dataItems = dataItems;
        textToSpeech = new TextToSpeech(context, this);
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        WordModel dataItem = dataItems.get(position);
        holder.textView.setText(dataItem.getEnglish());
        holder.txtBangla.setText(dataItems.get(position).getBengali());


        holder.startButton.setOnClickListener(view -> {
            String textToSpeak = dataItem.getEnglish();
            textToSpeech.speak(textToSpeak, TextToSpeech.QUEUE_FLUSH, null, null);
            setSpeechRate(speechRate);
        });

    }
    public void setSpeechRate(float rate) {
        this.speechRate = rate;
        if (textToSpeech != null) {
            textToSpeech.setSpeechRate(rate);
        }
    }

    public void setModelList(List<WordModel> personModels) {
        this.dataItems = personModels;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return dataItems.size();
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            int result = textToSpeech.setLanguage(Locale.US);

            if (result == TextToSpeech.LANG_MISSING_DATA ||
                    result == TextToSpeech.LANG_NOT_SUPPORTED) {
                // Handle the case where the language is not available or not supported.
            }
        }
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView, txtBangla;
        ImageButton startButton;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
            txtBangla = itemView.findViewById(R.id.txtBangla);
            startButton = itemView.findViewById(R.id.startButton);
        }
    }
}
