package com.example.andreapolimena.consulenzeapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class InserimentoRichiesta extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserimento_richiesta);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.insert_request);

        final ListView listView = (ListView) findViewById(R.id.listView);
        ArrayList<String> listSpec = new ArrayList<String>();
        listSpec.add("Specializzazione 1");
        listSpec.add("Specializzazione 2");
        listSpec.add("Specializzazione 3");
        listSpec.add("Specializzazione 4");
        listSpec.add("Specializzazione 5");
        listSpec.add("Specializzazione 6");
        listSpec.add("Specializzazione 7");
        listSpec.add("Specializzazione 8");
        listSpec.add("Specializzazione 8");
        listSpec.add("Specializzazione 8");

        ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(this, R.layout.riga_specializ, R.id.textViewList, listSpec);
        listView.setAdapter(listAdapter);

    }
}
