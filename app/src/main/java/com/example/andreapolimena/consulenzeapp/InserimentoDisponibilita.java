package com.example.andreapolimena.consulenzeapp;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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

public class InserimentoDisponibilita extends AppCompatActivity {
    public boolean flag=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserimento_disponibilita);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final RadioButton radioButton = (RadioButton)findViewById(R.id.radiobutton);
        final RadioButton radioButton1 = (RadioButton)findViewById(R.id.radiobutton2);
        RadioButton radioButton2 = (RadioButton)findViewById(R.id.radiobutton3);
        radioButton.toggle();
        final EditText editText = (EditText)findViewById(R.id.editText7);
        final Button button = (Button) findViewById(R.id.button3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getFragmentManager(), "datePicker");
            }
        });

        final Button button1 = (Button)findViewById(R.id.button4);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new TimePickerFragmentInizio();
                newFragment.show(getFragmentManager(), "timePicker");
            }
        });
        final Button button2=(Button)findViewById(R.id.button5);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new TimePickerFragmentFine();
                newFragment.show(getFragmentManager(),"timePicker");
            }
        });

        Button button3 = (Button)findViewById(R.id.button6);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!editText.getText().toString().isEmpty()&&
                        button.getText().toString().contains("-")&&
                        button1.getText().toString().contains(":")&&
                        button2.getText().toString().contains(":")){

                    Thread thread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                JSONObject json = new JSONObject();
                                String serverUrl = "http://andreapolimena2.altervista.org/script_php/InsDisponibilita.php";
                                URL url = new URL(serverUrl);
                                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                                conn.setReadTimeout(10000);
                                conn.setConnectTimeout(15000);
                                conn.setRequestMethod("PUT");
                                conn.setDoOutput(true);

                                json.put("email", Inizio.utenteLoggato);

                                json.put("dataInizio", button.getText().toString());
                                json.put("oraInizio", button1.getText().toString());
                                json.put("oraFine", button2.getText().toString());
                                json.put("dataFine", "2017-03-09");
                                json.put("specializzazione", editText.getText().toString());
                                if(radioButton.isChecked()){
                                    json.put("ripetizione", 0);
                                }else if(radioButton1.isChecked()){
                                    json.put("ripetizione", 1);
                                }else {
                                    json.put("ripetizione", 2);
                                }

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


                    Toast.makeText(InserimentoDisponibilita.this, "Disponibilità inserita correttamente", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(InserimentoDisponibilita.this, Disponibilita.class);
                    startActivity(intent);
                }
            }
        });
    }

    public void onRadioButtonClicked(View view) {

        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radiobutton:
                if (checked)
                    break;
            case R.id.radiobutton2:
                if (checked)
                    // Ninjas rule
                    break;
            case R.id.radiobutton3:
                if (checked)

                    break;
        }
    }

}
