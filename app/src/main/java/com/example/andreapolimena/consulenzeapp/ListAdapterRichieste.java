package com.example.andreapolimena.consulenzeapp;

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
public class ListAdapterRichieste extends ArrayAdapter<Utente> {

    public ListAdapterRichieste(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public ListAdapterRichieste(Context context, int resource, List<Utente> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.item_registrazione2, null);
        }
        //Utente
        Utente p = getItem(position);

        if (p != null) {
            TextView data = (TextView) v.findViewById(R.id.textView8);
            TextView nomeCognome = (TextView) v.findViewById(R.id.textView10);
            TextView ora = (TextView) v.findViewById(R.id.textView9);
            RatingBar ratingBar2 = (RatingBar) v.findViewById(R.id.ratingBar2);

            ratingBar2.setStepSize((float)0.1);
            ratingBar2.setNumStars(5);
            ratingBar2.setRating((float)4.8);

            if (data != null) {
                data.setText("12/12/2016");
            }

            if (nomeCognome != null) {
                nomeCognome.setText(p.getNome()+ " "+p.getCognome());
            }
            if(ora!= null){
                ora.setText("10:00");
            }

        }

        return v;
    }

}