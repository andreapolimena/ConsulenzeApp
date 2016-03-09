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
public class ListAdapterNuoveRichClass extends ArrayAdapter<NuoveRichiesteClass> {

    public ListAdapterNuoveRichClass(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public ListAdapterNuoveRichClass(Context context, int resource, List<NuoveRichiesteClass> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.list_item_nuove_rich, null);
        }

        NuoveRichiesteClass p = getItem(position);

        if (p != null) {
            TextView data = (TextView) v.findViewById(R.id.textView2);
            TextView nomeCognome = (TextView) v.findViewById(R.id.textView3);
            TextView tipoConsulenza = (TextView) v.findViewById(R.id.textView4);
            TextView oraInizio = (TextView) v.findViewById(R.id.textView5);
            TextView oraFine = (TextView) v.findViewById(R.id.textView6);

            if (data != null) {
                data.setText(p.getDate());
            }

            if (nomeCognome != null) {
                nomeCognome.setText(p.getNome()+ " "+p.getCognome());
            }

            if (tipoConsulenza != null) {
                tipoConsulenza.setText(p.getSpec());
            }
            if(oraInizio!= null){
                oraInizio.setText(p.getOra_inizio());
            }
            if(oraFine!=null){
                oraFine.setText(p.getOra_fine());
            }
        }
        return v;
    }

}