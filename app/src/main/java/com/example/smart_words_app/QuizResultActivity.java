package com.example.smart_words_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class QuizResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_result);

        Intent intent = getIntent();
        int nHits = intent.getIntExtra("nHits",0);
        int nMisses = intent.getIntExtra("nMisses",0);

        TextView nHitsTextView = findViewById(R.id.nHits);
        TextView nMissesTextView = findViewById(R.id.nMisses);

        nHitsTextView.setText("Número de acertos: "+nHits);
        nMissesTextView.setText("Número de erros: "+nMisses);

    }
}