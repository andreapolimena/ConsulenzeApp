package com.example.andreapolimena.consulenzeapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Registrazione extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrazione);

        android.support.v7.app.ActionBar ab = getSupportActionBar();
        ab.setTitle(R.string.register);

        Button mRegister = (Button)findViewById(R.id.button2);
        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Registrazione.this, "Registrazione avvenuta", Toast.LENGTH_LONG).show();
                Intent login = new Intent(Registrazione.this, Inizio.class);
                startActivity(login);
            }
        });

    }
}