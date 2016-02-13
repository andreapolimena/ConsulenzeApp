package com.example.andreapolimena.consulenzeapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
                EditText nome= (EditText)findViewById(R.id.editText);
                EditText cognome= (EditText)findViewById(R.id.editText2);
                EditText email= (EditText)findViewById(R.id.editText3);
                EditText indirizzo= (EditText)findViewById(R.id.editText4);
                EditText primaSpec= (EditText)findViewById(R.id.editText5);
                EditText secondaSpec = (EditText)findViewById(R.id.editText6);
                EditText password= (EditText)findViewById(R.id.editText8);
                EditText passwordTemp = (EditText)findViewById(R.id.editText9);
                if(nome.getText().toString().isEmpty()) {
                    Toast.makeText(Registrazione.this, "Nome non inserito correttamente, prego reinserirlo", Toast.LENGTH_LONG).show();
                }else if(cognome.getText().toString().isEmpty()){
                    Toast.makeText(Registrazione.this, "Cognome non inserito correttamente, prego reinserirlo", Toast.LENGTH_LONG).show();
                }else if(email.getText().toString().contains("@")==false){
                    Toast.makeText(Registrazione.this, "Email non inserita correttamente, prego reinserla", Toast.LENGTH_LONG).show();
                }else if(indirizzo.getText().toString().isEmpty()){
                    Toast.makeText(Registrazione.this, "Indirizzo non inserito correttamente, prego reinserirlo", Toast.LENGTH_LONG).show();
                }else if(primaSpec.getText().toString().isEmpty()){
                    Toast.makeText(Registrazione.this, "Specializzazione non inserita correttamente, prego reinserirla", Toast.LENGTH_LONG).show();
                }else if(secondaSpec.getText().toString().isEmpty()){
                    Toast.makeText(Registrazione.this, "Specializzazione non inserita correttamente, prego reinserirla", Toast.LENGTH_LONG).show();
                }else if(password.getText().toString().equals(passwordTemp.getText().toString())==false || password.getText().toString().isEmpty()==true){
                    Toast.makeText(Registrazione.this, "Password non inserita correttamente, prego reinserirlo", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(Registrazione.this, "Registrazione avvenuta", Toast.LENGTH_LONG).show();
                    Intent login = new Intent(Registrazione.this, Inizio.class);
                    startActivity(login);
                }
            }
        });

    }
}
