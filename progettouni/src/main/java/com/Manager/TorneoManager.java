package com.Manager;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import java.util.Random;



import com.DTO.Torneo;
import com.DTO.Partita;
import com.DTO.Utente;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.List;

public class TorneoManager {
    private static final String filename="progettouni/json/Torneo.json";
    private static final ObjectMapper objectMapper = new ObjectMapper()
    .enable(SerializationFeature.INDENT_OUTPUT);

    HashMap<String, String[]> partecipantiTorneo = new HashMap<>();
    String codiceT;
    ArrayList<Utente> partecipanti = new ArrayList<>();
    Partita p;
    int partiteDaGiocare;


    public void creaTorneo(Torneo t){
        partiteDaGiocare = t.getPartiteDaGiocare();
        p = t.getPartitaTorneo();
    }
        
    public HashMap<String, String[]> leggiJson() {
        String FILE_PATH = "progettouni/json/Torneo.json";
        HashMap<String, String[]> partiteMap = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // Legge l'intero file JSON come un albero di nodi
            JsonNode rootNode = objectMapper.readTree(new File(FILE_PATH));
            JsonNode partiteNode = rootNode.path("partite");

            // Itera su ogni elemento dell'array "partite"
            for (JsonNode partitaNode : partiteNode) {
                String codice = partitaNode.path("Codice").asText(null);

                List<String> partecipantiList = new ArrayList<>();
                // Ottiene l'array "Partecipanti" e lo converte in una lista di stringhe
                JsonNode partecipantiNode = partitaNode.path("Partecipanti");

                Iterator<JsonNode> partecipantiIterator = partecipantiNode.elements();
                while (partecipantiIterator.hasNext()) {
                    String partecipante = partecipantiIterator.next().asText();
                    partecipantiList.add(partecipante);
                }

                String[] partecipanti = partecipantiList.toArray(new String[0]);
                if (codice != null) {
                    partiteMap.put(codice, partecipanti);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return partiteMap;
    }

    public void scriviJson(HashMap<String, String[]> nuoviDati) {
        String FILE_PATH = "progettouni/json/Torneo.json";
        
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode rootNode;
        ArrayNode partiteArray;

        try {
            // Legge il contenuto esistente del file JSON
            File file = new File(FILE_PATH);
            if (file.exists() && !file.isDirectory()) {
                rootNode = (ObjectNode) objectMapper.readTree(file);
                partiteArray = (ArrayNode) rootNode.path("partite");
            } else {
                // Se il file non esiste, creiamo un nuovo nodo radice e array di partite
                rootNode = objectMapper.createObjectNode();
                partiteArray = objectMapper.createArrayNode();
                rootNode.set("partite", partiteArray);
            }

            // Popola l'array "partite" con i nuovi dati dalla HashMap
            for (Map.Entry<String, String[]> entry : nuoviDati.entrySet()) {
                ObjectNode partitaNode = objectMapper.createObjectNode();
                partitaNode.put("Codice", entry.getKey());

                ArrayNode partecipantiArray = objectMapper.createArrayNode();
                for (String partecipante : entry.getValue()) {
                    partecipantiArray.add(partecipante);
                }

                partitaNode.set("Partecipanti", partecipantiArray);
                partiteArray.add(partitaNode);
            }

            // Scrive l'oggetto JSON combinato sul file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                objectMapper.writerWithDefaultPrettyPrinter().writeValue(writer, rootNode);
                System.out.println("File JSON scritto correttamente: " + FILE_PATH);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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


    
    public HashMap scriviDizionario(String nome, int n){
        String[] nomi = creaListaNomi(n, nome);
        String codiceTorneo = generateRandomString(6);
        Utente u = new Utente(nome);
        partecipanti.add(u);
        codiceT=codiceTorneo;
        partecipantiTorneo.put(codiceTorneo, nomi);
        stampaDizionario(partecipantiTorneo); 
        
        manipolazioneJson(partecipantiTorneo);
        return partecipantiTorneo;
    }

    public String[] creaListaNomi(int n, String nome){
        String[] nomi = new String[n];
        //crea array di nomi 
        nomi =nome.split(",");
        return nomi;
    }


    public void manipolazioneJson(HashMap<String, String[]> partecipantiTorneo){
        HashMap <String, String[]> partecipanti = leggiJson();
        scriviJson(partecipantiTorneo);
    }


    public void stampaDizionario( HashMap partecipantiToreno){
        for (Map.Entry<String, String[]> entry : partecipantiTorneo.entrySet()) {
            String key = entry.getKey();
            String[] value = entry.getValue();

            System.out.println("Torneo: " + key);
            System.out.print("Partecipanti: ");
            for (String partecipante : value) {
                System.out.print(partecipante + " ");
            }
            System.out.println(); // Linea vuota per separare i team
        }
    }

    public Partita creaPartiteTorneo(){
        String codice = generateRandomString(6);
        p = new Partita(codice,partecipanti);
        return p;
    }

    public ArrayList<Utente> getPartecipantiTorneo(){
        return partecipanti;
    }

    public void vincitoreTorneo(){
        if(partiteDaGiocare ==0){
            Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("TORNEO FINITO");
                alert.setHeaderText(null);
                alert.setContentText("COMPLIMENTI HAI SPACCATO!");
                alert.showAndWait(); 
        }
    }
}

