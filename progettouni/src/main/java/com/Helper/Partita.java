package com.Helper;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.DTO.Utente;

import javafx.application.Preloader.ErrorNotification;

public class Partita {
    private String codice;
    ArrayList <Utente> Partecipanti;
    public Partita(){
        this.codice= generateRandomString(6);
        Partecipanti = new ArrayList<>();
        //creo json

    }

    public Partita(String codice){

        //carico json
    }

        private void salvaPartita() {
        try {
            String fileName = "tuo_file.json";
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            List<Partita> partite;

            // Leggi il contenuto del file JSON esistente
            if (Files.exists(Paths.get(fileName))) {
                try (FileReader reader = new FileReader(fileName)) {
                    partite = gson.fromJson(reader, List.class);
                }
            } else {
                partite = new ArrayList<>();
            }

            // Aggiungi la nuova Partita alla lista
            partite.add(this);

            // Scrivi la lista aggiornata nel file JSON
            try (FileWriter writer = new FileWriter(fileName)) {
                gson.toJson(partite, writer);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
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
