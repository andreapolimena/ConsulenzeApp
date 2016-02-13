package com.example.andreapolimena.consulenzeapp;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

public class InserimentoDisponibilita extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserimento_disponibilita);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        RadioButton radioButton = (RadioButton)findViewById(R.id.radiobutton);
        radioButton.toggle();
        Button button = (Button) findViewById(R.id.button3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getFragmentManager(), "datePicker");
            }
        });

        Button button1 = (Button)findViewById(R.id.button4);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new TimePickerFragmentInizio();
                newFragment.show(getFragmentManager(), "timePicker");
            }
        });
        Button button2=(Button)findViewById(R.id.button5);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new TimePickerFragmentFine();
                newFragment.show(getFragmentManager(),"timePicker");
            }
        });

        Button button3 = (Button)findViewById(R.id.button6);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(InserimentoDisponibilita.this, "Disponibilità inserita correttamente", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(InserimentoDisponibilita.this, Disponibilita.class);
                startActivity(intent);
            }
        });
    }

    public void onRadioButtonClicked(View view) {

        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radiobutton:
                if (checked)
                    break;
            case R.id.radiobutton2:
                if (checked)
                    // Ninjas rule
                    break;
            case R.id.radiobutton3:
                if (checked)

                    break;
        }
    }

}
