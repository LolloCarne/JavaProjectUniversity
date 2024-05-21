package com.DTO;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import com.Manager.PartitaManager;
import com.fasterxml.jackson.annotation.JsonProperty;


public class Partita {
    @JsonProperty("codice")
    public String codice;

    @JsonProperty("Partecipanti")
    ArrayList <Utente> Partecipanti;


    public Partita(){
        PartitaManager manager = new PartitaManager();
        this.codice= generateRandomString(6);
        Partecipanti = new ArrayList<>();
        manager.creaPartita(this);

    }

    public Partita(ArrayList<Utente> partecipanti){
        PartitaManager manager = new PartitaManager();
        this.codice= generateRandomString(6);
        this.Partecipanti=partecipanti;
        manager.creaPartita(this);
    }
 

 
    /* costruttore che viene chiamato quando si vuole istanziare una partita esistente
     * pertendo da un codice
     */
    public Partita(String codice){       
        PartitaManager manager = new PartitaManager();
        this.codice=codice;
        this.Partecipanti=manager.getPartecipantiByCode(codice);
        
    }

    public Partita(String codice, ArrayList <Utente> partecipanti){
        this.codice=codice;
        this.Partecipanti=partecipanti;
    }



    
    
    public void addPartecipante(Utente u) throws Error{
        PartitaManager manager = new PartitaManager();
        if (this.Partecipanti.size()>=4){
            throw new Error();
        }
        Partecipanti.add(u);

        ArrayList<Partita> partite= manager.leggiJson();
        
       for(Partita p : partite){
        if(p.codice.equals(this.codice)){
            p.Partecipanti=this.Partecipanti;
        }
       }

       manager.scriviJson(partite);
    }
    
    public ArrayList<Utente> getPartecipanti(){
        return this.Partecipanti;
    }


    public void setPartecipanti(ArrayList <Utente> u){
        this.Partecipanti=u;
    }
    
    public String getCodice(){
        return this.codice;
    }

    public HashMap getPartiteVintePartecipanti(){
        HashMap <String,String > partiteVinte = new HashMap();
        String str="";
        for(Utente u : Partecipanti){
            str = u.getPartiteVinte() +"";
            partiteVinte.put(str,u.getNick());
        }
        for (Map.Entry<String, String> entry : partiteVinte.entrySet()) {
            System.out.println("Chiave: " + entry.getKey() + ", Valore: " + entry.getValue());
        }
        return partiteVinte;

    }

    
    private static String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"; // Caratteri alfabetici maiuscoli e cifre
        StringBuilder randomString = new StringBuilder();

        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            randomString.append(characters.charAt(index));
        }

        return randomString.toString();
    }

    @Override
    public String toString(){
        return "Partecipanti:"+this.getPartecipanti().toString() + "\nCodice:  "+this.codice;
    }
}
