package com.example.andreapolimena.consulenzeapp;

/**
 * Created by andreapolimena on 10/02/16.
 */
public class Utente {
    private String nome;
    private String cognome;
    private String email;
    private String indirizzo;
    private String primaSpec;
    private String secondaSpec;


    public Utente(){
        nome = "Gianni";
        cognome="Rossi";
        email = "giannirossi@gmail.com";
        indirizzo= "via roma, lecce";
        primaSpec= "muratore";
        secondaSpec="idraulico";
    }

    public void setUtente(String nome, String cognome, String email, String indirizzo, String primaSpec, String secondaSpec){
        this.nome= nome;
        this.cognome=cognome;
        this.email=email;
        this.indirizzo=indirizzo;
        this.primaSpec=primaSpec;
        this.secondaSpec=secondaSpec;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public String getEmail() {
        return email;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public String getPrimaSpec() {
        return primaSpec;
    }

    public String getSecondaSpec() {
        return secondaSpec;
    }
}
