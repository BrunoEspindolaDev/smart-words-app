package com.example.smart_words_app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smart_words_app.model.Question;
import com.example.smart_words_app.model.Word;
import com.example.smart_words_app.model.WordResponse;
import com.example.smart_words_app.retrofit.RetrofitInitializer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuizActivity extends AppCompatActivity {

    private Context context;
    private TextView labelProgress;
    private TextView labelWord;
    private ImageButton buttonCancel;
    private Button buttonOption1;
    private Button buttonOption2;
    private Button buttonOption3;
    private LinearLayout header;
    private ProgressBar progressBarQuestion;
    private String collectionName;
    private int collectionId;
    private List<Word> collectionWordList = new ArrayList<>();
    private List<Word> randomWordList = new ArrayList<>();
    private Question currentQuestion;
    private int nHits = 0;
    private int nMisses = 0;
    private int nResponses = 0;
    private int nQuestions = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        context = this;

        mapIntent();
        mapElements();
        hiddeElements();
        loadListeners();
        loadRandomWordList();
    }


    private void mapElements() {
        labelWord = findViewById(R.id.labelWord);
        labelProgress = findViewById(R.id.labelProgress);
        buttonCancel = findViewById(R.id.buttonCancel);
        buttonOption1 = findViewById(R.id.buttonOption1);
        buttonOption2 = findViewById(R.id.buttonOption2);
        buttonOption3 = findViewById(R.id.buttonOption3);
        progressBarQuestion = findViewById(R.id.progressBarQuestion);
        header = findViewById(R.id.header);
    }

    private void hiddeElements() {
        progressBarQuestion.setVisibility(View.VISIBLE);
        labelWord.setVisibility(View.GONE);
        labelProgress.setVisibility(View.GONE);
        buttonOption1.setVisibility(View.GONE);
        buttonOption2.setVisibility(View.GONE);
        buttonOption3.setVisibility(View.GONE);
        buttonCancel.setVisibility(View.GONE);
        header.setVisibility(View.GONE);
    }

    private void showElements() {
        progressBarQuestion.setVisibility(View.GONE);
        labelWord.setVisibility(View.VISIBLE);
        labelProgress.setVisibility(View.VISIBLE);
        buttonOption1.setVisibility(View.VISIBLE);
        buttonOption2.setVisibility(View.VISIBLE);
        buttonOption3.setVisibility(View.VISIBLE);
        buttonCancel.setVisibility(View.VISIBLE);
        header.setVisibility(View.VISIBLE);
    }

    private void mapIntent() {
        collectionName = getIntent().getStringExtra("collectionName");
        collectionId = getIntent().getIntExtra("collectionId", 0);
    }

    private void loadListeners() {
        buttonOption1.setOnClickListener(c -> handleOptionClick(currentQuestion.getOption1()));
        buttonOption2.setOnClickListener(c -> handleOptionClick(currentQuestion.getOption2()));
        buttonOption3.setOnClickListener(c -> handleOptionClick(currentQuestion.getOption3()));
        buttonCancel.setOnClickListener(c -> handleCancel());
    }

    private void loadRandomWordList() {
        Call<WordResponse> call = new RetrofitInitializer().serviceWord().getRandomWords(collectionId);

        call.enqueue(new Callback<WordResponse>() {
            @Override
            public void onResponse(Call<WordResponse> call, Response<WordResponse> response) {
                WordResponse wordResponse = response.body();
                Word[] wordList = wordResponse.getWordList();

                if (wordList.length > 0) {
                    for (Word word : wordResponse.getWordList()) {
                        randomWordList.add(word);
                    }
                    loadCollectionWordList();
                }
            }

            @Override
            public void onFailure(Call<WordResponse> call, Throwable t) {
                Toast.makeText(context, "Falha na requisição", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void loadCollectionWordList() {
        Call<WordResponse> call = new RetrofitInitializer().serviceWord().getWords(collectionId);

        call.enqueue(new Callback<WordResponse>() {
            @Override
            public void onResponse(Call<WordResponse> call, Response<WordResponse> response) {
                WordResponse wordResponse = response.body();
                Word[] wordList = wordResponse.getWordList();

                if (wordList.length > 0) {
                    for (Word word : wordResponse.getWordList()) {
                        collectionWordList.add(word);
                        nQuestions = wordList.length;
                    }
                    labelProgress.setText(wordList.length + " Palavras");
                    loadQuestion();
                    showElements();
                }
            }

            @Override
            public void onFailure(Call<WordResponse> call, Throwable t) {
                Toast.makeText(context, "Falha na requisição", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void loadQuestion() {
        if (collectionWordList.size() > 0 && randomWordList.size() > 2) {
            Word correctOption = selectRandomWord(collectionWordList);
            List<Word> randomOptions = selectRandomWords(randomWordList, 2);

            List<Word> questionOptions = new ArrayList<>();
            questionOptions.add(randomOptions.get(0));
            questionOptions.add(randomOptions.get(1));
            questionOptions.add(correctOption);
            Collections.shuffle(questionOptions);

            Question question = new Question(questionOptions.get(0), questionOptions.get(1), questionOptions.get(2), correctOption);

            labelWord.setText(correctOption.getAttributes().getPt());
            buttonOption1.setText(question.getOption1().getAttributes().getEn());
            buttonOption2.setText(question.getOption2().getAttributes().getEn());
            buttonOption3.setText(question.getOption3().getAttributes().getEn());

            currentQuestion = question;

            removeWordFromList(randomWordList, randomOptions.get(0));
            removeWordFromList(randomWordList, randomOptions.get(1));
            removeWordFromList(collectionWordList, correctOption);
        }
    }

    private void handleOptionClick(Word selectedOption) {
        if (currentQuestion.getCorrectOption().getId() == selectedOption.getId()) {
            nHits++;
        } else {
            nMisses++;
        }

        nResponses++;
        labelProgress.setText(nResponses + "/" + nQuestions);

        if (nResponses < nQuestions) {
            loadQuestion();
        } else {
            Intent intent = new Intent(context, QuizResultActivity.class);
            intent.putExtra("nHits", nHits);
            intent.putExtra("nMisses", nMisses);
            intent.putExtra("collectionId", collectionId);
            intent.putExtra("collectionName", collectionName);
            context.startActivity(intent);
        }
    }

    private void handleCancel() {
        Intent intent = new Intent(context, CollectionActivity.class);
        intent.putExtra("collectionId", collectionId);
        intent.putExtra("collectionName", collectionName);
        context.startActivity(intent);
    }

    private List<Word> selectRandomWords(List<Word> words, int numWordsToSelect) {
        List<Word> selectedWords = new ArrayList<>();
        int totalWords = words.size();
        Random random = new Random();

        for (int i = 0; i < numWordsToSelect; i++) {
            int randomIndex = random.nextInt(totalWords);
            Word randomWord = words.get(randomIndex);
            selectedWords.add(randomWord);
        }

        return selectedWords;
    }

    private Word selectRandomWord(List<Word> words) {
        int totalWords = words.size();
        Random random = new Random();
        int randomIndex = random.nextInt(totalWords);
        return words.get(randomIndex);
    }


    public static <T> void removeWordFromList(List<T> list, T itemToRemove) {
        list.remove(itemToRemove);
    }


}
