package com.example.andreapolimena.consulenzeapp;

import android.content.Intent;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class NuoveRichieste extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public ListView listView;
    public ArrayList<NuoveRichiesteClass> listRich = new ArrayList<NuoveRichiesteClass>();
    public boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuove_richieste);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listView = (ListView) findViewById(R.id.listView5);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    JSONObject json = new JSONObject();
                    String serverUrl = "http://andreapolimena2.altervista.org/script_php/NuovaRichiesta.php";
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
                    JSONObject jsonObject = new JSONObject(response);
                    int k = 0;
                    while (k < jsonObject.length()) {
                        NuoveRichiesteClass richiesta = new NuoveRichiesteClass(
                                jsonObject.getJSONObject(((Integer) k).toString()).getString("nome"),
                                jsonObject.getJSONObject(((Integer) k).toString()).getString("cognome"),
                                jsonObject.getJSONObject(((Integer) k).toString()).getString("specializzazione"),
                                jsonObject.getJSONObject(((Integer) k).toString()).getString("data_richiesta"),
                                jsonObject.getJSONObject(((Integer) k).toString()).getString("ora_inizio"),
                                jsonObject.getJSONObject(((Integer) k).toString()).getString("ora_fine")
                        );
                        if (!listRich.contains(richiesta))
                            listRich.add(richiesta);
                        k++;
                    }
                    flag = true;
                    isw.close();
                    conn.disconnect();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();

        while (!flag) {
            listView.invalidateViews();
        }

        ListAdapterNuoveRichClass listAdapterRichiesta = new ListAdapterNuoveRichClass(this, R.layout.list_item_nuove_rich, listRich);
        listView.setAdapter(listAdapterRichiesta);
        listView.invalidateViews();
        listView.refreshDrawableState();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NuoveRichieste.this, InserimentoRichiesta.class);
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
        getMenuInflater().inflate(R.menu.nuove_richieste, menu);
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
            Intent intent = new Intent(NuoveRichieste.this, Appuntamenti.class);
            startActivity(intent);
        } else if (id == R.id.availability) {
            Intent intent = new Intent(NuoveRichieste.this, Disponibilita.class);
            startActivity(intent);
        } else if (id == R.id.new_request) {

        } else if (id == R.id.history) {
            Intent intent = new Intent(NuoveRichieste.this, StoricoAttivita.class);
            startActivity(intent);
        } else if (id == R.id.received_request) {
            Intent intent = new Intent(NuoveRichieste.this, RichiesteRicevute.class);
            startActivity(intent);
        } else if (id == R.id.account) {
            Intent intent = new Intent(NuoveRichieste.this, Profilo.class);
            startActivity(intent);
        } else if (id == R.id.rating) {
            Intent intent = new Intent(NuoveRichieste.this, Valutazione.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
