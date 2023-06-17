package com.example.smart_words_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.example.smart_words_app.adapter.CollectionAdapter;
import com.example.smart_words_app.model.Collection;
import com.example.smart_words_app.model.CollectionResponse;
import com.example.smart_words_app.retrofit.RetrofitInitializer;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private Context context;
    private RecyclerView recyclerView;
    private CollectionAdapter collectionAdapter;
    private List<Collection> collectionList;

    private ProgressBar progressBar;

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

        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        loadData();
    }

    private void loadData() {
        Call<CollectionResponse> call = new RetrofitInitializer().serviceCollections().getCollections();

        call.enqueue(new Callback<CollectionResponse>() {
            @Override
            public void onResponse(Call<CollectionResponse> call, Response<CollectionResponse> response) {
                CollectionResponse collectionResponse = response.body();

                for (Collection collection : collectionResponse.getCollectionList()) {
                    collectionList.add(collection);
                    collectionAdapter.notifyDataSetChanged();
                }

                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<CollectionResponse> call, Throwable t) {
                Toast.makeText(context, "Falha na requisição", Toast.LENGTH_LONG).show();
            }
        });
    }
}