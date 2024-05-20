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
    
    public ArrayList<Utente> partecipantiTorneo= new ArrayList<>();
    TorneoManager manager = new TorneoManager();
    Partita partitaTorneo;
    String codice;
    int partiteDaGiocare =3;

    public Torneo(){
        
        partitaTorneo = manager.creaPartiteTorneo();
        this.partiteDaGiocare=partiteDaGiocare;
        this.codice = codice;
        manager.creaTorneo(this);
    }

    /*public Torneo(ArrayList<Utente> partecipantiTorneo, String codice){
        TorneoManager manager = new TorneoManager();
        PartitaManager managerP = new PartitaManager();
        this.codice =codice;
        this.partecipantiTorneo=manager.getPartecipantiTorneo();
        System.out.println("si");
        Partita p= new Partita(partecipantiTorneo);
        managerP.creaPartita(p);
    }*/

    
    public void addPartecipante(Utente u) throws Error{
        TorneoManager manager = new TorneoManager();
        partecipantiTorneo.add(u);
    }

    public ArrayList<Utente> getPartecipanti(){
        return this.partecipantiTorneo;
    }

    public int getPartiteDaGiocare(){
        return this.partiteDaGiocare;
    }

    public Partita getPartitaTorneo(){
        return this.partitaTorneo;
    }
    public void setPartiteDaGiocare(int n){
        partiteDaGiocare=n;
    }


}