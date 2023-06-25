package com.example.smart_words_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class QuizResultActivity extends AppCompatActivity {

    private Context context;
    private LinearLayout layout;
    private ImageView iconResult;
    private TextView labelHits;
    private TextView labelMisses;
    private TextView labelResultStatus;
    private TextView labelResume;
    private Button buttonFinish;
    private boolean isApproved = false;
    private String collectionName;
    private int collectionId;
    private int nHits = 0;
    private int nMisses = 0;
    private double hitPercentage = 0;
    private SharedPreferences.Editor sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mapIntent();
        beforeScreenMount();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_result);
        context = this;

        mapElements();
        afterScreenMount();

        buttonFinish.setOnClickListener(e -> handleFinish());
    }

    private void mapIntent() {
        nHits = getIntent().getIntExtra("nHits", 0);
        nMisses = getIntent().getIntExtra("nMisses", 0);
        collectionName = getIntent().getStringExtra("collectionName");
        collectionId = getIntent().getIntExtra("collectionId", 0);
    }

    private void mapElements() {
        layout = findViewById(R.id.layout);
        labelResume = findViewById(R.id.labelResume);
        labelResultStatus = findViewById(R.id.labelResultStatus);
        labelHits = findViewById(R.id.labelHits);
        labelMisses = findViewById(R.id.labelMisses);
        iconResult = findViewById(R.id.iconResult);
        buttonFinish = findViewById(R.id.buttonFinish);
    }

    private void beforeScreenMount() {
        hitPercentage = ((double) nHits / (nHits + nMisses)) * 100;

        if (hitPercentage > 90) {
            isApproved = true;
            setTheme(R.style.SuccessStatusBarTheme);
        } else {
            isApproved = false;
            setTheme(R.style.DangerStatusBarTheme);
        }
    }

    private void afterScreenMount() {
        int danger = ContextCompat.getColor(context, R.color.danger);
        int success = ContextCompat.getColor(context, R.color.success);

        labelHits.setText(String.valueOf(nHits));
        labelMisses.setText(String.valueOf(nMisses));

        if (isApproved) {
            layout.setBackgroundColor(success);
            iconResult.setImageResource(R.drawable.approved);
            labelResultStatus.setText("Aprovado");
            labelResume.setText("Parabéns, você completou esta coleção!");
            buttonFinish.setText("Finalizar");

            sharedPreferences = getSharedPreferences("collections", Context.MODE_PRIVATE).edit();
            sharedPreferences.putBoolean(collectionName, true);
            sharedPreferences.apply();
        } else {
            layout.setBackgroundColor(danger);
            iconResult.setImageResource(R.drawable.disapproved);
            labelResultStatus.setText("Reprovado");
            labelResume.setText("Opps... Estamos quase lá!\nAinda é necessário reforçar um pouco mais essa coleção.");
            buttonFinish.setText("Reiniciar");
        }
    }

    private void handleFinish() {
        if (isApproved) {
            context.startActivity(new Intent(context, MainActivity.class));
        } else {
            Intent intent = new Intent(context, QuizActivity.class);
            intent.putExtra("collectionName", collectionName);
            intent.putExtra("collectionId", collectionId);
            context.startActivity(intent);
        }
    }
}




