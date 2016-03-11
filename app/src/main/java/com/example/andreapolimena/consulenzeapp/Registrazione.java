package com.example.andreapolimena.consulenzeapp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Registrazione extends AppCompatActivity {

    public EditText nome;
    public EditText cognome;
    public EditText email;
    public EditText indirizzo;
    public EditText primaSpec;
    public EditText secondaSpec;
    public EditText password;
    public EditText passwordTemp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrazione);

        android.support.v7.app.ActionBar ab = getSupportActionBar();
        ab.setTitle(R.string.register);

        nome = (EditText) findViewById(R.id.editText);
        cognome = (EditText) findViewById(R.id.editText2);
        email = (EditText) findViewById(R.id.editText3);
        indirizzo = (EditText) findViewById(R.id.editText4);
        primaSpec = (EditText) findViewById(R.id.editText5);
        secondaSpec = (EditText) findViewById(R.id.editText6);
        password = (EditText) findViewById(R.id.editText8);
        passwordTemp = (EditText) findViewById(R.id.editText9);

        Button mRegister = (Button) findViewById(R.id.button2);
        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (nome.getText().toString().isEmpty()) {
                    Toast.makeText(Registrazione.this, "Nome non inserito correttamente, prego reinserirlo", Toast.LENGTH_LONG).show();
                } else if (cognome.getText().toString().isEmpty()) {
                    Toast.makeText(Registrazione.this, "Cognome non inserito correttamente, prego reinserirlo", Toast.LENGTH_LONG).show();
                } else if (email.getText().toString().contains("@") == false) {
                    Toast.makeText(Registrazione.this, "Email non inserita correttamente, prego reinserla", Toast.LENGTH_LONG).show();
                } else if (indirizzo.getText().toString().isEmpty()) {
                    Toast.makeText(Registrazione.this, "Indirizzo non inserito correttamente, prego reinserirlo", Toast.LENGTH_LONG).show();
                } else if (primaSpec.getText().toString().isEmpty()) {
                    Toast.makeText(Registrazione.this, "Specializzazione non inserita correttamente, prego reinserirla", Toast.LENGTH_LONG).show();
                } else if (secondaSpec.getText().toString().isEmpty()) {
                    Toast.makeText(Registrazione.this, "Specializzazione non inserita correttamente, prego reinserirla", Toast.LENGTH_LONG).show();
                } else if (password.getText().toString().equals(passwordTemp.getText().toString()) == false || password.getText().toString().isEmpty() == true) {
                    Toast.makeText(Registrazione.this, "Password non inserita correttamente, prego reinserirlo", Toast.LENGTH_LONG).show();
                } else {

                    Thread thread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                JSONObject json = new JSONObject();
                                String serverUrl = "http://andreapolimena2.altervista.org/script_php/RegistrazioneUtente.php";
                                URL url = new URL(serverUrl);
                                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                                conn.setReadTimeout(10000);
                                conn.setConnectTimeout(15000);
                                conn.setRequestMethod("PUT");
                                conn.setDoOutput(true);

                                json.put("nome", nome.getText().toString());
                                json.put("cognome", cognome.getText().toString());
                                json.put("email", email.getText().toString());
                                json.put("pass", password.getText().toString());
                                json.put("indirizzo", indirizzo.getText().toString());
                                json.put("specializzazione", primaSpec.getText().toString());
                                json.put("secondaria", secondaSpec.getText().toString());

                                OutputStream os = conn.getOutputStream();
                                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                                String data = json.toString();

                                writer.write(data);
                                writer.flush();
                                writer.close();
                                conn.connect();
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
                    Intent login = new Intent(Registrazione.this, Inizio.class);
                    startActivity(login);
                }
            }
        });

    }
}
