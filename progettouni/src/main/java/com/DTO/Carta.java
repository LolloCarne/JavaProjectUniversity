package com.DTO;

import com.Enum.Seme;
import java.util.Random;


public class Carta {

    private Seme s;
    private int valore;


    public Carta(){}

    
    public Carta(Seme s, int v){
        this.s=s;
        this.valore=v;
    }

    public Carta(String s){
        switch(s){
            case "CUORI":
            this.s=Seme.CUORI;
            break;

            case "FIORI":
            this.s=Seme.FIORI;
            break;
            case "PICCHE":
            this.s=Seme.PICCHE;
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

}
