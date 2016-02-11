package com.example.andreapolimena.consulenzeapp;

import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

/**
 * Created by andreapolimena on 10/02/16.
 */
public class ListAdapter extends ArrayAdapter<Utente> {

    public ListAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public ListAdapter(Context context, int resource, List<Utente> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.list_item, null);
        }
        //Utente
        Utente p = getItem(position);

        if (p != null) {
            TextView data = (TextView) v.findViewById(R.id.textView2);
            TextView nomeCognome = (TextView) v.findViewById(R.id.textView3);
            TextView tipoConsulenza = (TextView) v.findViewById(R.id.textView4);
            TextView ora = (TextView) v.findViewById(R.id.textView5);
            RatingBar ratingBar = (RatingBar) v.findViewById(R.id.ratingBar);

            if (data != null) {
                data.setText("12/12/1223");
            }

            if (nomeCognome != null) {
                nomeCognome.setText(p.getNome()+ " "+p.getCognome());
            }

            if (tipoConsulenza != null) {
                tipoConsulenza.setText(p.getPrimaSpec());
            }
            if(ora!= null){
                ora.setText("10:00");
            }
            ratingBar.setStepSize((float)0.1);
            ratingBar.setRating((float) 2.5);
            ratingBar.setEnabled(false);
        }

        return v;
    }

}