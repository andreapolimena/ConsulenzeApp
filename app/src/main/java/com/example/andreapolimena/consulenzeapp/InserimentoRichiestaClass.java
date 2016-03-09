package com.example.andreapolimena.consulenzeapp;

/**
 * Created by andreapolimena on 16/02/16.
 */
public class InserimentoRichiestaClass {

    public String nome = "";
    public String cognome = "";
    public String spec = "";
    public String date = "01-01-1900";
    public String ora = "00:00:00";
    public String oraFine ="00:00:00";

    public String getOraFine() {
        return oraFine;
    }

    public void setOraFine(String oraFine) {
        this.oraFine = oraFine;
    }

    public void setDate(String date) {
        this.date = date;
    }

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

    public void setDate(int giorno, int mese, int anno) {
        if (giorno < 10) {
            this.date = "0" + ((Integer) giorno).toString();
        } else{
            this.date = ((Integer) giorno).toString();
        }
        this.date=this.date+"/";
        if (mese<10){
            this.date=this.date+"0"+((Integer) mese).toString();
        }else {
            this.date=this.date+((Integer) mese).toString();
        }
        this.date=this.date+"/"+((Integer) anno).toString();
    }

    public String getOra() {
        return ora;
    }

    public void setOra(String ora) {
        this.ora= ora;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public InserimentoRichiestaClass(String nome, String cognome, String spec, String date, String ora, String oraFine) {
        setNome(nome);
        setCognome(cognome);
        setSpec(spec);
        setDate(date);
        setOra(ora);
        setOraFine(oraFine);
    }

}
