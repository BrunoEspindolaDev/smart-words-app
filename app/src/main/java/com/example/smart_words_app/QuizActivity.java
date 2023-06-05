package com.example.smart_words_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smart_words_app.model.Question;
import com.example.smart_words_app.model.Word;
import com.example.smart_words_app.model.WordAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class QuizActivity extends AppCompatActivity {

    private Context context;
    private int nHits = 0;
    private int nMisses = 0;
    private int nQuestions = 9;
    private int nResponses = 1;
    private Question currentQuestion;
    private List<Word> words = new ArrayList<>();
    private TextView title;
    private ImageView image;
    private Button option1;
    private Button option2;
    private Button option3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        context = this;

        title = findViewById(R.id.title);
        image = findViewById(R.id.image);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);

        title.setText(nResponses + "/" + nQuestions);

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

        words.add(word1);
        words.add(word2);
        words.add(word3);
        words.add(word4);
        words.add(word5);
        words.add(word6);
        words.add(word7);
        words.add(word8);
        words.add(word9);
        words.add(word10);

        loadQuestion();

        option1.setOnClickListener(c -> onOptionClick(currentQuestion.getOption1()));
        option2.setOnClickListener(c -> onOptionClick(currentQuestion.getOption2()));
        option3.setOnClickListener(c -> onOptionClick(currentQuestion.getOption3()));
    }

    private void onOptionClick(Word selectedWord) {

        if (currentQuestion.getCorrectOption().getId() == selectedWord.getId()) {
            nHits++;
            Toast.makeText(context, "Acertou", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(context, "Errou", Toast.LENGTH_LONG).show();
            nMisses++;
        }

        for (Word word : words) {
            if (word.getId().equals(selectedWord.getId())) {
                words.remove(word);
                break;
            }
        }

        nResponses++;
        title.setText(nResponses + "/" + nQuestions);

        if (nResponses < nQuestions - 1) {
            loadQuestion();
        } else {
            Toast.makeText(context, "Acabou-se o que era doce", Toast.LENGTH_LONG).show();
            Toast.makeText(context, "x: "+nHits, Toast.LENGTH_LONG).show();

            Intent intent = new Intent(context, QuizResultActivity.class);
            intent.putExtra("nHits", nHits);
            intent.putExtra("nMisses", nMisses);
            context.startActivity(intent);
        }


    }

    private void loadQuestion() {
        List<Word> selectedWords = selectRandomWords(words, 3);
        Question question = new Question(selectedWords.get(0), selectedWords.get(1), selectedWords.get(2), selectedWords.get(0));

        image.setImageResource(R.drawable.ic_launcher_background);
        option1.setText(question.getOption1().getAttributes().getEn());
        option2.setText(question.getOption2().getAttributes().getEn());
        option3.setText(question.getOption3().getAttributes().getEn());
        currentQuestion = question;

    }


    public List<Word> selectRandomWords(List<Word> words, int numWordsToSelect) {
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
}