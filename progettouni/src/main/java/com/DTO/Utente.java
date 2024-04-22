package com.DTO;

import java.util.ArrayList;
import java.util.UUID;

import com.fasterxml.jackson.databind.JsonNode;

public class Utente {
    
    private String Nickname;
    private String id;
    public ArrayList<Carta> mano;
    public ArrayList<CartaSpacca> carteSpacca;

    public Utente(String n){
        this.Nickname = n;
        this.id = UUID.randomUUID().toString();
        this.mano=new ArrayList<>();
    }

    public Utente (JsonNode utenteJson){
        this.Nickname=utenteJson.get("nick").asText();
        this.id=utenteJson.get("id").asText();
        this.mano=new ArrayList<>();
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
}
