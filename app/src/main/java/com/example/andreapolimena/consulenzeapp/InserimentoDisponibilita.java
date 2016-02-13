package com.example.andreapolimena.consulenzeapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class InserimentoDisponibilita extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserimento_disponibilita);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final Button dataPick = (Button) findViewById(R.id.button3);
        //final Calendar calendar = Calendar.getInstance();
        //DatePickerDialog datePickerDialog = new DatePickerDialog(getCallingActivity(), this, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        dataPick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDataPicker dataPicker = new MyDataPicker();
                dataPicker.doDataPicker(v);
            }
        });
    }

}
