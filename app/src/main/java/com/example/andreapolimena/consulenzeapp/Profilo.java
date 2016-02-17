package com.example.andreapolimena.consulenzeapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Profilo extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profilo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        final EditText nome = (EditText)findViewById(R.id.editText10);
        final EditText cognome = (EditText)findViewById(R.id.editText11);
        final EditText email = (EditText)findViewById(R.id.editText12);
        final EditText indirizzo = (EditText)findViewById(R.id.editText13);
        final EditText spec_princ = (EditText)findViewById(R.id.editText14);
        final EditText spec_sec = (EditText)findViewById(R.id.editText15);
        final EditText pass1 = (EditText)findViewById(R.id.editText16);
        EditText pass2 = (EditText)findViewById(R.id.editText17);

        UtenteDbHelper utenteDbHelper = new UtenteDbHelper(Profilo.this);
        final SQLiteDatabase database = utenteDbHelper.getReadableDatabase();
        Cursor cursor=database.rawQuery("select * from Utente where email=='"+Inizio.utenteLoggato+"'",null);
        if(cursor!=null&&cursor.moveToFirst()){
            while (!cursor.isAfterLast()) {
                nome.setText(cursor.getString(cursor.getColumnIndexOrThrow(UtenteDb.Utente.COLUMN_NAME_NOME)));
                cognome.setText(cursor.getString(cursor.getColumnIndexOrThrow(UtenteDb.Utente.COLUMN_NAME_COGNOME)));
                email.setText(cursor.getString(cursor.getColumnIndexOrThrow(UtenteDb.Utente.COLUMN_NAME_EMAIL)));
                indirizzo.setText(cursor.getString(cursor.getColumnIndexOrThrow(UtenteDb.Utente.COLUMN_NAME_INDIRIZZO)));
                spec_princ.setText(cursor.getString(cursor.getColumnIndexOrThrow(UtenteDb.Utente.COLUMN_NAME_SPEC_PRINC)));
                spec_sec.setText(cursor.getString(cursor.getColumnIndexOrThrow(UtenteDb.Utente.COLUMN_NAME_SPEC_SECOND)));
                pass1.setText(cursor.getString(cursor.getColumnIndexOrThrow(UtenteDb.Utente.COLUMN_NAME_PASSWORD)));
                pass2.setText(cursor.getString(cursor.getColumnIndexOrThrow(UtenteDb.Utente.COLUMN_NAME_PASSWORD)));
                cursor.moveToNext();
            }
        }
        cursor.close();
        database.close();
        utenteDbHelper.close();

        Button update = (Button)findViewById(R.id.button8);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UtenteDbHelper utenteDbHelper1 = new UtenteDbHelper(Profilo.this);
                SQLiteDatabase database1 = utenteDbHelper1.getWritableDatabase();

                database1.execSQL("UPDATE Utente SET " +
                                UtenteDb.Utente.COLUMN_NAME_NOME + "='" + nome.getText().toString() + "', " +
                                UtenteDb.Utente.COLUMN_NAME_COGNOME + "='" + cognome.getText().toString() + "', " +
                                UtenteDb.Utente.COLUMN_NAME_EMAIL + "='" + email.getText().toString() + "', " +
                                UtenteDb.Utente.COLUMN_NAME_INDIRIZZO + "='" + indirizzo.getText().toString() + "', " +
                                UtenteDb.Utente.COLUMN_NAME_SPEC_PRINC + "='" + spec_princ.getText().toString() + "', " +
                                UtenteDb.Utente.COLUMN_NAME_SPEC_SECOND + "='" + spec_sec.getText().toString() + "', " +
                                UtenteDb.Utente.COLUMN_NAME_PASSWORD + "='" + pass1.getText().toString() + "' " +
                                "WHERE email='" + Inizio.utenteLoggato + "'"
                );
                database1.close();
                utenteDbHelper1.close();
                Toast.makeText(Profilo.this, "Utente modificato correttamente",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Profilo.this, Appuntamenti.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.profilo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if(id==R.id.dates){
            Intent intent = new Intent(Profilo.this, Appuntamenti.class);
            startActivity(intent);
        }else if (id == R.id.availability) {
            Intent intent = new Intent(Profilo.this, Disponibilita.class);
            startActivity(intent);
        } else if (id == R.id.new_request) {
            Intent intent = new Intent(Profilo.this, NuoveRichieste.class);
            startActivity(intent);
        } else if (id == R.id.history) {
            Intent intent = new Intent(Profilo.this, StoricoAttivita.class);
            startActivity(intent);
        } else if (id == R.id.received_request) {
            Intent intent = new Intent(Profilo.this, RichiesteRicevute.class);
            startActivity(intent);
        } else if (id == R.id.account) {
            
        } else if (id == R.id.rating) {
            Intent intent = new Intent(Profilo.this, Valutazione.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
