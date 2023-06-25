package com.example.smart_words_app.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.List;

import com.example.smart_words_app.CollectionActivity;
import com.example.smart_words_app.QuizActivity;
import com.example.smart_words_app.R;
import com.example.smart_words_app.model.Word;
import com.fasterxml.jackson.databind.ObjectMapper;


public class WordAdapter extends RecyclerView.Adapter<WordAdapter.ViewHolder> {

    private TextToSpeech textToSpeech;
    private Context context;
    private List<Word> wordList;

    public WordAdapter(Context context, List<Word> wordList, TextToSpeech textToSpeech) {
        this.context = context;
        this.wordList = wordList;
        this.textToSpeech = textToSpeech;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView subtitle;
        public ImageButton buttonSpeech;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.card_title);
            subtitle = itemView.findViewById(R.id.card_subtitle);
            buttonSpeech = itemView.findViewById(R.id.buttonSpeech);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_word, parent, false);
        return new ViewHolder(v);
    }

    public static void setTimeout(Runnable runnable,int delay){
        new Thread(()->{
            try{
                Thread.sleep(delay);
                runnable.run();
            }
            catch(Exception e){
                System.err.println(e);
            }
        }).start();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Word word = wordList.get(position);
        holder.title.setText(word.getAttributes().getEn());
        holder.subtitle.setText(word.getAttributes().getPt());

        holder.itemView.setOnClickListener(c -> {
            holder.buttonSpeech.setImageResource(R.drawable.soundstart);
            textToSpeech.speak(word.getAttributes().getEn(), TextToSpeech.QUEUE_FLUSH, null);
            setTimeout(() -> holder.buttonSpeech.setImageResource(R.drawable.sound),800);
        });
    }


    @Override
    public int getItemCount() {
        return wordList.size();
    }
}

