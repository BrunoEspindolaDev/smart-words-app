package com.example.smart_words_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smart_words_app.model.Word;
import com.example.smart_words_app.model.WordResponse;
import com.example.smart_words_app.retrofit.RetrofitInitializer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class QuizActivity extends AppCompatActivity {

    private Context context;
    private int nHits = 0;
    private int nMisses = 0;
    private int nQuestions = 0;
    private int nResponses = 0;
    private Word currentWord;
    private List<Word> wordList = new ArrayList<>();
    private ImageButton buttonCancel;
    private TextView title;

    private static final int REQ_CODE_SPEECH_INPUT = 100;
    private TextView textView;
    private ImageButton btnSpeak;
    private SpeechRecognizer speechRecognizer;

    private int collectionId;

    private TextView ptWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        context = this;


        collectionId = getIntent().getIntExtra("collectionId", 0);

        mapElements();
        loadData();


        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(context);
        speechRecognizer.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle params) {
                // Chamado quando o dispositivo está pronto para iniciar a fala.

            }

            @Override
            public void onBeginningOfSpeech() {
                // Chamado quando a fala foi iniciada.
                Toast.makeText(context, "xxx", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRmsChanged(float rmsdB) {
                // Chamado quando há uma alteração no nível de áudio.

            }

            @Override
            public void onBufferReceived(byte[] buffer) {
                // Chamado quando o buffer de áudio é recebido.
            }

            @Override
            public void onEndOfSpeech() {
                // Chamado quando a fala foi concluída.
            }

            @Override
            public void onError(int error) {
                // Chamado em caso de erro durante o reconhecimento de fala.
                Toast.makeText(context, "teste", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResults(Bundle results) {
                ArrayList<String> matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                if (matches != null && !matches.isEmpty()) {
                    String text = matches.get(0);
                    Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onPartialResults(Bundle partialResults) {
                // Chamado quando resultados parciais do reconhecimento de fala estão disponíveis.
            }

            @Override
            public void onEvent(int eventType, Bundle params) {
                // Chamado quando um evento adicional relacionado ao reconhecimento de fala ocorre.
            }
        });

        buttonCancel.setOnClickListener(e -> handleCancel());

        btnSpeak.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                startSpeechToText();
                return true; // Retorna true para indicar que o evento de clique longo foi tratado
            }
        });

        btnSpeak.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    stopSpeechToText();
                }
                return false;
            }
        });
    }


    private void startSpeechToText() {
        btnSpeak.setImageResource(R.drawable.recording);
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en-US");
        speechRecognizer.startListening(intent);
    }

    private void stopSpeechToText() {
        btnSpeak.setImageResource(R.drawable.mic);
        speechRecognizer.stopListening();
    }

    private void mapElements() {
        title = findViewById(R.id.title);
        buttonCancel = findViewById(R.id.cancelButton);
        btnSpeak = findViewById(R.id.buttonRec);
        ptWord = findViewById(R.id.ptWord);
    }

//    private void handleOptionClick(Word selectedWord) {
//        if (currentQuestion.getCorrectOption().getId() == selectedWord.getId()) {
//            nHits++;
//        } else {
//            nMisses++;
//        }
//
//        nResponses++;
//        title.setText(nResponses + "/" + nQuestions);
//
//        if (nResponses < nQuestions) {
//            loadQuestion();
//        } else {
//            Intent intent = new Intent(context, QuizResultActivity.class);
//            intent.putExtra("nHits", nHits);
//            intent.putExtra("nMisses", nMisses);
//            context.startActivity(intent);
//        }
//    }

    public void handleCancel() {
        Intent intent = new Intent(context, CollectionActivity.class);
        context.startActivity(intent);
    }

    private void loadWord() {
        Word selectedWord = selectRandomWord(wordList);

        if (selectedWord != null) {
            currentWord = selectedWord;
            removeWordFromList(wordList, selectedWord);
            ptWord.setText(selectedWord.getAttributes().getPt());
        }
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

    private void loadData() {
        Call<WordResponse> call = new RetrofitInitializer().serviceWord().getWords(collectionId);

        call.enqueue(new Callback<WordResponse>() {
            @Override
            public void onResponse(Call<WordResponse> call, Response<WordResponse> response) {
                WordResponse wordResponse = response.body();
                if (wordResponse.getWordList().length > 0) {
                    if (wordResponse.getWordList().length > 0) {
                        for (Word word : wordResponse.getWordList()) {
                            wordList.add(word);
                        }
                    }
                    title.setText(nResponses + "/" + wordList.size());
                    loadWord();
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
        if (speechRecognizer != null) {
            speechRecognizer.destroy();
        }
    }
}