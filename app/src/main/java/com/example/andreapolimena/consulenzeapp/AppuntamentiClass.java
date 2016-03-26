package com.example.andreapolimena.consulenzeapp;

/**
 * Created by andreapolimena on 16/02/16.
 */
public class AppuntamentiClass {

    public String nome = "";
    public String cognome = "";
    public String spec = "";
    public String date = "01-01-1900";
    public String ora = "00:00:00";

    public float getValutazione() {
        return valutazione;
    }

    public void setValutazione(float valutazione) {
        this.valutazione = valutazione;
    }

    public float valutazione = (float) 3.2;

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date=date;
    }

    public String getOra() {
        return ora;
    }

    public void setOra(String ora) {

        this.ora =ora;

    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public AppuntamentiClass(String nome, String cognome, String spec, String date, String ora, float valutazione) {
        setNome(nome);
        setCognome(cognome);
        setSpec(spec);
        setDate(date);
        setOra(ora);
        setValutazione(valutazione);
    }

}
