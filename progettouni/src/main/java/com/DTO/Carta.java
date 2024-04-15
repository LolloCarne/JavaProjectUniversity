package com.DTO;

import com.Enum.Seme;
import java.util.Random;


public class Carta {

    public Seme s=sceltaSeme();
    public int valore;
    public Random r = new Random();

    public Carta(){
        this.s=s;
        this.valore=r.nextInt(7)+1;
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
    public Seme sceltaSeme(){
        int i = r.nextInt(3)+1;
        switch (i){
            case 1: s=Seme.CUORI; break;
            case 2: s=Seme.FIORI; break;
            case 3: s=Seme.PICCHE; break;
        }
        return s;
    }

}