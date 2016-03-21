package com.example.andreapolimena.consulenzeapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class GestioneDisponibilita extends AppCompatActivity {

    public boolean flag=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestione_disponibilita);

        final TextView specializzazioneView = (TextView)findViewById(R.id.textView23);
        final TextView giornoView = (TextView)findViewById(R.id.textView24);
        final TextView oraInizioView = (TextView)findViewById(R.id.textView25);
        TextView oraFineView = (TextView)findViewById(R.id.textView26);


        specializzazioneView.setText(getIntent().getStringExtra("specializzazione"));
        giornoView.setText(getIntent().getStringExtra("giorno"));
        oraInizioView.setText(getIntent().getStringExtra("oraInizio"));
        oraFineView.setText(getIntent().getStringExtra("oraFine"));

        Button Delete = (Button)findViewById(R.id.button10);
        Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            JSONObject json = new JSONObject();
                            String serverUrl = "http://andreapolimena2.altervista.org/script_php/DelDisponibilita.php";
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

                            if (conn.getResponseCode() == 200) {
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
                Intent intent = new Intent(GestioneDisponibilita.this, Disponibilita.class);
                startActivity(intent);
            }
        });


        Button Edit = (Button)findViewById(R.id.button9);

        Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(GestioneDisponibilita.this, ModificaDisponibilita.class);
                intent.putExtra("bundle",getIntent().getExtras());
                startActivity(intent);
            }
        });

    }
}
