package com.DTO;

public class Admin extends Utente{
    
    String password;
    public Admin(String n, String p){
        super(n);
        this.password=p;
    }

    public String getPassword(){
        return this.password;
    }

   
    
}
