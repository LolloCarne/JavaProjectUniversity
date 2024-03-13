package com.Helper;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.DTO.Utente;

import javafx.application.Preloader.ErrorNotification;

public class Partita {
    private String codice;
    ArrayList <Utente> Partecipanti;
    public Partita(){
        this.codice= generateRandomString(6);
        Partecipanti = new ArrayList<>();
        salvaPartita();

    }

    public Partita(String codice){
        this.codice=codice;
        caricaPartita(codice);
        
    }

        private void salvaPartita() {
        try {
            String fileName = "progettouni/json/Partite.json";
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

    private void caricaPartita(String codice){

         try {
            String fileName = "progettouni/json/Partite.json";
            Gson gson = new Gson();

            // Leggi il contenuto del file JSON esistente
            if (Files.exists(Paths.get(fileName))) {
                try (FileReader reader = new FileReader(fileName)) {
                    
                    List<Partita> partite = gson.fromJson(reader,List.class);

                    // Cerca la partita con il codice specificato
                    Optional<Partita> partitaOptional = partite.stream()
                            .filter(partita -> partita.getCode().equals(codice))
                            .findFirst();

                    // Se la partita è presente, carica i partecipanti
                    partitaOptional.ifPresent(partita -> {
                        this.Partecipanti = new ArrayList<>(partita.getPlayers());
                    });
                }
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
