package com.example.andreapolimena.consulenzeapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by andreapolimena on 14/02/16.
 */
public class UtenteDbHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    private static final String TEXT_TYPE = " TEXT";
    private static final String DATA_TYPE = " DATE";
    private static final String TIME_TYPE= " TIME";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + UtenteDb.Utente.TABLE_NAME + " (" +
                    UtenteDb.Utente._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    UtenteDb.Utente.COLUMN_NAME_EMAIL + TEXT_TYPE + COMMA_SEP +
                    UtenteDb.Utente.COLUMN_NAME_PASSWORD + TEXT_TYPE + COMMA_SEP +
                    UtenteDb.Utente.COLUMN_NAME_NOME + TEXT_TYPE + COMMA_SEP +
                    UtenteDb.Utente.COLUMN_NAME_COGNOME + TEXT_TYPE + COMMA_SEP +
                    UtenteDb.Utente.COLUMN_NAME_INDIRIZZO + TEXT_TYPE + COMMA_SEP +
                    UtenteDb.Utente.COLUMN_NAME_SPEC_PRINC + TEXT_TYPE + COMMA_SEP +
                    UtenteDb.Utente.COLUMN_NAME_SPEC_SECOND + TEXT_TYPE +
                    " );";
    private static final String SQL_CREATE_TABLE_2 =
            "CREATE TABLE " + UtenteDb.Utente.TABLE_APPUNTAMENTI + " (" +
                    UtenteDb.Utente._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    UtenteDb.Utente.COLUMN_NAME_EMAIL+TEXT_TYPE+COMMA_SEP+
                    UtenteDb.Utente.COLUMN_NAME_NOME + TEXT_TYPE + COMMA_SEP +
                    UtenteDb.Utente.COLUMN_NAME_COGNOME + TEXT_TYPE + COMMA_SEP +
                    UtenteDb.Utente.COLUMN_NAME_SPEC_PRINC + TEXT_TYPE + COMMA_SEP+
                    UtenteDb.Utente.COLUMN_NAME_DATE +DATA_TYPE+ COMMA_SEP +
                    UtenteDb.Utente.COLUMN_NAME_ORA +TIME_TYPE+
                    " );";
    private static final String SQL_CREATE_TABLE_3 =
            "CREATE TABLE "+ UtenteDb.Utente.TABLE_DISPONIBILITA+" ("+
                    UtenteDb.Utente._ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                    UtenteDb.Utente.COLUMN_NAME_NOME+TEXT_TYPE+COMMA_SEP+
                    UtenteDb.Utente.COLUMN_NAME_COGNOME+TEXT_TYPE+COMMA_SEP+
                    UtenteDb.Utente.COLUMN_NAME_SPEC_PRINC+TEXT_TYPE+COMMA_SEP+
                    UtenteDb.Utente.COLUMN_NAME_DATE+DATA_TYPE+COMMA_SEP+
                    UtenteDb.Utente.COLUMN_NAME_ORA+TIME_TYPE+COMMA_SEP+
                    UtenteDb.Utente.COLUMN_NAME_ORAFINE+TIME_TYPE+
                    " );";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + UtenteDb.Utente.TABLE_NAME;
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "FeedReader2.db";

    public UtenteDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS "+ UtenteDb.Utente.TABLE_NAME);
        db.execSQL(SQL_CREATE_ENTRIES);
        db.execSQL("insert into Utente values(null,'andreapolimena@gmail.com','pass','Andrea','Polimena', 'via marciano','Informatica','Java');");
        db.execSQL("insert into Utente values(null,'eliamarasco93@hotmail.it','pass','Elia','Marasco', 'via magenta','Informatica','DBMS');");
        db.execSQL("insert into Utente values(null,'a@a','a','a','a', 'via milano','Idraulica','Impianti');");
        db.execSQL("insert into Utente values(null,'prova@prova.it','prova','Prova','Schiacciante', 'via roma','Prove','Evidenze');");
        db.execSQL("DROP TABLE IF EXISTS "+ UtenteDb.Utente.TABLE_APPUNTAMENTI);
        db.execSQL(SQL_CREATE_TABLE_2);
        db.execSQL("insert into Appuntamenti values(null,'prova@prova.it','Andrea','Polimena','Informatica','2016-02-24','10:30:00');");
        db.execSQL("insert into Appuntamenti values(null,'prova@prova.it','Elia','Marasco','Informatica','2016-02-24','11:30:00');");
        db.execSQL("insert into Appuntamenti values(null,'a@a','Andrea','Polimena','Informatica','2016-02-24','10:30:00');");
        db.execSQL("insert into Appuntamenti values(null,'a@a','Elia','Marasco','Informatica','2016-02-25','11:30:00');");
        db.execSQL(SQL_CREATE_TABLE_3);
        db.execSQL("insert into Disponibilita values(null, 'Andrea', 'Polimena','Informatica','2016-02-25', '10:00:00','11:00:00');");
        db.execSQL("insert into Disponibilita values(null, 'Elia', 'Marasco','Informatica','2016-02-26', '12:00:00','13:00:00');");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
