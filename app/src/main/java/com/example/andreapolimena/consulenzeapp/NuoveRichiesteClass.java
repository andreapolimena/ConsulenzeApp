package com.example.andreapolimena.consulenzeapp;

/**
 * Created by andreapolimena on 16/02/16.
 */
public class NuoveRichiesteClass {

    public String nome = "";
    public String cognome = "";
    public String spec = "";
    public String date = "01-01-1900";
    public String ora_inizio = "00:00:00";

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getOra_inizio() {
        return ora_inizio;
    }

    public void setOra_inizio(String ora_inizio) {
        this.ora_inizio = ora_inizio;
    }

    public String getOra_fine() {
        return ora_fine;
    }

    public void setOra_fine(String ora_fine) {
        this.ora_fine = ora_fine;
    }

    public String ora_fine ="00:00:00";

    public NuoveRichiesteClass(String nome, String cognome, String spec, String date, String ora_inizio, String ora_fine) {
        this.nome = nome;
        this.cognome = cognome;
        this.spec = spec;
        this.date = date;
        this.ora_inizio = ora_inizio;
        this.ora_fine = ora_fine;
    }
}
