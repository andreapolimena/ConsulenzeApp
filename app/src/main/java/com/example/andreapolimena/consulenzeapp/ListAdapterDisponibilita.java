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
public class ListAdapterDisponibilita extends ArrayAdapter<DisponibilitaClass> {

    public ListAdapterDisponibilita(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public ListAdapterDisponibilita(Context context, int resource, List<DisponibilitaClass> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.list_item_disponib, null);
        }

        DisponibilitaClass p = getItem(position);

        if (p != null) {
            TextView data = (TextView) v.findViewById(R.id.textView2);
            TextView nomeCognome = (TextView) v.findViewById(R.id.textView3);
            TextView tipoConsulenza = (TextView) v.findViewById(R.id.textView4);
            TextView ora = (TextView) v.findViewById(R.id.textView12);
            TextView oraFine = (TextView) v.findViewById(R.id.textView13);

            if (data != null) {
                data.setText(p.getDate());
            }

            if (nomeCognome != null) {
                nomeCognome.setText(p.getNome()+ " "+p.getCognome());
            }

            if (tipoConsulenza != null) {
                tipoConsulenza.setText(p.getSpec());
            }
            if(ora!= null){
                ora.setText(p.getOra());
            }
            if(oraFine!=null) {
                oraFine.setText(p.getOraFine());
            }

        }
        return v;
    }

}