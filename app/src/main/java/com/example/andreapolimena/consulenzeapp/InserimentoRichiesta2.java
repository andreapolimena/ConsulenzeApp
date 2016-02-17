package com.example.andreapolimena.consulenzeapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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

        UtenteDbHelper utenteDbHelper = new UtenteDbHelper(InserimentoRichiesta2.this);
        SQLiteDatabase database = utenteDbHelper.getReadableDatabase();
        Cursor cursor = database.rawQuery("select * from Utente where " +
                UtenteDb.Utente.COLUMN_NAME_SPEC_PRINC +
                "='" + InserimentoRichiesta.Spec +
                "'",
                null
        );

        if(cursor!=null&&cursor.moveToFirst()){
            while (!cursor.isAfterLast()){
                Utente u = new Utente();
                u.setUtente(cursor.getString(cursor.getColumnIndexOrThrow(UtenteDb.Utente.COLUMN_NAME_NOME)),
                        cursor.getString(cursor.getColumnIndexOrThrow(UtenteDb.Utente.COLUMN_NAME_COGNOME)),
                        cursor.getString(cursor.getColumnIndexOrThrow(UtenteDb.Utente.COLUMN_NAME_EMAIL)),
                        cursor.getString(cursor.getColumnIndexOrThrow(UtenteDb.Utente.COLUMN_NAME_INDIRIZZO)),
                        cursor.getString(cursor.getColumnIndexOrThrow(UtenteDb.Utente.COLUMN_NAME_SPEC_PRINC)),
                        cursor.getString(cursor.getColumnIndexOrThrow(UtenteDb.Utente.COLUMN_NAME_SPEC_SECOND))
                );
                listUtente.add(u);
                cursor.moveToNext();
            }
        }
        cursor.close();
        database.close();
        utenteDbHelper.close();

        ListAdapterRichieste listAdapterRichieste = new ListAdapterRichieste(this, R.layout.item_registrazione2, listUtente);
        listView.setAdapter(listAdapterRichieste);


    }
}
