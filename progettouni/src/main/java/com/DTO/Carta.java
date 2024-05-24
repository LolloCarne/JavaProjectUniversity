package com.DTO;

import com.Enum.Seme;
import java.util.Random;


public class Carta implements Comparable<Carta>{

    private Seme s;
    private int valore;
    private String path;
    String str="";

    public Carta(){}

    //costruttore che crea la carta da gioco
    public Carta(Seme s, int v){
        this.s=s;
        this.valore=v+1;
        this.str=s.getValore();
        this.path = "/com/example/img/" +str + String.valueOf(valore) + ".png";
    }

    //assegnazione del seme 
    public Carta(String s, int n){
        switch(s){
            case "cucchiaio":
            this.s=Seme.CUCCHIAI;
            this.str="cucchiaio";
            this.valore=n;
            this.path = "/com/example/img/" +str + String.valueOf(valore) + ".png";
            break;
            case "forchetta":
            this.s=Seme.FORCHETTE;
            this.str="forchetta";
            this.valore=n;
            this.path = "/com/example/img/" +str + String.valueOf(valore) + ".png";
            break;
            case "coltello":
            this.s=Seme.COLTELLI;
            this.str="coltello";
            this.valore=n;
            this.path = "/com/example/img/" +str + String.valueOf(valore) + ".png";
            break;
        }
    }

    //costruttori dati nel primo caso il seme e nel secondo il valore
    public Carta(Seme s){
        this.s=s;
    }
    public Carta(int v){
        this.valore=v;
    }

    //metodi getter e setter
    public int getValore(){
        return valore;
    }
    public Seme getSeme(){
        return s;
    }
    public void setPath(String url){
        this.path=url;
    }
    public void setValore(int v){
        valore = v;
    }
    public void setSeme(Seme s){
        this.s = s;
    }
    public String getPath(){
        return this.path;
    }

    public String toString(){
        return s.toString() + " " + valore;
    }
    
    //sovrascrive il metodo compareTo dell'interfaccia Comparable
    //serve a comparare due oggetti
    @Override
    public int compareTo(Carta altraCarta) {
    return Integer.compare(this.valore, altraCarta.valore);
    }

    //ti ritorna true se l'oggetto che gli passi è successivo all'oggetto in cui si trova
    //lo usiamo per comparare le carte per controllare quando facciamo scala
    public boolean valoreSuccessivo(Carta altraCarta) {
        return this.valore == altraCarta.valore - 1;
    }
}
