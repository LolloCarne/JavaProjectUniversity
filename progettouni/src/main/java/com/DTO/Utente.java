package com.DTO;

public class Utente {
    
    private String Nickname;
    private String id;

    public Utente(String n, String i){
        this.Nickname = n;
        this.id = i;
    }

    public String getNick(){
        return this.Nickname;
    }
}
