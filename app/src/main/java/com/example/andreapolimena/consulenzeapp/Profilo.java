package com.example.andreapolimena.consulenzeapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class Profilo extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public boolean flag = false;
    public static String nomeT=null;
    public static String cognomeT=null;
    public static String emailT=null;
    public static String passwordT=null;
    public static String indirizzoT=null;
    public static String specializzazioneT=null;
    public static String secondariaT=null;
    public boolean flag2=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profilo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        final EditText nome = (EditText) findViewById(R.id.editText10);
        final EditText cognome = (EditText) findViewById(R.id.editText11);
        final EditText email = (EditText) findViewById(R.id.editText12);
        final EditText indirizzo = (EditText) findViewById(R.id.editText13);
        final EditText spec_princ = (EditText) findViewById(R.id.editText14);
        final EditText spec_sec = (EditText) findViewById(R.id.editText15);
        final EditText pass1 = (EditText) findViewById(R.id.editText16);
        final EditText pass2 = (EditText) findViewById(R.id.editText17);



        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    JSONObject json = new JSONObject();
                    String serverUrl = "http://andreapolimena2.altervista.org/script_php/EditUtente.php";
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

                    Profilo.nomeT = jsonObject.getString("nome");
                    Profilo.cognomeT = jsonObject.getString("cognome");
                    Profilo.emailT = jsonObject.getString("email");
                    Profilo.passwordT = jsonObject.getString("password");
                    Profilo.indirizzoT = jsonObject.getString("indirizzo");
                    Profilo.specializzazioneT = jsonObject.getString("specializzazione");
                    Profilo.secondariaT = jsonObject.getString("secondaria");
                    isw.close();
                    conn.disconnect();
                    flag2=true;

                } catch (UnsupportedEncodingException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (JSONException e1) {
                    e1.printStackTrace();
                }
            }
        });
        thread.start();
        while (!flag2){

        }

        nome.setText(nomeT);
        cognome.setText(cognomeT);
        email.setText(emailT);
        indirizzo.setText(indirizzoT);
        spec_princ.setText(specializzazioneT);
        spec_sec.setText(secondariaT);
        pass1.setText(passwordT);
        pass2.setText(passwordT);

        Button update = (Button) findViewById(R.id.button8);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nome.getText().toString().isEmpty()) {
                    Toast.makeText(Profilo.this, "Nome non inserito correttamente, prego reinserirlo", Toast.LENGTH_LONG).show();
                } else if (cognome.getText().toString().isEmpty()) {
                    Toast.makeText(Profilo.this, "Cognome non inserito correttamente, prego reinserirlo", Toast.LENGTH_LONG).show();
                } else if (email.getText().toString().contains("@") == false) {
                    Toast.makeText(Profilo.this, "Email non inserita correttamente, prego reinserla", Toast.LENGTH_LONG).show();
                } else if (indirizzo.getText().toString().isEmpty()) {
                    Toast.makeText(Profilo.this, "Indirizzo non inserito correttamente, prego reinserirlo", Toast.LENGTH_LONG).show();
                } else if (spec_princ.getText().toString().isEmpty()) {
                    Toast.makeText(Profilo.this, "Specializzazione non inserita correttamente, prego reinserirla", Toast.LENGTH_LONG).show();
                } else if (spec_sec.getText().toString().isEmpty()) {
                    Toast.makeText(Profilo.this, "Specializzazione non inserita correttamente, prego reinserirla", Toast.LENGTH_LONG).show();
                } else if (pass1.getText().toString().equals(pass2.getText().toString()) == false || pass1.getText().toString().isEmpty() == true) {
                    Toast.makeText(Profilo.this, "Password non inserita correttamente, prego reinserirlo", Toast.LENGTH_LONG).show();
                } else {

                    Thread thread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                JSONObject json = new JSONObject();
                                String serverUrl = "http://andreapolimena2.altervista.org/script_php/EditUtente.php";
                                URL url = new URL(serverUrl);
                                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                                conn.setReadTimeout(10000);
                                conn.setConnectTimeout(15000);
                                conn.setRequestMethod("PUT");
                                conn.setDoOutput(true);

                                json.put("utenteLoggato", Inizio.utenteLoggato);
                                json.put("nome", nome.getText().toString());
                                json.put("cognome", cognome.getText().toString());
                                json.put("email", email.getText().toString());
                                json.put("pass", pass1.getText().toString());
                                json.put("indirizzo", indirizzo.getText().toString());
                                json.put("specializzazione", spec_princ.getText().toString());
                                json.put("secondaria", spec_sec.getText().toString());

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

                                int responsecode = conn.getResponseCode();
                                if (responsecode == 200) {
                                    flag = true;
                                }
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

                    }
                    Toast.makeText(getApplicationContext(), "Dati aggiornati correttamente", Toast.LENGTH_SHORT).show();

                    Intent login = new Intent(Profilo.this, Appuntamenti.class);
                    startActivity(login);
                }
            }
        });

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
        getMenuInflater().inflate(R.menu.profilo, menu);
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
            Intent intent = new Intent(Profilo.this, Appuntamenti.class);
            startActivity(intent);
        } else if (id == R.id.availability) {
            Intent intent = new Intent(Profilo.this, Disponibilita.class);
            startActivity(intent);
        } else if (id == R.id.new_request) {
            Intent intent = new Intent(Profilo.this, NuoveRichieste.class);
            startActivity(intent);
        } else if (id == R.id.history) {
            Intent intent = new Intent(Profilo.this, StoricoAttivita.class);
            startActivity(intent);
        } else if (id == R.id.received_request) {
            Intent intent = new Intent(Profilo.this, RichiesteRicevute.class);
            startActivity(intent);
        } else if (id == R.id.account) {

        } else if (id == R.id.rating) {
            Intent intent = new Intent(Profilo.this, Valutazione.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
