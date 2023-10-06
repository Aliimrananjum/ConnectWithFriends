package com.example.connectwithfriends;

public class Avtale {

    private long id;
    private String navn;

    private String Sted;

    private String Tid;

    public long getId() {
        return id;
    }

    public String getNavn() {
        return navn;
    }

    public String getSted() {
        return Sted;
    }

    public String getTid() {
        return Tid;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public void setSted(String sted) {
        Sted = sted;
    }

    public void setTid(String tid) {
        Tid = tid;
    }

    public Avtale() {

    }
}
