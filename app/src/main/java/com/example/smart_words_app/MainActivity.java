package com.example.smart_words_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.os.Bundle;
import com.example.smart_words_app.adapter.CollectionAdapter;
import com.example.smart_words_app.model.Collection;
import com.example.smart_words_app.model.CollectionAttributes;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Context context;
    private RecyclerView recyclerView;
    private CollectionAdapter collectionAdapter;
    private List<Collection> collectionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        collectionList = new ArrayList<>();
        collectionAdapter = new CollectionAdapter(context, collectionList);
        recyclerView = findViewById(R.id.recycleViewCollections);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(collectionAdapter);

        loadData();
    }

    private void loadData() {
        Collection collection1 = new Collection("1", new CollectionAttributes("potato", "Batata", "Potato", "06/06/2023", "06/06/2023", "06/06/2023"));

        collectionList.add(collection1);
        collectionList.add(collection1);
        collectionList.add(collection1);
        collectionList.add(collection1);
        collectionList.add(collection1);
        collectionList.add(collection1);
        collectionList.add(collection1);
        collectionList.add(collection1);
        collectionList.add(collection1);
        collectionList.add(collection1);
        collectionList.add(collection1);
        collectionList.add(collection1);
        collectionList.add(collection1);
        collectionList.add(collection1);
        collectionList.add(collection1);
        collectionList.add(collection1);
        collectionList.add(collection1);
        collectionList.add(collection1);

        collectionAdapter.notifyDataSetChanged();
    }
}