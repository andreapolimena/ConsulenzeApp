package com.example.andreapolimena.consulenzeapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class GestioneRichieste extends AppCompatActivity {

    boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestione_richieste);

        final TextView specializzazioneView = (TextView) findViewById(R.id.textView54);
        final TextView giornoView = (TextView) findViewById(R.id.textView55);
        final TextView oraInizioView = (TextView) findViewById(R.id.textView56);
        final TextView oraFine = (TextView) findViewById(R.id.textView59);
        TextView oraFineView = (TextView) findViewById(R.id.textView57);
        final String nomeUtente = getIntent().getStringExtra("nome");
        final String cognomeUtente = getIntent().getStringExtra("cognome");

        specializzazioneView.setText(getIntent().getStringExtra("specializzazione"));
        giornoView.setText(getIntent().getStringExtra("giorno"));
        oraInizioView.setText(getIntent().getStringExtra("oraInizio"));
        oraFineView.setText(getIntent().getStringExtra("nome") + " " + getIntent().getStringExtra("cognome"));
        String temp = getIntent().getStringExtra("oraInizio");
        String[] oraDivisi = temp.split(" -> ");
        oraInizioView.setText(oraDivisi[0]);
        oraFine.setText(oraDivisi[1]);

        Button accept = (Button) findViewById(R.id.button50);
        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            JSONObject json = new JSONObject();
                            String serverUrl = "http://andreapolimena2.altervista.org/script_php/InsAppuntamenti.php";
                            URL url = new URL(serverUrl);
                            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                            conn.setReadTimeout(10000);
                            conn.setConnectTimeout(15000);
                            conn.setRequestMethod("PUT");
                            conn.setDoOutput(true);

                            json.put("email", Inizio.utenteLoggato);
                            json.put("nomeUtente", nomeUtente);
                            json.put("cognomeUtente", cognomeUtente);
                            json.put("data", giornoView.getText().toString());
                            json.put("oraInizio", oraInizioView.getText().toString());
                            json.put("oraFine", oraFine.getText().toString());
                            json.put("specializzazione", specializzazioneView.getText());

                            OutputStream os = conn.getOutputStream();
                            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                            String data = json.toString();

                            writer.write(data);
                            writer.flush();
                            writer.close();
                            conn.connect();

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

                Toast.makeText(GestioneRichieste.this, "La richiesta è stata inserita negli appuntamenti", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(GestioneRichieste.this, RichiesteRicevute.class);
                startActivity(intent);
            }
        });

        Button deny = (Button) findViewById(R.id.button51);
        deny.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            JSONObject json = new JSONObject();
                            String serverUrl = "http://andreapolimena2.altervista.org/script_php/RifiutaRichiesta.php";
                            URL url = new URL(serverUrl);
                            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                            conn.setReadTimeout(10000);
                            conn.setConnectTimeout(15000);
                            conn.setRequestMethod("PUT");
                            conn.setDoOutput(true);

                            json.put("email", Inizio.utenteLoggato);
                            json.put("nomeUtente", nomeUtente);
                            json.put("cognomeUtente", cognomeUtente);
                            json.put("data", giornoView.getText().toString());
                            json.put("oraInizio", oraInizioView.getText().toString());
                            json.put("oraFine", oraFine.getText().toString());
                            json.put("specializzazione", specializzazioneView.getText());

                            OutputStream os = conn.getOutputStream();
                            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                            String data = json.toString();

                            writer.write(data);
                            writer.flush();
                            writer.close();
                            conn.connect();

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

                Toast.makeText(GestioneRichieste.this, "La richiesta è stata rifiutata", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(GestioneRichieste.this, RichiesteRicevute.class);
                startActivity(intent);
            }
        });

    }
}
