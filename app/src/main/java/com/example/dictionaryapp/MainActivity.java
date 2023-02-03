package com.example.dictionaryapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.example.dictionaryapp.Adapter.MeaningsAdapter;
import com.example.dictionaryapp.Adapter.PhoneticsAdapter;
import com.example.dictionaryapp.Models.MeaningsModel;
import com.example.dictionaryapp.Models.PhoneticsModel;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TextView word, origin;
    RecyclerView recyclerView_meaning, recyclerView_phonetics;
//    androidx.appcompat.widget.SearchView
    SearchView searchBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        word=findViewById(R.id.word);
        origin=findViewById(R.id.origin);
        recyclerView_meaning=findViewById(R.id.meaning_rv);
        recyclerView_phonetics=findViewById(R.id.phonetics_rv);
        searchBar=findViewById(R.id.searchView);

        ArrayList<PhoneticsModel> phonetics = new ArrayList<>();
        PhoneticsAdapter phonticsAdapter = new PhoneticsAdapter(MainActivity.this, phonetics);
        recyclerView_phonetics.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView_phonetics.setAdapter(phonticsAdapter);

        ArrayList<MeaningsModel> meanings = new ArrayList<>();
        MeaningsAdapter meaningAdapter = new MeaningsAdapter(MainActivity.this, meanings);
        recyclerView_meaning.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView_meaning.setAdapter(meaningAdapter);



    }
}