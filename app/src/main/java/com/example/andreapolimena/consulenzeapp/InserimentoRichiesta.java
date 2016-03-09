package com.example.andreapolimena.consulenzeapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.*;

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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;

public class InserimentoRichiesta extends AppCompatActivity {

    public static String Spec;
    public ArrayList<String> listSpec = new ArrayList<String>();
    public ListView listView;
    public boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserimento_richiesta);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.insert_request);

        listView = (ListView) findViewById(R.id.listView);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    JSONObject json = new JSONObject();
                    String serverUrl = "http://andreapolimena2.altervista.org/script_php/Specializzazione.php";
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
                    int k = 0;
                    while (k < jsonObject.length()) {
                        String spec = jsonObject.getJSONObject(((Integer) k).toString()).getString("specializzazione");
                        if (!listSpec.contains(spec))
                            listSpec.add(spec);
                        k++;
                    }
                    flag=true;
                    isw.close();
                    conn.disconnect();

                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent intent;
                            switch (position) {
                                case 0:
                                    Spec = listSpec.get(position);

                                    intent = new Intent(InserimentoRichiesta.this, InserimentoRichiesta2.class);
                                    startActivity(intent);
                                    break;
                                default:
                                    Spec = listSpec.get(position);

                                    intent = new Intent(InserimentoRichiesta.this, InserimentoRichiesta2.class);
                                    startActivity(intent);
                                    break;
                                //add more if you have more items in listview
                                //0 is the first item 1 second and so on...
                            }
                        }
                    });
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
        ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.riga_specializ, R.id.textViewList, listSpec);
        listView.setAdapter(listAdapter);
        listView.invalidateViews();
        listView.refreshDrawableState();

    }
}
