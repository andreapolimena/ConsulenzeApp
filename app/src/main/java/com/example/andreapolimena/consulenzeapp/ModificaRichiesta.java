package com.example.andreapolimena.consulenzeapp;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
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

public class ModificaRichiesta extends AppCompatActivity {

    public boolean flag = false;
    public static String dataVecchia = "";
    public static String oraInizioVecchia = "";
    public static String oraFineVecchia = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifica_richiesta);

        final EditText specializzazione = (EditText)findViewById(R.id.specializzazione);
        final Button selectData = (Button) findViewById(R.id.date);
        final Button oraInizio = (Button)findViewById(R.id.oraInizio);
        final Button oraFine =(Button)findViewById(R.id.oraFine);
        Button modifica = (Button) findViewById(R.id.Modifica);
        Button cancella = (Button)findViewById(R.id.Cancella);

        specializzazione.setEnabled(false);

        specializzazione.setText(getIntent().getStringExtra("specializzazione"));
        selectData.setText(getIntent().getStringExtra("giorno"));
        oraInizio.setText(getIntent().getStringExtra("oraInizio"));
        oraFine.setText(getIntent().getStringExtra("oraFine"));
        dataVecchia = getIntent().getStringExtra("giorno");
        oraInizioVecchia = getIntent().getStringExtra("oraInizio");
        oraFineVecchia = getIntent().getStringExtra("oraFine");


        selectData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new DatePickerFragmentMOD();
                newFragment.show(getFragmentManager(), "datePicker");
            }
        });


        oraInizio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new TimePickerFragmentInizioMOD();
                newFragment.show(getFragmentManager(), "timePicker");
            }
        });

        oraFine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new TimePickerFragmentFineMOD();
                newFragment.show(getFragmentManager(), "timePicker");
            }
        });


        modifica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!specializzazione.getText().toString().isEmpty() &&
                        selectData.getText().toString().contains("-") &&
                        oraFine.getText().toString().contains(":") &&
                        oraInizio.getText().toString().contains(":")) {

                    Thread thread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                JSONObject json = new JSONObject();
                                String serverUrl = "http://andreapolimena2.altervista.org/script_php/EditRichiesta.php";
                                URL url = new URL(serverUrl);
                                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                                conn.setReadTimeout(10000);
                                conn.setConnectTimeout(15000);
                                conn.setRequestMethod("PUT");
                                conn.setDoOutput(true);

                                json.put("email", Inizio.utenteLoggato);
                                json.put("dataRichiesta", dataVecchia);
                                json.put("oraInizio", oraInizioVecchia);
                                json.put("oraFine", oraFineVecchia);
                                json.put("dataRichiestaMOD", selectData.getText().toString());
                                json.put("oraInizioMOD", oraInizio.getText().toString());
                                json.put("oraFineMOD", oraFine.getText().toString());

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
                    Toast.makeText(ModificaRichiesta.this, "Richiesta modificata correttamente", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(ModificaRichiesta.this, NuoveRichieste.class);
                    startActivity(intent);
                }
            }
        });

        cancella.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            JSONObject json = new JSONObject();
                            String serverUrl = "http://andreapolimena2.altervista.org/script_php/DelRichiesta.php";
                            URL url = new URL(serverUrl);
                            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                            conn.setReadTimeout(10000);
                            conn.setConnectTimeout(15000);
                            conn.setRequestMethod("PUT");
                            conn.setDoOutput(true);

                            json.put("email", Inizio.utenteLoggato);
                            json.put("nomeTutor", getIntent().getStringExtra("nome"));
                            json.put("cognomeTutor", getIntent().getStringExtra("cognome"));
                            json.put("dataRichiesta", getIntent().getStringExtra("giorno"));
                            json.put("oraInizio", getIntent().getStringExtra("oraInizio"));

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
                Intent intent = new Intent(ModificaRichiesta.this, NuoveRichieste.class);
                startActivity(intent);
            }
        });
    }
}
