package com.example.andreapolimena.consulenzeapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by andreapolimena on 14/02/16.
 */
public class FeedUtenteDbHelper extends SQLiteOpenHelper {
        // If you change the database schema, you must increment the database version.
        private static final String TEXT_TYPE = " TEXT";
        private static final String COMMA_SEP = ",";
        private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + FeedUtente.FeedEntry.TABLE_NAME + " (" +
                    FeedUtente.FeedEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    FeedUtente.FeedEntry.COLUMN_NAME_EMAIL + TEXT_TYPE + COMMA_SEP +
                    FeedUtente.FeedEntry.COLUMN_NAME_PASSWORD + TEXT_TYPE + COMMA_SEP +
                    FeedUtente.FeedEntry.COLUMN_NAME_NOME + TEXT_TYPE + COMMA_SEP +
                    FeedUtente.FeedEntry.COLUMN_NAME_COGNOME + TEXT_TYPE + COMMA_SEP +
                    FeedUtente.FeedEntry.COLUMN_NAME_INDIRIZZO + TEXT_TYPE + COMMA_SEP +
                    FeedUtente.FeedEntry.COLUMN_NAME_SPEC_PRINC + TEXT_TYPE + COMMA_SEP +
                    FeedUtente.FeedEntry.COLUMN_NAME_SPEC_SECOND + TEXT_TYPE +

            " );";

        private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + FeedUtente.FeedEntry.TABLE_NAME;
        public static final int DATABASE_VERSION = 1;
        public static final String DATABASE_NAME = "FeedReader2.db";

        public FeedUtenteDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_CREATE_ENTRIES);
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
