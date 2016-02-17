package com.example.andreapolimena.consulenzeapp;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class InserimentoRichiesta extends AppCompatActivity {

    public static String Spec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserimento_richiesta);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.insert_request);

        final ListView listView = (ListView) findViewById(R.id.listView);
        final ArrayList<String> listSpec = new ArrayList<String>();

        UtenteDbHelper utenteDbHelper = new UtenteDbHelper(InserimentoRichiesta.this);
        SQLiteDatabase database = utenteDbHelper.getReadableDatabase();
        Cursor cursor = database.rawQuery("select " + UtenteDb.Utente.COLUMN_NAME_SPEC_PRINC + " from Utente", null);

        if(cursor!=null && cursor.moveToFirst()){
            while (!cursor.isAfterLast()){
                String spec = cursor.getString(cursor.getColumnIndexOrThrow(UtenteDb.Utente.COLUMN_NAME_SPEC_PRINC));
                if(!listSpec.contains(spec))
                listSpec.add(spec);
                cursor.moveToNext();
            }
        }

        cursor.close();
        database.close();
        utenteDbHelper.close();

        ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(this, R.layout.riga_specializ, R.id.textViewList, listSpec);
        listView.setAdapter(listAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent;
                switch(position){
                    case 0:
                        Spec = listSpec.get(position);
                        Toast.makeText(InserimentoRichiesta.this, Spec, Toast.LENGTH_LONG).show();
                        intent = new Intent(InserimentoRichiesta.this,InserimentoRichiesta2.class);
                        startActivity(intent);
                        break;
                    default:
                        Spec = listSpec.get(position);
                        Toast.makeText(InserimentoRichiesta.this, Spec, Toast.LENGTH_LONG).show();
                        intent = new Intent(InserimentoRichiesta.this,InserimentoRichiesta2.class);
                        startActivity(intent);
                        break;
                    //add more if you have more items in listview
                    //0 is the first item 1 second and so on...
                }
            }
        });
    }
}
