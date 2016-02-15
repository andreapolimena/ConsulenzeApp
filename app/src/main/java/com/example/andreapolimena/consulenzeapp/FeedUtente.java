package com.example.andreapolimena.consulenzeapp;

import android.provider.BaseColumns;

/**
 * Created by andreapolimena on 14/02/16.
 */
public class FeedUtente {
    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    public FeedUtente() {}

    /* Inner class that defines the table contents */
    public static abstract class FeedEntry implements BaseColumns {
        public static final String COLUMN_NAME_NULLABLE="null";
        public static final String TABLE_NAME = "Utente";
        public static final String COLUMN_NAME_EMAIL = "email";
        public static final String COLUMN_NAME_PASSWORD = "password";
        public static final String COLUMN_NAME_NOME = "nome";
        public static final String COLUMN_NAME_COGNOME = "cognome";
        public static final String COLUMN_NAME_INDIRIZZO = "indirizzo";
        public static final String COLUMN_NAME_SPEC_PRINC = "spec_princ";
        public static final String COLUMN_NAME_SPEC_SECOND = "spec_second";
    }
}
