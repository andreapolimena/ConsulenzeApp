package com.example.andreapolimena.consulenzeapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by andreapolimena on 10/02/16.
 */
public class ListAdapterInsRichieste extends ArrayAdapter<InserimentoRichiestaClass> {

    public boolean flag = false;

    public ListAdapterInsRichieste(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public ListAdapterInsRichieste(Context context, int resource, List<InserimentoRichiestaClass> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, final View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.item_registrazione2, null);
        }
        //Utente
        InserimentoRichiestaClass p = getItem(position);

        if (p != null) {
            final TextView dataEOra = (TextView) v.findViewById(R.id.textView8);
            final TextView nomeCognome = (TextView) v.findViewById(R.id.textView10);
            TextView valutazione = (TextView) v.findViewById(R.id.textView9);
            RatingBar ratingBar2 = (RatingBar) v.findViewById(R.id.ratingBar2);

            Button button7 = (Button)v.findViewById(R.id.button7);
            button7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Thread thread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                JSONObject json = new JSONObject();
                                String serverUrl = "http://andreapolimena2.altervista.org/script_php/InsNuovaRichiesta.php";
                                URL url = new URL(serverUrl);
                                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                                conn.setReadTimeout(10000);
                                conn.setConnectTimeout(15000);
                                conn.setRequestMethod("PUT");
                                conn.setDoOutput(true);
                                String[] temp = nomeCognome.getText().toString().split(" ");
                                json.put("email", Inizio.utenteLoggato);
                                json.put("nomeTutor", temp[0]);
                                json.put("cognomeTutor", temp[1]);
                                temp = dataEOra.getText().toString().split(" ");
                                json.put("data", temp[0]);
                                json.put("oraInizio", temp[1]);
                                json.put("oraFine", temp[3]);
                                json.put("specializzazione", InserimentoRichiesta.Spec);

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
                    Toast.makeText(v.getContext(), "Richiesta inviata correttamente",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(v.getContext(), NuoveRichieste.class);
                    v.getContext().startActivity(intent);
                }
            });

            ratingBar2.setStepSize((float)0.1);
            ratingBar2.setNumStars(5);
            ratingBar2.setRating((float)4.8);

            if (dataEOra != null) {
                dataEOra.setText(p.getDate()+" "+p.getOra()+" -> "+p.getOraFine());
            }

            if (nomeCognome != null) {
                nomeCognome.setText(p.getNome()+ " "+p.getCognome());
            }
            if(valutazione!= null){
                valutazione.setText("Valutazione: 4.8");
            }

        }

        return v;
    }

}