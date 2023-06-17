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
import com.example.smart_words_app.model.Collection;

public class CollectionAdapter extends RecyclerView.Adapter<CollectionAdapter.ViewHolder> {

    private Context context;
    private List<Collection> collectionList;

    public CollectionAdapter(Context context, List<Collection> collectionList) {
        this.context = context;
        this.collectionList = collectionList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView subtitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.card_title);
            subtitle = itemView.findViewById(R.id.card_subtitle);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_collection, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Collection collection = collectionList.get(position);

        holder.title.setText(collection.getAttributes().getEn());
        holder.subtitle.setText(collection.getAttributes().getPt());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, CollectionActivity.class);
            intent.putExtra("collectionName", collection.getAttributes().getName());
            intent.putExtra("collectionId", collection.getId());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return collectionList.size();
    }
}

