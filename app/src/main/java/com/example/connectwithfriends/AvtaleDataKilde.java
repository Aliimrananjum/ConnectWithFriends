package com.example.connectwithfriends;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

public class AvtaleDataKilde {
    // Objekter for håndtering av database og hjelpefunksjoner.
    private SQLiteDatabase database;
    private DatabaseHjelper dbHelper;

    // Konstruktør for denne klassen. Initialiserer databasen-hjelper.
    public AvtaleDataKilde(Context context){
        dbHelper = new DatabaseHjelper(context);
    }
    // Åpner en skrivbar tilkobling til databasen.
    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase(); }
    // Lukker tilkoblingen til databasen.
    public void close() { dbHelper.close(); }


    // Metode for å legge til en ny avtale i databasen.
    public Avtale leggInnAvtale(String avtaleNavn){
        ContentValues values = new ContentValues();

        values.put(DatabaseHjelper.KOLONNE_AVTALE_NAVN,avtaleNavn);

        long insertId = database.insert(DatabaseHjelper.TABELL_AVTALE,null,values);

        Cursor cursor = database.query(DatabaseHjelper.TABELL_AVTALE,null,DatabaseHjelper.KOLONNE_ID +"=" + insertId,null,null,null,null);

        cursor.moveToFirst();

        Avtale nyAvtale = cursorTilAvtale(cursor);
        cursor.close();

        return nyAvtale;
    }

    // En hjelpefunksjon som konverterer data fra en database-cursor til et Avtale-objekt.
    private Avtale cursorTilAvtale(Cursor cursor){
        Avtale avtale = new Avtale();

        avtale.setId(cursor.getLong(cursor.getColumnIndexOrThrow(DatabaseHjelper.KOLONNE_ID)));


        avtale.setNavn(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHjelper.KOLONNE_AVTALE_NAVN)));
        return avtale;
    }
    // Henter alle avtalene fra databasen og returnerer dem som en liste med Avtale-objekter.
    public List<Avtale> finnAlleAvtaler(){
        List<Avtale> avtaler = new ArrayList<>();
        Cursor cursor = database.query(DatabaseHjelper.TABELL_AVTALE,null,null,null,null,null,null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            Avtale avtale = cursorTilAvtale(cursor);
            avtaler.add(avtale);
            cursor.moveToNext();
        }
        cursor.close();
        return avtaler;
    }

}
