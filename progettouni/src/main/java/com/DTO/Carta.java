package com.DTO;

import com.Enum.Seme;
import java.util.Random;


public class Carta implements Comparable<Carta>{

    private Seme s;
    private int valore;
    private String path;
    String str="";

    public Carta(){}

    
    public Carta(Seme s, int v){
        this.s=s;
        this.valore=v+1;
        this.str=s.getValore();
        this.path = "/com/example/img/" +str + String.valueOf(valore) + ".png";
    }

    public Carta(String s){

        switch(s){
            case "CUCCHIAI":
            this.s=Seme.CUCCHIAI;
            this.str="cucchiaio";
            break;
            case "FORCHETTE":
            this.s=Seme.FORCHETTE;
            this.str="forchetta";
            break;
            case "COLTELLI":
            this.s=Seme.COLTELLI;
            this.str="coltello";
            break;
        }
    }

    public Carta(Seme s){
        this.s=s;

    }
    public Carta(int v){
        this.valore=v;
    }

    public int getValore(){
        return valore;
    }

    public Seme getSeme(){
        return s;
    }

    public void setValore(int v){
        valore = v;
    }
    public void setSeme(Seme s){
        this.s = s;
    }

    public String toString(){
        return s.toString() + " " + valore;
    }
    public String getPath(){
       
        return this.path;
    }

    @Override
    public int compareTo(Carta altraCarta) {
    return Integer.compare(this.valore, altraCarta.valore);
    }

    public boolean valoreSuccessivo(Carta altraCarta) {
        // Se il valore della carta corrente è uno in meno rispetto al valore dell'altra carta, allora il valore è successivo
        return this.valore == altraCarta.valore - 1;
    }
}
