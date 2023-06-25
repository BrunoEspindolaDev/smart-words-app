package com.example.smart_words_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.example.smart_words_app.adapter.WordAdapter;
import com.example.smart_words_app.model.Word;
import com.example.smart_words_app.model.WordResponse;
import com.example.smart_words_app.retrofit.RetrofitInitializer;
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
    private int collectionId;
    private String collectionName;
    private ProgressBar progressBarWords;
    private TextView nWords;
    private ImageButton startButton;

    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);
        context = this;

        mapIntent();
        mapElements();
        hiddeElements();

        title.setText(collectionName);
        initializeTextToSpeech();

        startButton.setOnClickListener((c) -> handlePratice());

        wordList = new ArrayList<>();
        wordAdapter = new WordAdapter(context, wordList, textToSpeech);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(wordAdapter);
        loadData();
    }

    private void mapIntent() {
        collectionId = getIntent().getIntExtra("collectionId", 0);
        collectionName = getIntent().getStringExtra("collectionName");
    }

    private void mapElements() {
        progressBarWords = findViewById(R.id.progressBarWords);
        recyclerView = findViewById(R.id.recycleViewWords);
        startButton = findViewById(R.id.buttonCancel);
        nWords = findViewById(R.id.nWords);
        title = findViewById(R.id.labelProgress);
    }

    private void hiddeElements(){
        progressBarWords.setVisibility(View.VISIBLE);
        nWords.setVisibility(View.GONE);
    }

    private void showElements(){
        progressBarWords.setVisibility(View.GONE);
        nWords.setVisibility(View.VISIBLE);
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
                    nWords.setText(wordList.size() + " Palavras");
                    showElements();
                }
            }

            @Override
            public void onFailure(Call<WordResponse> call, Throwable t) {
                Toast.makeText(context, "Falha na requisição", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void handlePratice() {
        Intent intent = new Intent(context, QuizActivity.class);
        intent.putExtra("collectionName", collectionName);
        intent.putExtra("collectionId", collectionId);
        context.startActivity(intent);
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