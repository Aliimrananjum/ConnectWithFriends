package com.example.connectwithfriends;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHjelper extends SQLiteOpenHelper{

    // Navn på databasen
    private static final String DATABASE_NAVN = "avtaler.db";
    // Versjon av databasen, endre denne ved strukturelle endringer for å kalle onUpgrade()
    private static final int DATABASE_VERSION = 1;

    public static final String TABELL_AVTALE = "avtaler";
    public static final String KOLONNE_ID = "id";
    public static final String KOLONNE_AVTALE_NAVN = "navn";

    // SQL spørring for å opprette oppgavetabellen
    private static final String CREATE_TABLE_TASKS =
            "CREATE TABLE " + TABELL_AVTALE + "(" +
                    KOLONNE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    KOLONNE_AVTALE_NAVN + " TEXT NOT NULL)";

    // Konstruktør
    public DatabaseHjelper(Context context) {
        super(context, DATABASE_NAVN, null, DATABASE_VERSION);
    }

    // Denne metoden blir kalt når databasen opprettes for første gang
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_TASKS);
    }

    // Denne metoden blir kalt hvis DATABASE_VERSION øker
    // og kan brukes til å oppdatere databasen ved strukturelle endringer
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }
}
