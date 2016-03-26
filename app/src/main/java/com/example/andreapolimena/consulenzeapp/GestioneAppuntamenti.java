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

public class GestioneAppuntamenti extends AppCompatActivity {

    boolean flag=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestione_appuntamenti);

        final TextView specializzazioneView = (TextView)findViewById(R.id.textView34);
        final TextView giornoView = (TextView)findViewById(R.id.textView35);
        final TextView oraInizioView = (TextView)findViewById(R.id.textView36);
        TextView oraFineView = (TextView)findViewById(R.id.textView37);


        specializzazioneView.setText(getIntent().getStringExtra("specializzazione"));
        giornoView.setText(getIntent().getStringExtra("giorno"));
        oraInizioView.setText(getIntent().getStringExtra("oraInizio"));
        oraFineView.setText(getIntent().getStringExtra("nome")+" "+getIntent().getStringExtra("cognome"));

        Button termina = (Button)findViewById(R.id.button21);
        termina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            JSONObject json = new JSONObject();
                            String serverUrl = "http://andreapolimena2.altervista.org/script_php/TerminaAppuntamento.php";
                            URL url = new URL(serverUrl);
                            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                            conn.setReadTimeout(10000);
                            conn.setConnectTimeout(15000);
                            conn.setRequestMethod("PUT");
                            conn.setDoOutput(true);

                            json.put("email", Inizio.utenteLoggato);
                            json.put("dataInizio", giornoView.getText().toString());
                            json.put("oraInizio", oraInizioView.getText().toString());

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

                Toast.makeText(GestioneAppuntamenti.this, "Appuntamento terminato", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(GestioneAppuntamenti.this, Appuntamenti.class);
                startActivity(intent);
            }
        });

    }
}
