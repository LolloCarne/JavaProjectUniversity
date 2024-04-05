package com.DTO;

import java.util.UUID;

import com.fasterxml.jackson.databind.JsonNode;

public class Utente {
    
    private String Nickname;
    private String id;

    public Utente(String n){
        this.Nickname = n;
        this.id = UUID.randomUUID().toString();
    }

    public Utente (JsonNode utenteJson){
        this.Nickname=utenteJson.get("nick").asText();
        this.id=utenteJson.get("id").asText();
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
