package com.example.smart_words_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
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
    private int nQuestions = 0;
    private int nResponses = 0;
    private Question currentQuestion;
    private List<Word> wordList = new ArrayList<>();
    private List<Word> supportWordList = new ArrayList<>();
    private ImageButton cancelButton;
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
        cancelButton = findViewById(R.id.cancelButton);
        image = findViewById(R.id.image);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);

        cancelButton.setOnClickListener(e -> {
            Intent intent = new Intent(context, CollectionActivity.class);
            context.startActivity(intent);
        });

        title.setText(nResponses + "/" + nQuestions);

        loadWordList();
        loadSupportWordList();
        loadQuestion();

        option1.setOnClickListener(c -> onOptionClick(currentQuestion.getOption1()));
        option2.setOnClickListener(c -> onOptionClick(currentQuestion.getOption2()));
        option3.setOnClickListener(c -> onOptionClick(currentQuestion.getOption3()));
    }

    private void onOptionClick(Word selectedWord) {
        if (currentQuestion.getCorrectOption().getId() == selectedWord.getId()) {
            nHits++;
        } else {
            nMisses++;
        }

        nResponses++;
        title.setText(nResponses + "/" + nQuestions);

        if (nResponses < nQuestions) {
            loadQuestion();
        } else {
            Intent intent = new Intent(context, QuizResultActivity.class);
            intent.putExtra("nHits", nHits);
            intent.putExtra("nMisses", nMisses);
            context.startActivity(intent);
        }
    }

    private void loadQuestion() {
        Word selectedWord = selectRandomWord(wordList);
        List<Word> selectedSupportWords = selectRandomWords(supportWordList, 2);
        Question question = new Question(selectedWord, selectedSupportWords.get(0), selectedSupportWords.get(1), selectedWord);

        removeWordFromList(wordList, selectedWord);
        removeWordFromList(supportWordList, selectedSupportWords.get(0));
        removeWordFromList(supportWordList, selectedSupportWords.get(1));

        image.setImageResource(R.drawable.ic_launcher_background);
        option1.setText(question.getOption1().getAttributes().getEn());
        option2.setText(question.getOption2().getAttributes().getEn());
        option3.setText(question.getOption3().getAttributes().getEn());
        currentQuestion = question;
    }

    private void loadWordList() {
        wordList.add(new Word("1", new WordAttributes("Bed", "Cama", "06/06/2023", "06/06/2023", "06/06/2023")));
        wordList.add(new Word("2", new WordAttributes("Bedside Table", "Mesa de cabeceira", "06/06/2023", "06/06/2023", "06/06/2023")));
        wordList.add(new Word("3", new WordAttributes("Wardrobe", "Guarda-roupa", "06/06/2023", "06/06/2023", "06/06/2023")));
        wordList.add(new Word("4", new WordAttributes("Lamp", "Luminária", "06/06/2023", "06/06/2023", "06/06/2023")));
        wordList.add(new Word("5", new WordAttributes("Nightstand", "Criado-mudo", "06/06/2023", "06/06/2023", "06/06/2023")));
        nQuestions = wordList.size();
    }

    private void loadSupportWordList() {
        supportWordList.add(new Word("1", new WordAttributes("Desk", "Escrivaninha", "06/06/2023", "06/06/2023", "06/06/2023")));
        supportWordList.add(new Word("2", new WordAttributes("Bookshelf", "Estante de livros", "06/06/2023", "06/06/2023", "06/06/2023")));
        supportWordList.add(new Word("3", new WordAttributes("Dresser", "Cômoda", "06/06/2023", "06/06/2023", "06/06/2023")));
        supportWordList.add(new Word("4", new WordAttributes("Mirror", "Espelho", "06/06/2023", "06/06/2023", "06/06/2023")));
        supportWordList.add(new Word("5", new WordAttributes("Curtains", "Cortinas", "06/06/2023", "06/06/2023", "06/06/2023")));
        supportWordList.add(new Word("6", new WordAttributes("Armchair", "Poltrona", "06/06/2023", "06/06/2023", "06/06/2023")));
        supportWordList.add(new Word("7", new WordAttributes("Dining Table", "Mesa de jantar", "06/06/2023", "06/06/2023", "06/06/2023")));
        supportWordList.add(new Word("8", new WordAttributes("China Cabinet", "Cristaleira", "06/06/2023", "06/06/2023", "06/06/2023")));
        supportWordList.add(new Word("9", new WordAttributes("Sideboard", "Aparador", "06/06/2023", "06/06/2023", "06/06/2023")));
        supportWordList.add(new Word("10", new WordAttributes("Chandelier", "Candelabro", "06/06/2023", "06/06/2023", "06/06/2023")));
        supportWordList.add(new Word("11", new WordAttributes("Coffee Table", "Mesa de centro", "06/06/2023", "06/06/2023", "06/06/2023")));
        supportWordList.add(new Word("12", new WordAttributes("Shelves", "Prateleiras", "06/06/2023", "06/06/2023", "06/06/2023")));
        supportWordList.add(new Word("13", new WordAttributes("Sofa Bed", "Sofá-cama", "06/06/2023", "06/06/2023", "06/06/2023")));
        supportWordList.add(new Word("14", new WordAttributes("Dining Chairs", "Cadeiras de jantar", "06/06/2023", "06/06/2023", "06/06/2023")));
        supportWordList.add(new Word("15", new WordAttributes("Cabinet", "Armário", "06/06/2023", "06/06/2023", "06/06/2023")));
        supportWordList.add(new Word("16", new WordAttributes("Ceiling Fan", "Ventilador de teto", "06/06/2023", "06/06/2023", "06/06/2023")));
        supportWordList.add(new Word("17", new WordAttributes("Dresser Mirror", "Espelho da cômoda", "06/06/2023", "06/06/2023", "06/06/2023")));
        supportWordList.add(new Word("18", new WordAttributes("Window Blinds", "Persianas", "06/06/2023", "06/06/2023", "06/06/2023")));
        supportWordList.add(new Word("19", new WordAttributes("Ottoman", "Pufe", "06/06/2023", "06/06/2023", "06/06/2023")));
        supportWordList.add(new Word("20", new WordAttributes("Rug", "Tapete", "06/06/2023", "06/06/2023", "06/06/2023")));
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