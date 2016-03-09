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

import java.util.List;

/**
 * Created by andreapolimena on 10/02/16.
 */
public class ListAdapterInsRichieste extends ArrayAdapter<InserimentoRichiestaClass> {

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
            TextView dataEOra = (TextView) v.findViewById(R.id.textView8);
            TextView nomeCognome = (TextView) v.findViewById(R.id.textView10);
            TextView valutazione = (TextView) v.findViewById(R.id.textView9);
            RatingBar ratingBar2 = (RatingBar) v.findViewById(R.id.ratingBar2);

            Button button7 = (Button)v.findViewById(R.id.button7);
            button7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
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