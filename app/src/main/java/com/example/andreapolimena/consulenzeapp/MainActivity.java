package com.example.andreapolimena.consulenzeapp;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = (ListView)findViewById(R.id.listView);
        List listUtente = new LinkedList();
        listUtente.add(new Utente());
        listUtente.add(new Utente());
        ListAdapter listAdapter = new com.example.andreapolimena.consulenzeapp.ListAdapter(this, R.layout.list_item,listUtente);
        //ArrayAdapter<Utente> customAdapter = new ArrayAdapter<Utente>(this, R.layout.list_item, listUtente);
        //CustomAdapter customAdapter= new CustomAdapter(this, R.layout.list_item, listUtente);
        listView.setAdapter(listAdapter);
    }
}
