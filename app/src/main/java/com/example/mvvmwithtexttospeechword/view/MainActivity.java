package com.example.mvvmwithtexttospeechword.view;

import  androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.mvvmwithtexttospeechword.R;
import com.example.mvvmwithtexttospeechword.adapter.WordAdapter;
import com.example.mvvmwithtexttospeechword.model.WordModel;
import com.example.mvvmwithtexttospeechword.view_models.WordViewModel;

import java.text.Collator;
import java.text.RuleBasedCollator;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity{

    RecyclerView recyclerView;
    WordAdapter adapter;
    WordViewModel wordViewModel;
    List<WordModel> modelList;

    private SearchView searchView;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar);
        searchView = findViewById(R.id.searchView);
        searchView.clearFocus();

        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new WordAdapter(this, modelList);
        recyclerView.setAdapter(adapter);

        wordViewModel = new WordViewModel();

        wordViewModel.getMutableLiveData().observe(this, new Observer<List<WordModel>>() {
            @Override
            public void onChanged(List<WordModel> wordModels) {

                Log.d("error", wordModels.toString());
                if (wordModels.size()!=0){
                    progressBar.setVisibility(View.GONE);

                }
                adapter.setModelList((List<WordModel>) wordModels);
                adapter.notifyDataSetChanged();
                //Toast.makeText(MainActivity.this, personModels.get(0).getName(), Toast.LENGTH_SHORT).show();

            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }
        });

    }

    private void filterList(String query) {
        // Ensure that viewModel.getDataList() does not return null
        if (wordViewModel.getMutableLiveData() != null) {
            wordViewModel.getMutableLiveData().observe(this, newDataList -> {
                List<WordModel> filteredList = new ArrayList<>();
                if (newDataList != null) {
                    for (WordModel wordModel : newDataList) {
                        if (wordModel.getEnglish().toLowerCase().contains(query.toLowerCase())) {
                            filteredList.add(wordModel);
                        }
                    }
                    adapter.setModelList(filteredList);
                }
            });
        }
    }
}