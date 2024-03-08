package com.example.progetto_oo.Database;

public class Ordine {
    private int codOrdine;
    private String cf;
    private String tipologia;
    private String dataordinato;
    private String idMerce;
    private int quantità;
    private String descrizione;


    public Ordine(int codOrdine, String cf, String tipologia, String dataordinato, String idMerce, int quantità, String descrizione){
        this.codOrdine = codOrdine;
        this.cf = cf;
        this.tipologia = tipologia;
        this.dataordinato = dataordinato;
        this.idMerce = idMerce;
        this.quantità = quantità;
        this.descrizione = descrizione;
    }

    public int getCodOrdine() {
        return codOrdine;
    }
    public String getCf() {
        return cf;
    }
    public String getTipologia() {
        return tipologia;
    }
    public String getDataordinato() {
        return dataordinato;
    }
    public String getIdMerce() {
        return idMerce;
    }
    public int getQuantità() {
        return quantità;
    }
    public String getDescrizione() {
        return descrizione;
    }
}
