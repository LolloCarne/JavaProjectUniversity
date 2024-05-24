package com.DTO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.Manager.PartitaManager;
import com.fasterxml.jackson.annotation.JsonProperty;


public class Partita {
    @JsonProperty("codice")
    public String codice;

    @JsonProperty("Partecipanti")
    ArrayList <Utente> Partecipanti;

    //costruttore per creare una partita 
    public Partita(){
        PartitaManager manager = new PartitaManager();
        this.codice= generateRandomString(6);
        Partecipanti = new ArrayList<>();
        manager.creaPartita(this);

    }

    // costruttore che viene chiamato quando si vuole istanziare una partita esistente partendo da un ArrayList di Utenti 
    public Partita(ArrayList<Utente> partecipanti){
        PartitaManager manager = new PartitaManager();
        this.codice= generateRandomString(6);
        this.Partecipanti=partecipanti;
        manager.creaPartita(this);
    }
 

 
    // costruttore che viene chiamato quando si vuole istanziare una partita esistente partendo da un codice esistente
    public Partita(String codice){       
        PartitaManager manager = new PartitaManager();
        Partita p = manager.getPartitaByCode(codice);

        if(p!= null){
            this.codice = p.codice;
            this.Partecipanti=p.getPartecipanti();
        }else{
            this.codice=codice;
            this.Partecipanti=manager.getPartecipantiByCode(codice);
        }  
    }

    // costruttore che viene chiamato quando si vuole istanziare una partita esistente partendo da un codice esistente e da un array di utenti
    public Partita(String codice, ArrayList <Utente> partecipanti){
        this.codice=codice;
        this.Partecipanti=partecipanti;
    }

    //metodo per aggiungere partecipanti alla partita
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
       manager.scriviJson(partite); //aggiornamento del Json
    }
    
    //metodo per avere i partecipanti della partita
    public ArrayList<Utente> getPartecipanti(){
        return this.Partecipanti;
    }

    //metodo per settare i partecipanti della partita
    public void setPartecipanti(ArrayList <Utente> u){
        this.Partecipanti=u;
    }
    
    //metodo per avere il codice della partita
    public String getCodice(){
        return this.codice;
    }

    //metodo per salvare la partita
    public void save(){
        PartitaManager manager = new PartitaManager();
        ArrayList<Partita> partite= manager.leggiJson();
        for(Partita p : partite){
            if(p.codice.equals(this.codice)){
                p.Partecipanti=this.Partecipanti;
            }
        }
        manager.scriviJson(partite);
    }

    //metodo per creare una HashMap con chiave come numero partite vinte
    //e valore come utente che ha vinto le partite
    public HashMap partiteVintePartecipanti(){
        HashMap <String,String > partiteVinte = new HashMap();
        String str="";
        for(Utente u : Partecipanti){
            str = u.getPartiteVinte() +"";
            partiteVinte.put(str,u.getNick());
        }
        return partiteVinte;

    }
    
    //metodo per generare un codice casuale
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
