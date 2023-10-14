package com.example.connectwithfriends;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Kontakt.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract KontaktDao kontaktDao();
}
