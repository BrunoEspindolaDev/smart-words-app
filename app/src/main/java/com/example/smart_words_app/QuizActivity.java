package com.example.smart_words_app;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.smart_words_app.model.Question;
import com.example.smart_words_app.model.Word;
import com.example.smart_words_app.model.WordAttributes;


public class QuizActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        int nWords = 9;
        int nHits = 0;
        int nMisses = 0;
        int nResponses = 1;
        Word currentWord;

        TextView title = findViewById(R.id.title);
        title.setText(nResponses + "/" + nWords);

        Word word1 = new Word("1", new WordAttributes("Bed", "Cama", "06/06/2023", "06/06/2023", "06/06/2023"));
        Word word2 = new Word("2", new WordAttributes("Bedside Table", "Mesa de cabeceira", "06/06/2023", "06/06/2023", "06/06/2023"));
        Word word3 = new Word("3", new WordAttributes("Wardrobe", "Guarda-roupa", "06/06/2023", "06/06/2023", "06/06/2023"));
        Word word4 = new Word("4", new WordAttributes("Lamp", "Luminária", "06/06/2023", "06/06/2023", "06/06/2023"));
        Word word5 = new Word("5", new WordAttributes("Nightstand", "Criado-mudo", "06/06/2023", "06/06/2023", "06/06/2023"));
        Word word6 = new Word("6", new WordAttributes("Pillow", "Travesseiro", "06/06/2023", "06/06/2023", "06/06/2023"));
        Word word7 = new Word("7", new WordAttributes("Blanket", "Cobertor", "06/06/2023", "06/06/2023", "06/06/2023"));
        Word word8 = new Word("8", new WordAttributes("Curtain", "Cortina", "06/06/2023", "06/06/2023", "06/06/2023"));
        Word word9 = new Word("9", new WordAttributes("Rug", "Tapete", "06/06/2023", "06/06/2023", "06/06/2023"));
        Word word10 = new Word("10", new WordAttributes("Mirror", "Espelho", "06/06/2023", "06/06/2023", "06/06/2023"));

        Question question1 = new Question();
        question1.setOption1(word1);
        question1.setOption1(word2);
        question1.setOption1(word3);
        question1.setCorrectOption(word1);

        loadQuestion(question1);
    }


    public void loadQuestion(Question question) {
        ImageView image = findViewById(R.id.image);
        TextView option1 = findViewById(R.id.option1);
        TextView option2 = findViewById(R.id.option2);
        TextView option3 = findViewById(R.id.option3);

//        image.setImageResource(R.drawable.ic_launcher_background);
        option1.setText(question.getOption1().getAttributes().getEn());
        option2.setText(question.getOption2().getAttributes().getEn());
        option3.setText(question.getOption3().getAttributes().getEn());
    }
}