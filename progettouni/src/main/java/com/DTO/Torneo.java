package com.DTO;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;


import com.Manager.PartitaManager;
import com.Manager.TorneoManager;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Torneo {
    @JsonProperty("codice")
    public String codice;

    @JsonProperty("Partecipanti")
    public ArrayList<Utente> partecipantiTorneo;

    public Torneo(){
        TorneoManager manager = new TorneoManager();
        this.codice = codice;
        partecipantiTorneo=new ArrayList<>();
        manager.creaTorneo(this);
    }

    public Torneo(ArrayList<Utente> Nickname, String codice){
        TorneoManager manager = new TorneoManager();
        this.codice =codice;
        partecipantiTorneo=Nickname;
    }

    
   /*  public void addPartecipante(Utente u) throws Error{
        TorneoManager manager = new TorneoManager();
        partecipantiTorneo.add(u);

        ArrayList<Torneo> tornei= manager.leggiJson();
        
       for(Torneo t : tornei){
        if(t.codice.equals(this.codice)){
            t.partecipantiTorneo=this.partecipantiTorneo;
        }
       }

       manager.scriviJson(tornei);
    }*/

}
