package com.example.andreapolimena.consulenzeapp;

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
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class RichiesteRicevute extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public ArrayList<AppuntamentiClass> listAppuntamentiClass = new ArrayList<AppuntamentiClass>();
    public boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_richieste_ricevute);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        ListView listView = (ListView) findViewById(R.id.listView6);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    JSONObject json = new JSONObject();
                    String serverUrl = "http://andreapolimena2.altervista.org/script_php/RichiestePervenute.php";
                    URL url = new URL(serverUrl);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setReadTimeout(10000);
                    conn.setConnectTimeout(15000);
                    conn.setRequestMethod("POST");
                    conn.setDoOutput(true);


                    String key = "email";
                    String value = Inizio.utenteLoggato;
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
                    isw.close();
                    conn.disconnect();

                    JSONObject jsonObject = new JSONObject(response);
                    int k = 0;
                    while (k < jsonObject.length()) {

                        listAppuntamentiClass.add(new AppuntamentiClass(
                                jsonObject.getJSONObject(((Integer) k).toString()).getString("nome"),
                                jsonObject.getJSONObject(((Integer) k).toString()).getString("cognome"),
                                jsonObject.getJSONObject(((Integer) k).toString()).getString("specializzazione"),
                                jsonObject.getJSONObject(((Integer) k).toString()).getString("data_richiesta"),
                                jsonObject.getJSONObject(((Integer) k).toString()).getString("ora_inizio"),
                                (float) 2
                        ));
                        k++;
                    }

                    flag = true;
                    isw.close();
                    conn.disconnect();
                } catch (Exception e) {
                    Intent intent = new Intent(getApplicationContext(), NessunaConnessione.class);
                    startActivity(intent);
                }
            }
        });

        thread.start();


        while (!flag) {
            listView.invalidateViews();
        }

        ListAdapterAppuntamentiClass listAdapterAppuntamentiClass = new ListAdapterAppuntamentiClass(this, R.layout.list_item_appuntamenti, listAppuntamentiClass);
        listView.setAdapter(listAdapterAppuntamentiClass);
        listView.invalidateViews();
        listView.refreshDrawableState();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent;
                switch (position) {
                    default:
                        AppuntamentiClass appuntamentiClass = listAppuntamentiClass.get(position);
                        intent = new Intent(RichiesteRicevute.this, GestioneRichieste.class);
                        intent.putExtra("cognome", appuntamentiClass.getCognome());
                        intent.putExtra("nome", appuntamentiClass.getNome());
                        intent.putExtra("specializzazione", appuntamentiClass.getSpec());
                        intent.putExtra("giorno", appuntamentiClass.getDate());
                        intent.putExtra("oraInizio", appuntamentiClass.getOra());
                        startActivity(intent);
                        break;
                    //add more if you have more items in listview
                    //0 is the first item 1 second and so on...
                }
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
        getMenuInflater().inflate(R.menu.richieste_ricevute, menu);
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

        if(id==R.id.dates){
            Intent intent = new Intent(RichiesteRicevute.this, Appuntamenti.class);
            startActivity(intent);
        }else if (id == R.id.availability) {
            Intent intent = new Intent(RichiesteRicevute.this, Disponibilita.class);
            startActivity(intent);
        } else if (id == R.id.new_request) {
            Intent intent = new Intent(RichiesteRicevute.this, NuoveRichieste.class);
            startActivity(intent);
        } else if (id == R.id.history) {
            Intent intent = new Intent(RichiesteRicevute.this, StoricoAttivita.class);
            startActivity(intent);
        } else if (id == R.id.received_request) {

        } else if (id == R.id.account) {
            Intent intent = new Intent(RichiesteRicevute.this, Profilo.class);
            startActivity(intent);
        } else if (id == R.id.rating) {
            Intent intent = new Intent(RichiesteRicevute.this, Valutazione.class);
            startActivity(intent);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
