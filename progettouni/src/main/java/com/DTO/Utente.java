package com.DTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.fasterxml.jackson.databind.JsonNode;

import javafx.scene.image.ImageView;

public class Utente {
    
    private String Nickname;
    private String id;
    public ArrayList<Carta> mano;
    public Map<ImageView, String> mapImageView;
    public ArrayList<CartaSpacca> carteSpacca;

    public Utente(String n){
        this.Nickname = n;
        this.id = UUID.randomUUID().toString();
        this.mano=new ArrayList<>();
        this.carteSpacca = new ArrayList<>();
        this.mapImageView = new HashMap();
    }

    public Utente (JsonNode utenteJson){
        this.Nickname=utenteJson.get("nick").asText();
        this.id=utenteJson.get("id").asText();
        this.mano=new ArrayList<>();
        this.carteSpacca=new ArrayList<>();
        this.mapImageView = new HashMap();
    }

    public String getNick(){
        return this.Nickname;
    }

    public String getId(){

        return this.id;
    }

    @Override
    public String toString() {
        return this.Nickname;
    }

    public boolean controllaScala(){
        if (mano.get(2).valoreSuccessivo(mano.get(1)) && mano.get(1).valoreSuccessivo(mano.get(0))) {
           return true;
        }
        return false;
    }

    public boolean controllaTreStessoSeme() {
        Map<String, Integer> conteggioSemi = new HashMap<>();

        // Conta quante carte ci sono per ciascun seme
        for (Carta carta : mano) {
            String seme = carta.getSeme().toString();
            conteggioSemi.put(seme, conteggioSemi.getOrDefault(seme, 0) + 1);
        }

        // Controlla se c'è un seme con almeno tre carte
        for (int conteggio : conteggioSemi.values()) {
            if (conteggio >= 3) {
                return true;
            }
        }

        return false;
    }

}
