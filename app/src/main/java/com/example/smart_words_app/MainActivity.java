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
        Collection collection1 = new Collection("1", new CollectionAttributes("Living room", "Sala de estar", "Living room", "06/06/2023", "06/06/2023", "06/06/2023"));
        Collection collection2 = new Collection("2", new CollectionAttributes("Kitchen", "Cozinha", "Kitchen", "06/06/2023", "06/06/2023", "06/06/2023"));
        Collection collection3 = new Collection("3", new CollectionAttributes("Bedroom", "Quarto", "Bedroom", "06/06/2023", "06/06/2023", "06/06/2023"));
        Collection collection4 = new Collection("4", new CollectionAttributes("Bathroom", "Banheiro", "Bathroom", "06/06/2023", "06/06/2023", "06/06/2023"));
        Collection collection5 = new Collection("5", new CollectionAttributes("Dining room", "Sala de jantar", "Dining room", "06/06/2023", "06/06/2023", "06/06/2023"));
        Collection collection6 = new Collection("6", new CollectionAttributes("Home office", "Escritório em casa", "Home office", "06/06/2023", "06/06/2023", "06/06/2023"));
        Collection collection7 = new Collection("7", new CollectionAttributes("Laundry room", "Área de serviço", "Laundry room", "06/06/2023", "06/06/2023", "06/06/2023"));
        Collection collection8 = new Collection("8", new CollectionAttributes("Garage", "Garagem", "Garage", "06/06/2023", "06/06/2023", "06/06/2023"));
        Collection collection9 = new Collection("9", new CollectionAttributes("Garden", "Jardim", "Garden", "06/06/2023", "06/06/2023", "06/06/2023"));
        Collection collection10 = new Collection("10", new CollectionAttributes("Patio", "Pátio", "Patio", "06/06/2023", "06/06/2023", "06/06/2023"));
        Collection collection11 = new Collection("11", new CollectionAttributes("Attic", "Sótão", "Attic", "06/06/2023", "06/06/2023", "06/06/2023"));
        Collection collection12 = new Collection("12", new CollectionAttributes("Basement", "Porão", "Basement", "06/06/2023", "06/06/2023", "06/06/2023"));
        Collection collection13 = new Collection("13", new CollectionAttributes("Playroom", "Sala de jogos", "Playroom", "06/06/2023", "06/06/2023", "06/06/2023"));
        Collection collection14 = new Collection("14", new CollectionAttributes("Library", "Biblioteca", "Library", "06/06/2023", "06/06/2023", "06/06/2023"));
        Collection collection15 = new Collection("15", new CollectionAttributes("Study room", "Sala de estudos", "Study room", "06/06/2023", "06/06/2023", "06/06/2023"));
        Collection collection16 = new Collection("16", new CollectionAttributes("Guest room", "Quarto de hóspedes", "Guest room", "06/06/2023", "06/06/2023", "06/06/2023"));
        Collection collection17 = new Collection("17", new CollectionAttributes("Home theater", "Home theater", "Home theater", "06/06/2023", "06/06/2023", "06/06/2023"));
        Collection collection18 = new Collection("18", new CollectionAttributes("Wine cellar", "Cave de vinhos", "Wine cellar", "06/06/2023", "06/06/2023", "06/06/2023"));
        Collection collection19 = new Collection("19", new CollectionAttributes("Fitness room", "Sala de ginástica", "Fitness room", "06/06/2023", "06/06/2023", "06/06/2023"));
        Collection collection20 = new Collection("20", new CollectionAttributes("Swimming pool", "Piscina", "Swimming pool", "06/06/2023", "06/06/2023", "06/06/2023"));
        Collection collection21 = new Collection("21", new CollectionAttributes("Sauna", "Sauna", "Sauna", "06/06/2023", "06/06/2023", "06/06/2023"));
        Collection collection22 = new Collection("22", new CollectionAttributes("Game room", "Sala de jogos", "Game room", "06/06/2023", "06/06/2023", "06/06/2023"));
        Collection collection23 = new Collection("23", new CollectionAttributes("Bar", "Bar", "Bar", "06/06/2023", "06/06/2023", "06/06/2023"));
        Collection collection24 = new Collection("24", new CollectionAttributes("Balcony", "Varanda", "Balcony", "06/06/2023", "06/06/2023", "06/06/2023"));
        Collection collection25 = new Collection("25", new CollectionAttributes("Terrace", "Terraço", "Terrace", "06/06/2023", "06/06/2023", "06/06/2023"));
        Collection collection26 = new Collection("26", new CollectionAttributes("Mudroom", "Sala de entrada", "Mudroom", "06/06/2023", "06/06/2023", "06/06/2023"));
        Collection collection27 = new Collection("27", new CollectionAttributes("Washroom", "Banheiro de serviço", "Washroom", "06/06/2023", "06/06/2023", "06/06/2023"));
        Collection collection28 = new Collection("28", new CollectionAttributes("Staircase", "Escada", "Staircase", "06/06/2023", "06/06/2023", "06/06/2023"));
        Collection collection29 = new Collection("29", new CollectionAttributes("Hallway", "Corredor", "Hallway", "06/06/2023", "06/06/2023", "06/06/2023"));
        Collection collection30 = new Collection("30", new CollectionAttributes("Roof terrace", "Terraço no telhado", "Roof terrace", "06/06/2023", "06/06/2023", "06/06/2023"));

        collectionList.add(collection1);
        collectionList.add(collection2);
        collectionList.add(collection3);
        collectionList.add(collection4);
        collectionList.add(collection5);
        collectionList.add(collection6);
        collectionList.add(collection7);
        collectionList.add(collection8);
        collectionList.add(collection9);
        collectionList.add(collection10);
        collectionList.add(collection11);
        collectionList.add(collection12);
        collectionList.add(collection13);
        collectionList.add(collection14);
        collectionList.add(collection15);
        collectionList.add(collection16);
        collectionList.add(collection17);
        collectionList.add(collection18);
        collectionList.add(collection19);
        collectionList.add(collection20);
        collectionList.add(collection21);
        collectionList.add(collection22);
        collectionList.add(collection23);
        collectionList.add(collection24);
        collectionList.add(collection25);
        collectionList.add(collection26);
        collectionList.add(collection27);
        collectionList.add(collection28);
        collectionList.add(collection29);
        collectionList.add(collection30);

        collectionAdapter.notifyDataSetChanged();
    }
}