package com.DTO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import com.fasterxml.jackson.databind.JsonNode;


public class Utente {
    
    private String Nickname;
    private String id;
    public ArrayList<Carta> mano;
    public ArrayList<CartaSpacca> carteSpacca;
    public String codiceTorneo;
    public int partiteVinte;


    //costruttore utente passando un nome 
    public Utente(String n){
        this.Nickname = n;
        this.id = UUID.randomUUID().toString();
        this.mano=new ArrayList<>();
        this.carteSpacca = new ArrayList<>();
        this.partiteVinte=0;
        this.codiceTorneo =null;
    }

    //costruttore utente passando un nome e un codice del torneo
    public Utente(String n, String codiceTorneo){
        this.Nickname = n;
        this.id = UUID.randomUUID().toString();
        this.mano=new ArrayList<>();
        this.carteSpacca = new ArrayList<>();
        this.partiteVinte=0;
        this.codiceTorneo =codiceTorneo;
    }

    //creazione utente da utente esistente
    public Utente(Utente u){
        this.Nickname = u.getNick();
        this.id = u.getId();
        this.mano= u.mano;
        this.carteSpacca = u.carteSpacca;
        this.codiceTorneo =null;
        this.partiteVinte=0;
    }

    //metodo per mappare un oggetto utente partendo da un JsonNode
    public Utente (JsonNode utenteJson){
        this.Nickname=utenteJson.get("nick").asText();
        this.id=utenteJson.get("id").asText();
        this.codiceTorneo=utenteJson.get("codiceTorneo").asText();
        this.mano=new ArrayList<>();
        this.carteSpacca=new ArrayList<>();
        for(JsonNode cartaSpacca : utenteJson.get("carteSpacca")){
            this.carteSpacca.add(new CartaSpacca(cartaSpacca.get("lettera").asText()));
        }

        this.partiteVinte=utenteJson.get("partiteVinte").asInt();
    }

    //metodi getter e setter
    public String getNick(){
        return this.Nickname;
    }
    public String getId(){
        return this.id;
    }
    public int getPartiteVinte(){
        return this.partiteVinte;
    }
    public void setCodiceTorneo(String codice){
        this.codiceTorneo = codice;
    }
    public void setPartiteVinte(int n){
        this.partiteVinte = n;
    }
    public String getCodiceTorneo(){
        return this.codiceTorneo;
    }

    
    public boolean controllaScala() {
        // Ordina le carte in mano in base al valore
        Collections.sort(mano);

        // Controlla se le carte formano una scala
        for (int i = 0; i < mano.size() - 1; i++) {
            if (!mano.get(i).valoreSuccessivo(mano.get(i + 1))) {
                return false; // Se troviamo una coppia di carte non consecutive, non è una scala
            }
        }
        return true; // Se tutte le carte sono consecutive, è una scala
    }

    public boolean controllaTreStessoSeme() {
        Map<String, Integer> conteggioSemi = new HashMap<>();

        // Conta quante carte ci sono per ciascun seme
        for (Carta carta : mano) {
            if(carta.getClass().getName().equals("com.DTO.Carta")){
                String seme = carta.getSeme().getValore();
                conteggioSemi.put(seme, conteggioSemi.getOrDefault(seme, 0) + 1);
            }

        }

        // Controlla se c'è un seme con almeno tre carte
        for (int conteggio : conteggioSemi.values()) {
            if (conteggio >= 3) {
                return true;
            }
        }

        return false;
    }

    

    public String toString(){
        return "nome: " + Nickname + "\ncodice torneo: " + codiceTorneo + "\npartite vinte" + partiteVinte;
    }

}
