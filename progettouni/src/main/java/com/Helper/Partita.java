package com.Helper;

import java.util.ArrayList;
import java.util.Random;

import com.DTO.Utente;

import javafx.application.Preloader.ErrorNotification;

public class Partita {
    private String codice;
    ArrayList <Utente> Partecipanti;
    public Partita(){
        this.codice= generateRandomString(6);
        Partecipanti = new ArrayList<>();
    }
    
    public void addPartecipante(Utente u) throws Error{
        if (this.Partecipanti.size()>=4){
            throw new Error();
        }

        Partecipanti.add(u);
    }
    
    public ArrayList<Utente> getPlayers(){
        return this.Partecipanti;
    }

    public String getCode(){
        return this.codice;
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
}
