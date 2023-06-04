package com.example.smart_words_app.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import com.example.smart_words_app.CollectionActivity;
import com.example.smart_words_app.R;
import com.example.smart_words_app.model.Word;

public class WordAdapter extends RecyclerView.Adapter<WordAdapter.ViewHolder> {

    private Context context;
    private List<Word> wordList;

    public WordAdapter(Context context, List<Word> wordList) {
        this.context = context;
        this.wordList = wordList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView subtitle;
        public ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.card_title);
            subtitle = itemView.findViewById(R.id.card_subtitle);
            image = itemView.findViewById(R.id.card_image);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_word, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Word word = wordList.get(position);

        holder.title.setText(word.getAttributes().getEn());
        holder.subtitle.setText(word.getAttributes().getPt());
        holder.image.setImageResource(R.drawable.ic_launcher_background);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, CollectionActivity.class);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return wordList.size();
    }
}

