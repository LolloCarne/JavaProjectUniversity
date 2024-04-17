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

    public int getValore(){
        return valore;
    }

    public Seme getSeme(){
        return s;
    }

    public void setValore(int v){
        valore = v;
    }

}
