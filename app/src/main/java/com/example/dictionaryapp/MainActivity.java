package com.example.dictionaryapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dictionaryapp.Adapter.MeaningsAdapter;
import com.example.dictionaryapp.Adapter.PhoneticsAdapter;
import com.example.dictionaryapp.Models.DictionaryModel;
import com.example.dictionaryapp.Models.MeaningsModel;
import com.example.dictionaryapp.Models.PhoneticsModel;


import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

        ProgressDialog progressDialog= new ProgressDialog(MainActivity.this);
        progressDialog.setTitle("Fetching the Data...");
        ArrayList<DictionaryModel> dictionary= new ArrayList<>();
        searchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                progressDialog.show();
                meanings.clear();
                phonetics.clear();
                APIObject.getApiInterface().searchWord(query).enqueue(new Callback<DictionaryModel>() {
                    @Override
                    public void onResponse(Call<DictionaryModel> call, Response<DictionaryModel> response) {
                        word.setText("Word: "+response.body().getWord());
                        origin.setText("Origin: "+ response.body().getOrigin());
                        dictionary.get(0).getMeanings().addAll(response.body().getMeanings());
                        dictionary.get(0).getPhonetics().addAll(response.body().getPhonetics());

                        meanings.addAll(dictionary.get(0).getMeanings());
                        meaningAdapter.notifyDataSetChanged();
                        phonetics.addAll(dictionary.get(0).getPhonetics());
                        phonticsAdapter.notifyDataSetChanged();

                        progressDialog.dismiss();
                    }

                    @Override
                    public void onFailure(Call<DictionaryModel> call, Throwable t) {
                        progressDialog.dismiss();
                        Toast.makeText(MainActivity.this, "Could not find the Data..!", Toast.LENGTH_SHORT).show();
                    }
                });
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });


    }
}