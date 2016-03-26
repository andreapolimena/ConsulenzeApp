package com.example.andreapolimena.consulenzeapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
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

public class ValutaTutor extends AppCompatActivity {

    boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_valuta_tutor);

        TextView nomeECognomeTutor = (TextView) findViewById(R.id.textView29);
        nomeECognomeTutor.setText(new StringBuilder().append(getIntent().getStringExtra("nomeTutor")).append(" ").append(getIntent().getStringExtra("cognomeTutor")).toString());
        final RatingBar ratingBar = (RatingBar)findViewById(R.id.ratingBar5);

        Button invia = (Button)findViewById(R.id.button11);
        invia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            JSONObject json = new JSONObject();
                            String serverUrl = "http://andreapolimena2.altervista.org/script_php/InsValutazione.php";
                            URL url = new URL(serverUrl);
                            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                            conn.setReadTimeout(10000);
                            conn.setConnectTimeout(15000);
                            conn.setRequestMethod("PUT");
                            conn.setDoOutput(true);

                            json.put("emailUtente", Inizio.utenteLoggato);
                            json.put("nomeTutor", getIntent().getStringExtra("nomeTutor"));
                            json.put("cognomeTutor", getIntent().getStringExtra("cognomeTutor"));
                            json.put("valutazione", ratingBar.getRating());

                            OutputStream os = conn.getOutputStream();
                            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                            String data = json.toString();

                            writer.write(data);
                            writer.flush();
                            writer.close();
                            conn.connect();

                            int responsecode=conn.getResponseCode();
                            if(responsecode==200){
                                flag=true;
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
                while (!flag){

                }
                Toast.makeText(ValutaTutor.this, "Valutazione inserita correttamente", Toast.LENGTH_LONG).show();
                finish();
            }
        });

    }
}
