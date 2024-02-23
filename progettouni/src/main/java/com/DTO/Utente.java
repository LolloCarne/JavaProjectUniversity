package com.DTO;

import java.util.UUID;

public class Utente {
    
    private String Nickname;
    private UUID id;

    public Utente(String n){
        this.Nickname = n;
        this.id = UUID.randomUUID();
    }

    public String getNick(){
        return this.Nickname;
    }
}
