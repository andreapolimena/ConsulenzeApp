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
    public static int ripetizione;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifica_disponibilita);

        final RadioButton radioButton4 = (RadioButton)findViewById(R.id.radiobutton4);
        final RadioButton radioButton5 = (RadioButton)findViewById(R.id.radiobutton5);
        final RadioButton radioButton6 = (RadioButton)findViewById(R.id.radiobutton6);
        final EditText specializzazione = (EditText)findViewById(R.id.specializzazione);
        final Button selectData = (Button) findViewById(R.id.date);
        final Button oraInizio = (Button)findViewById(R.id.oraInizio);
        final Button oraFine =(Button)findViewById(R.id.oraFine);
        Button modifica = (Button) findViewById(R.id.Modifica);
        radioButton4.toggle();
        specializzazione.setEnabled(false);

        specializzazione.setText(getIntent().getBundleExtra("bundle").getString("specializzazione"));
        selectData.setText(getIntent().getBundleExtra("bundle").getString("giorno"));
        oraInizio.setText(getIntent().getBundleExtra("bundle").getString("oraInizio"));
        oraFine.setText(getIntent().getBundleExtra("bundle").getString("oraFine"));
        dataVecchia = getIntent().getBundleExtra("bundle").getString("giorno");
        oraInizioVecchia = getIntent().getBundleExtra("bundle").getString("oraInizio");
        ripetizione = getIntent().getBundleExtra("bundle").getInt("ripetizione");

        if(ripetizione==0){
            radioButton4.toggle();
        }else if(ripetizione==1){
            radioButton5.toggle();
        }else if(ripetizione==2){
            radioButton6.toggle();
        }else {
            radioButton4.toggle();
        }

        radioButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ripetizione = 0;
            }
        });

        radioButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ripetizione = 1;
            }
        });

        radioButton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ripetizione = 2;
            }
        });

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
                                String serverUrl = "http://andreapolimena2.altervista.org/script_php/EditDisponibilita.php";
                                URL url = new URL(serverUrl);
                                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                                conn.setReadTimeout(10000);
                                conn.setConnectTimeout(15000);
                                conn.setRequestMethod("PUT");
                                conn.setDoOutput(true);

                                json.put("email", Inizio.utenteLoggato);
                                json.put("dataInizio", dataVecchia);
                                json.put("oraInizio", oraInizioVecchia);
                                json.put("dataInizioMOD", selectData.getText().toString());
                                json.put("oraInizioMOD", oraInizio.getText().toString());
                                json.put("oraFineMOD", oraFine.getText().toString());
                                json.put("dataFineMOD", "2017-03-09");
                                if (radioButton4.isChecked()) {
                                    json.put("ripetizione", 0);
                                } else if (radioButton5.isChecked()) {
                                    json.put("ripetizione", 1);
                                } else if (radioButton6.isChecked()) {
                                    json.put("ripetizione", 2);
                                } else {
                                    json.put("ripetizione", 0);
                                }

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

                    Toast.makeText(ModificaRichiesta.this, "Disponibilità modificata correttamente", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(ModificaRichiesta.this, Disponibilita.class);
                    startActivity(intent);
                }
            }
        });

    }
}
