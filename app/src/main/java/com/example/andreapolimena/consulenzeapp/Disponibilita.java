package com.example.andreapolimena.consulenzeapp;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Disponibilita extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disponibilita);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    JSONObject json = new JSONObject();
                    String serverUrl = "http://andreapolimena2.altervista.org/script_php/Accesso.php";
                    URL url = new URL(serverUrl);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setReadTimeout(10000);
                    conn.setConnectTimeout(15000);
                    conn.setRequestMethod("POST");
                    conn.setDoOutput(true);

                    ContentValues values = new ContentValues();

                    String key = "email";
                    String value = "email";
                    values.put(key, value);
                    json.put(key, value);

                    key = "pass";
                    value = "emaoil";
                    values.put(key, value);
                    json.put(key, value);

                    OutputStream os = conn.getOutputStream();
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                    String data = json.toString();

                    writer.write(data);
                    writer.flush();
                    writer.close();
                    conn.connect();

                    InputStreamReader isw = new InputStreamReader(conn.getInputStream());
                    int r;
                    char c;
                    String response = "";
                    while ((r = isw.read()) != -1) {
                        // int to character
                        c = (char) r;
                        response += c;
                    }

                    System.out.println(response);

                    isw.close();
                } catch (Exception e) {
                    Intent intent = new Intent(getApplicationContext(), NessunaConnessione.class);
                    startActivity(intent);
                }
            }
        });

        thread.start();


        ListView listView = (ListView) findViewById(R.id.listView4);
        List listDisponibilita = new LinkedList();
        listDisponibilita.add(new DisponibilitaClass("Andrea","Polimena", "Info",12,12,2016,11,0,12,0));
        ListAdapterDisponibilita listAdapterDisponibilita = new ListAdapterDisponibilita(this,R.layout.list_item_disponib,listDisponibilita);
        listView.setAdapter(listAdapterDisponibilita);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Disponibilita.this, InserimentoDisponibilita.class);
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
        getMenuInflater().inflate(R.menu.disponibilita, menu);
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
        if (id == R.id.dates) {
            Intent intent = new Intent(Disponibilita.this, Appuntamenti.class);
            startActivity(intent);
        } else if (id == R.id.availability) {

        } else if (id == R.id.new_request) {
            Intent intent = new Intent(Disponibilita.this, NuoveRichieste.class);
            startActivity(intent);
        } else if (id == R.id.history) {
            Intent intent = new Intent(Disponibilita.this, StoricoAttivita.class);
            startActivity(intent);
        } else if (id == R.id.received_request) {
            Intent intent = new Intent(Disponibilita.this, RichiesteRicevute.class);
            startActivity(intent);
        } else if (id == R.id.account) {
            Intent intent = new Intent(Disponibilita.this, Profilo.class);
            startActivity(intent);
        } else if (id == R.id.rating) {
            Intent intent = new Intent(Disponibilita.this, Valutazione.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
