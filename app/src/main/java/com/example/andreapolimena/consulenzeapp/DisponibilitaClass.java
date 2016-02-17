package com.example.andreapolimena.consulenzeapp;

/**
 * Created by andreapolimena on 16/02/16.
 */
public class DisponibilitaClass {

    public String nome = "";
    public String cognome = "";
    public String spec = "";
    public String date = "01-01-1900";
    public String ora = "00:00:00";
    public String oraFine ="00:00:00";

    public String getOraFine() {
        return oraFine;
    }

    public void setOraFine(int oraFi, int minFin) {
        if(oraFi<10){
            this.oraFine ="0"+((Integer)oraFi).toString();
        }else {
            this.oraFine =((Integer)oraFi).toString();
        }
        this.oraFine =this.oraFine +":";
        if(minFin<10){
            this.oraFine =this.oraFine+"0"+((Integer)minFin).toString();
        }else {
            this.oraFine =this.oraFine+((Integer)minFin).toString();
        }
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

    public void setOra(int ora, int minuti) {
        if(ora<10){
            this.ora ="0"+((Integer)ora).toString();
        }else {
            this.ora =((Integer)ora).toString();
        }
        this.ora =this.ora +":";
        if(minuti<10){
            this.ora =this.ora+"0"+((Integer)minuti).toString();
        }else {
            this.ora =this.ora+((Integer)minuti).toString();
        }
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public DisponibilitaClass(String nome, String cognome, String spec, int giorno, int mese, int anno, int ora, int min, int oraFine, int minFine) {
        setNome(nome);
        setCognome(cognome);
        setSpec(spec);
        setDate(giorno, mese,anno);
        setOra(ora,min);
        setOraFine(oraFine,minFine);
    }

}