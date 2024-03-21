package com.Helper;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
/*import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;*/
import com.DTO.Utente;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.type.CollectionType;

import javafx.application.Preloader.ErrorNotification;
import javafx.scene.shape.Path;

public class Partita {
    @JsonProperty("codice")
    private String codice;

    @JsonProperty("Partecipanti")
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

    public Partita(String codice, ArrayList <Utente> partecipanti){
        this.codice=codice;
        this.Partecipanti=partecipanti;
    }

    private void salvaPartita() {
        try {
            String fileName = "progettouni/json/Partite.json";
            ObjectMapper objectMapper = new ObjectMapper()
            .enable(SerializationFeature.INDENT_OUTPUT);
            List<Partita> partiteEsistenti;

            // Leggi il contenuto del file JSON esistente
            if (Files.exists(Paths.get(fileName))) {
                //partiteEsistenti =  objectMapper.readValue(Paths.get(fileName).toFile(), new TypeReference<List<Partita>>() {});
                JsonNode listaParite= objectMapper.readTree(Paths.get(fileName).toFile());
                ArrayList <Partita> nuovaLista = new ArrayList<>();
                for(JsonNode x : listaParite){
                ArrayList <Utente> tmpUtenti = new ArrayList<>();
                for(JsonNode utente : x.get("Partecipanti")){
                    tmpUtenti.add(new Utente(utente.toString()));
                }
                nuovaLista.add(new Partita(x.get("codice").toString(),tmpUtenti));
                }
                /*ArrayNode array = objectMapper.valueToTree(nuovaLista);
                ObjectNode companyNode = mapper.valueToTree(company);
                companyNode.putArray("Employee").addAll(array);
                JsonNode result = mapper.createObjectNode().set("company", companyNode);*/
                objectMapper.writeValue(Paths.get(fileName).toFile(),nuovaLista);
            }
               /*  System.out.println(partiteEsistenti.get(0).codice);
            } else {
                partiteEsistenti = new ArrayList<>();
            }

            // Aggiungi la nuova Partita alla lista
            partiteEsistenti.add(this);

            // Scrivi la lista aggiornata nel file JSON
            objectMapper.writeValue(Paths.get(fileName).toFile(), partiteEsistenti);
*/
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void caricaPartita(String codice){

         /*try {
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
        */
    }
    
    public void addPartecipante(Utente u) throws Error{
        if (this.Partecipanti.size()>=4){
            throw new Error();
        }

        Partecipanti.add(u);
    }
    
    public ArrayList<Utente> getPartecipanti(){
        return this.Partecipanti;
    }

    
    public String getCodice(){
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
