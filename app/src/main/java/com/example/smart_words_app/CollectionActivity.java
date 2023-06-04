package com.example.smart_words_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.example.smart_words_app.adapter.CollectionAdapter;
import com.example.smart_words_app.adapter.WordAdapter;
import com.example.smart_words_app.model.Collection;
import com.example.smart_words_app.model.CollectionAttributes;
import com.example.smart_words_app.model.Word;
import com.example.smart_words_app.model.WordAttributes;

import java.util.ArrayList;
import java.util.List;

public class CollectionActivity extends AppCompatActivity {

    private Context context;

    private RecyclerView recyclerView;
    private WordAdapter wordAdapter;
    private List<Word> wordList;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);

        Intent intent = getIntent();
        String collectionName = intent.getStringExtra("collectionName");

        context = this;

        TextView title = findViewById(R.id.title);
        title.setText(collectionName);

        recyclerView = findViewById(R.id.recycleViewWords);
        wordList = new ArrayList<>();
        wordAdapter = new WordAdapter(context, wordList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(wordAdapter);

        loadData();
    }

    private void loadData() {
        Word word1 = new Word("1", new WordAttributes("Batata", "Potato", "06/06/2023", "06/06/2023", "06/06/2023"));

        wordList.add(word1);
        wordList.add(word1);
        wordList.add(word1);
        wordList.add(word1);
        wordList.add(word1);
        wordList.add(word1);
        wordList.add(word1);
        wordList.add(word1);
        wordList.add(word1);
        wordList.add(word1);
        wordList.add(word1);
        wordList.add(word1);
        wordList.add(word1);
        wordList.add(word1);
        wordList.add(word1);
        wordList.add(word1);
        wordList.add(word1);
        wordList.add(word1);

        wordAdapter.notifyDataSetChanged();
    }
}