package com.example.andreapolimena.consulenzeapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by andreapolimena on 10/02/16.
 */
public class CustomAdapter extends ArrayAdapter<Utente>{
    public CustomAdapter(Context context, int textViewResourceId, Utente[] utentes){
        super(context, textViewResourceId, utentes);
    }

    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView=inflater.inflate(R.layout.list_item_appuntamenti, null);
        TextView nome = (TextView)convertView.findViewById(R.id.textView);
        TextView cognome = (TextView)convertView.findViewById(R.id.textView2);
        TextView email = (TextView)convertView.findViewById(R.id.textView3);
        TextView indirizzo = (TextView)convertView.findViewById(R.id.textView4);
        Utente u = new Utente();
        nome.setText(u.getNome());
        cognome.setText(u.getCognome());
        email.setText(u.getEmail());
        indirizzo.setText(u.getIndirizzo());
        return convertView;
    }

}
