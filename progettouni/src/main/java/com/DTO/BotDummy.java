package com.DTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import com.Enum.Seme;

public class BotDummy extends Utente{
    private static final String [] randomName = {"Jim","Chris","Erik","Jessie","Queen","Jennifer","Joshua"};
    public BotDummy(){
        super(randomName[new Random().nextInt(randomName.length)]+"@DUMMYBOT");
    }
    public BotDummy (Utente u){
        super(u);
      
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
