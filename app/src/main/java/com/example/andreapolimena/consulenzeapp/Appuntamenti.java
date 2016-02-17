package com.example.andreapolimena.consulenzeapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.List;

public class Appuntamenti extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appuntamenti);

        UtenteDbHelper utenteDbHelper = new UtenteDbHelper(this);
        SQLiteDatabase database = utenteDbHelper.getReadableDatabase();

        //database = utenteDbHelper.getWritableDatabase();
        //database.execSQL("insert into Appuntamenti values(1, 'andrea', 'polimena', 'infor', '2016-01-01','20:00:00');");

        Cursor cursor = database.rawQuery("select nome, cognome, spec_princ, data, ora from Appuntamenti where email=='"+Inizio.utenteLoggato+"'", null);
        List listAppuntamentiClass = new LinkedList();
        if(cursor!=null && cursor.moveToFirst()) {
            while(!cursor.isAfterLast()) {
                String nome = "";
                String cognome = "";
                String spec = "";
                String data = "";
                String time ="";
                int giorno = 0;
                int mese = 0;
                int anno = 0;
                int ora = 0;
                int min = 0;
                nome = cursor.getString(cursor.getColumnIndexOrThrow(UtenteDb.Utente.COLUMN_NAME_NOME));
                cognome = cursor.getString(cursor.getColumnIndexOrThrow(UtenteDb.Utente.COLUMN_NAME_COGNOME));
                spec = cursor.getString(cursor.getColumnIndexOrThrow(UtenteDb.Utente.COLUMN_NAME_SPEC_PRINC));
                data = cursor.getString(cursor.getColumnIndexOrThrow(UtenteDb.Utente.COLUMN_NAME_DATE));
                time = cursor.getString(cursor.getColumnIndexOrThrow(UtenteDb.Utente.COLUMN_NAME_ORA));

                String[] parts = data.split("-");
                String[] parts2 = time.split(":");

                giorno = Integer.parseInt(parts[2]);
                mese = Integer.parseInt(parts[1]);
                anno = Integer.parseInt(parts[0]);
                ora = Integer.parseInt(parts2[0]);
                min = Integer.parseInt(parts2[1]);

                listAppuntamentiClass.add(new AppuntamentiClass(nome, cognome, spec, giorno, mese, anno, ora, min));
                cursor.moveToNext();
            }
            cursor.close();
        }
        database.close();
        utenteDbHelper.close();

        ListView listView = (ListView) findViewById(R.id.listView2);
        ListAdapterAppuntamentiClass listAdapterAppuntamentiClass = new ListAdapterAppuntamentiClass(this, R.layout.list_item, listAppuntamentiClass);

        listView.setAdapter(listAdapterAppuntamentiClass);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Appuntamenti.this, InserimentoDisponibilita.class);
                startActivity(intent);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.appuntamenti, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if(id== R.id.dates){

        }else if (id == R.id.availability) {
            Intent intent = new Intent(Appuntamenti.this, Disponibilita.class);
            startActivity(intent);
        } else if (id == R.id.new_request) {
            Intent intent = new Intent(Appuntamenti.this, NuoveRichieste.class);
            startActivity(intent);
        } else if (id == R.id.history) {
            Intent intent = new Intent(Appuntamenti.this, StoricoAttivita.class);
            startActivity(intent);
        } else if (id == R.id.received_request) {
            Intent intent = new Intent(Appuntamenti.this, RichiesteRicevute.class);
            startActivity(intent);
        } else if (id == R.id.account) {
            Intent intent = new Intent(Appuntamenti.this, Profilo.class);
            startActivity(intent);
        } else if (id == R.id.rating) {
            Intent intent = new Intent(Appuntamenti.this, Valutazione.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
