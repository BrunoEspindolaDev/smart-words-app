package com.example.smart_words_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smart_words_app.adapter.WordAdapter;
import com.example.smart_words_app.model.Collection;
import com.example.smart_words_app.model.CollectionResponse;
import com.example.smart_words_app.model.Word;
import com.example.smart_words_app.model.WordAttributes;
import com.example.smart_words_app.model.WordResponse;
import com.example.smart_words_app.retrofit.RetrofitInitializer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CollectionActivity extends AppCompatActivity {

    private Context context;
    private TextToSpeech textToSpeech;
    private RecyclerView recyclerView;
    private WordAdapter wordAdapter;
    private List<Word> wordList;
    private Handler handler = new Handler();

    private int collectionId;

    private ProgressBar progressBarWords;

    private TextView nWords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);
        initializeTextToSpeech();

        String collectionName = getIntent().getStringExtra("collectionName");
        collectionId = getIntent().getIntExtra("collectionId", 0);
        context = this;
        ImageButton startButton = findViewById(R.id.cancelButton);

        startButton.setOnClickListener((c) -> {
            Intent intent = new Intent(context, QuizActivity.class);
            intent.putExtra("collectionName", collectionName);
            intent.putExtra("collectionId", collectionId);
            context.startActivity(intent);
        });

        progressBarWords = findViewById(R.id.progressBarWords);
        recyclerView = findViewById(R.id.recycleViewWords);
        wordList = new ArrayList<>();
        wordAdapter = new WordAdapter(context, wordList, textToSpeech);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(wordAdapter);

        TextView title = findViewById(R.id.title);
        nWords = findViewById(R.id.nWords);
        nWords.setText("0 Palavras");


        loadData();

        title.setText(collectionName);

    }

    private void mapElements() {

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
        progressBarWords.setVisibility(View.VISIBLE);
        Call<WordResponse> call = new RetrofitInitializer().serviceWord().getWords(collectionId);

        call.enqueue(new Callback<WordResponse>() {
            @Override
            public void onResponse(Call<WordResponse> call, Response<WordResponse> response) {
                WordResponse wordResponse = response.body();

                if (wordResponse.getWordList().length > 0) {
                    for (Word word : wordResponse.getWordList()) {
                        wordList.add(word);
                        wordAdapter.notifyDataSetChanged();
                    }
                    progressBarWords.setVisibility(View.GONE);
                    nWords.setText(wordList.size() + " Palavras");
                }
            }

            @Override
            public void onFailure(Call<WordResponse> call, Throwable t) {
                Toast.makeText(context, "Falha na requisição", Toast.LENGTH_LONG).show();
            }
        });
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