package com.example.progetto_oo.Database;

public class Veicolo{
    private String targa;
    private int maxTrasportabile;
    private String descrizione ;
    private String matricolaCorriere;
    private int id_magazzino;

    public Veicolo(String targa,int max_trasportabile,String descrizione,String matricola,int id_magazzino){
        this.targa = targa;
        this.maxTrasportabile=max_trasportabile;
        this.descrizione=descrizione;
        this.matricolaCorriere=matricola;
        this.id_magazzino=id_magazzino;
    }

    public String getTarga() {
        return this.targa;
    }
    public String getDescrizione() {
        return this.descrizione;
    }
    public int getMaxTrasportabile() {
        return maxTrasportabile;
    }
    public String getMatricolaCorriere() {
        return matricolaCorriere;
    }
    public int getId_magazzino() {
        return this.id_magazzino;
    }

}
