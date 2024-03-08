package com.example.progetto_oo.Database;

public class Ordine {
    private String codOrdine;
    private String cf;
    private String tipologia;

    public Ordine(String codOrdine, String cf, String tipologia){
        this.codOrdine = codOrdine;
        this.cf = cf;
        this.tipologia = tipologia;
    }

    public String getCodOrdine() {
        return codOrdine;
    }

    public String getCf() {
        return cf;
    }


    public String getTipologia() {
        return tipologia;
    }
}
