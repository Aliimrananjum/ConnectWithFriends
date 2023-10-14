package com.example.connectwithfriends;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Kontakt {
    @PrimaryKey(autoGenerate = true)
    public int kontantId;

    @ColumnInfo(name = "fornavn")
    public String fornavn;

    @ColumnInfo(name = "etternavn")
    public String etternavn;

    @ColumnInfo(name = "nummer")
    public String nummer;

    public int getKontantId() {
        return kontantId;
    }

    public String getFornavn() {
        return fornavn;
    }

    public String getEtternavn() {
        return etternavn;
    }

    public String getNummer() {
        return nummer;
    }

    public void setKontantId(int kontantId) {
        this.kontantId = kontantId;
    }

    public void setFornavn(String fornavn) {
        this.fornavn = fornavn;
    }

    public void setEtternavn(String etternavn) {
        this.etternavn = etternavn;
    }

    public void setNummer(String nummer) {
        this.nummer = nummer;
    }
}
