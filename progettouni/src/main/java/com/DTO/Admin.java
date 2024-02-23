package com.DTO;

public class Admin extends Utente{
    
    String p;
    public Admin(String n, String p){
        super(n);
        this.p=p;
    }

    public boolean Login(String n, String passwd){

        //logica di login
        return true;
    }

    public boolean Register(String n, String passwd){

        //logica di registrazione
        return true;
    }
}
