package com.example.smart_words_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.smart_words_app.adapter.WordAdapter;
import com.example.smart_words_app.model.Word;
import com.example.smart_words_app.model.WordAttributes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class CollectionActivity extends AppCompatActivity {

    private Context context;
    private TextToSpeech textToSpeech;
    private RecyclerView recyclerView;
    private WordAdapter wordAdapter;
    private List<Word> wordList;
    private Handler handler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);
        initializeTextToSpeech();

        String collectionName = getIntent().getStringExtra("collectionName");
        context = this;

        ImageButton startButton = findViewById(R.id.cancelButton);
        startButton.setOnClickListener((c) -> {
            Intent intent = new Intent(context, QuizActivity.class);
            intent.putExtra("collectionName", collectionName);
            context.startActivity(intent);
        });

        recyclerView = findViewById(R.id.recycleViewWords);
        wordList = new ArrayList<>();
        wordAdapter = new WordAdapter(context, wordList, textToSpeech);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(wordAdapter);

        TextView title = findViewById(R.id.title);
        TextView nWords = findViewById(R.id.nWords);

        loadData();

        title.setText(collectionName);
        nWords.setText(wordList.size() + " Palavras");
    }

    private void initializeTextToSpeech() {
        textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    Locale locale = Locale.US;
                    textToSpeech.setLanguage(locale);
                }
            }
        });
    }

    private void loadData() {

        Word word1 = new Word("1", new WordAttributes("Bed", "Cama", "06/06/2023", "06/06/2023", "06/06/2023"));
        Word word2 = new Word("2", new WordAttributes("Bedside Table", "Mesa de cabeceira", "06/06/2023", "06/06/2023", "06/06/2023"));
        Word word3 = new Word("3", new WordAttributes("Wardrobe", "Guarda-roupa", "06/06/2023", "06/06/2023", "06/06/2023"));
        Word word4 = new Word("4", new WordAttributes("Lamp", "Luminária", "06/06/2023", "06/06/2023", "06/06/2023"));
        Word word5 = new Word("5", new WordAttributes("Nightstand", "Criado-mudo", "06/06/2023", "06/06/2023", "06/06/2023"));

        wordList.add(word1);
        wordList.add(word2);
        wordList.add(word3);
        wordList.add(word4);
        wordList.add(word5);

        wordAdapter.notifyDataSetChanged();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
    }
}