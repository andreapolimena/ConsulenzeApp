package com.example.andreapolimena.consulenzeapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
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
import java.sql.Date;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class InserimentoRichiesta2 extends AppCompatActivity {

    public boolean flag = false;
    public ArrayList<InserimentoRichiestaClass> listUtente = new ArrayList<InserimentoRichiestaClass>();
    public ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserimento_richiesta2);

        listView = (ListView) findViewById(R.id.listView3);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    JSONObject json = new JSONObject();
                    String serverUrl = "http://andreapolimena2.altervista.org/script_php/DisponibilitaAltri.php";
                    URL url = new URL(serverUrl);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setReadTimeout(10000);
                    conn.setConnectTimeout(15000);
                    conn.setRequestMethod("POST");
                    conn.setDoOutput(true);

                    String key = "email";
                    String value = Inizio.utenteLoggato;
                    json.put(key, value);

                    key = "specializzazione";
                    value = InserimentoRichiesta.Spec;
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
                        String nome = jsonObject.getJSONObject(((Integer) k).toString()).getString("nome");
                        String cognome = jsonObject.getJSONObject(((Integer) k).toString()).getString("cognome");
                        String spec = jsonObject.getJSONObject(((Integer) k).toString()).getString("specializzazione");
                        String date = jsonObject.getJSONObject(((Integer) k).toString()).getString("data_inizio");
                        String oraInizio = jsonObject.getJSONObject(((Integer) k).toString()).getString("ora_inizio");
                        String oraFine = jsonObject.getJSONObject(((Integer) k).toString()).getString("ora_fine");
                        InserimentoRichiestaClass inserimentoRichiestaClass = new InserimentoRichiestaClass(nome,
                                cognome,
                                spec,
                                date,
                                oraInizio,
                                oraFine);

                        if (!listUtente.contains(inserimentoRichiestaClass))
                            listUtente.add(inserimentoRichiestaClass);
                        k++;
                    }
                    flag=true;
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
        while (!flag){
            listView.invalidateViews();
        }
        ListAdapterInsRichieste listAdapterInsRichieste = new ListAdapterInsRichieste(this, R.layout.item_registrazione2, listUtente);
        listView.setAdapter(listAdapterInsRichieste);

    }
}
