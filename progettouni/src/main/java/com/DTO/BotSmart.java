package com.DTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import com.Enum.Seme;

public class BotSmart extends Utente{
    private static final String [] randomName = {"Jim","Chris","Erik","Jessie","Queen","Jennifer","Joshua"};
    
    public BotSmart(){
        super(randomName[new Random().nextInt(randomName.length)]+"@SMARTBOT");
    }
    public BotSmart (Utente u){
        super(u);
      
    }

    public Carta qualeScartare(){
        Carta diversaPerSeme = null;
        
        for (Carta carta : super.mano) {
            Seme seme = carta.getSeme(); // Ottieni il seme della carta corrente
            // Itera attraverso ogni altra carta nel mazzo
            for (Carta altraCarta : super.mano) {
                // Controlla se la carta corrente ha lo stesso seme della carta iterata
                if (altraCarta.getSeme().equals(seme)) {

                    for(Carta diversa : super.mano){
                        if(!diversa.getSeme().equals(seme)){
                            diversaPerSeme = diversa;
                            break;
                        }
                    }
                }
            
            }
            if(diversaPerSeme != null){
                return diversaPerSeme;
            }
                

        }

        return super.mano.get(0);
        
        


    }
    
    public String gioca(){
        
        if(controllaTreStessoSeme()){
            return "Stesso Seme";
        }
        if(controllaScala()){
            return "Scala";
        }
        return "Scarta";
    }

    
    
}
