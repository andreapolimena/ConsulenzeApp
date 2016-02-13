package com.example.andreapolimena.consulenzeapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.LinkedList;
import java.util.List;

public class InserimentoRichiesta2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserimento_richiesta2);

        ListView listView = (ListView) findViewById(R.id.listView3);
        List listUtente = new LinkedList();
        listUtente.add(new Utente());
        listUtente.add(new Utente());
        listUtente.add(new Utente());
        listUtente.add(new Utente());
        listUtente.add(new Utente());
        listUtente.add(new Utente());
        listUtente.add(new Utente());
        listUtente.add(new Utente());
        listUtente.add(new Utente());
        ListAdapter listAdapter = new ListAdapter(this, R.layout.item_registrazione2, listUtente);
        listView.setAdapter(listAdapter);

    }
}
