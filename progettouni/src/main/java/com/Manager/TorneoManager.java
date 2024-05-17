package com.Manager;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import java.util.Random;



import com.DTO.Torneo;
import com.DTO.Utente;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.HashMap;
import java.util.Map;

public class TorneoManager {
    private static final String filename="progettouni/json/Torneo.json";
    private static final ObjectMapper objectMapper = new ObjectMapper()
    .enable(SerializationFeature.INDENT_OUTPUT);

    HashMap<String, String[]> partecipantiTorneo = new HashMap<>();
    String codiceT;


    public void creaTorneo(Torneo t){

        ArrayList <Torneo> jsonList = leggiJson();

        jsonList.add(t);
        try {
            scriviJson(partecipantiTorneo, codiceT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("entrato");   
    
    }
    public ArrayList<Torneo> leggiJson(){
        ArrayList<Torneo> nuovaLista = new ArrayList<>();
        try{
            if(Files.exists(Paths.get(filename))){
                JsonNode listaTornei = objectMapper.readTree(Paths.get(filename).toFile());
                for(JsonNode x :listaTornei){
                    ArrayList <Utente> tmpUtenti = new ArrayList<>();
                    for(JsonNode utente : x.get("Nickname")){
                        tmpUtenti.add(new Utente(utente));
                    }
                    String nuovoCodice= x.get("Codice").toString().replace("\"", "").replace("\\", ""); 
                    nuovaLista.add(new Torneo(tmpUtenti, nuovoCodice));
                }
                }
            }
        catch (IOException e) {
            e.printStackTrace();
        }
        
        return nuovaLista;
            
    }

    /*public static void scriviJson(HashMap<String, ArrayList<String>> torneo) {
        try {
            String jsonString = objectMapper.writeValueAsString(torneo);
            Files.write(Paths.get(filename), jsonString.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
    public void scriviJson(HashMap<String, String[]> partecipantiTorneo, String codice) throws IOException {
        // Crea un ObjectMapper per lavorare con JSON
        ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
        ObjectNode jsonRoot = objectMapper.createObjectNode();

        // Leggi il file JSON esistente
        File file = new File("progettouni/json/Torneo.json");

        if (file.exists()) {
            try{
            jsonRoot = (ObjectNode) mapper.readTree(file);
            }
            catch(Exception e) {
            e.printStackTrace();
            }
        }
        System.out.println("qui");

        ArrayNode partite = mapper.createArrayNode();
        ObjectNode partiteNode = objectMapper.createObjectNode();
        String[] nomi ={};
        
        for (Map.Entry<String, String[]> entry : partecipantiTorneo.entrySet()) {
            String key = entry.getKey();
            String[] value = entry.getValue();
            codice =key;
            System.out.println(codice);
            nomi = value;
            
        }

        partiteNode.put("Codice:", codice);
        partiteNode.putPOJO("Partecipanti", nomi);
        partite.add(partiteNode);

        //partite potrebbe diventare il nome del torneo 
        jsonRoot.set("partite", partite);

        try{
            mapper.writeValue(file, jsonRoot);
        }
        catch(IOException e){
            e.printStackTrace();
        }
        System.out.println("qui2");

        // Scrivi il JSON aggiornato nel file
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
        String[] nomi = metodo(n, nome);
        Utente u = new Utente(nome);
        String codiceTorneo = generateRandomString(6);
        codiceT=codiceTorneo;
        partecipantiTorneo.put(codiceTorneo, nomi);
        u.setCodiceTorneo(codiceTorneo);
        stampaDizionario(partecipantiTorneo);   
        return partecipantiTorneo;
    }
    public String[] metodo(int n, String nome){
        String[] nomi = new String[n];
        //crea array di nomi 

        nomi =nome.split(",");
        return nomi;
        
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
   /*  public ArrayList<String> creaArray(int n, String nome, ArrayList<String> nomi){
        
        //for (int i=0; i<n; i++){
        nomi.add(nome);
        //}
    
        for( String s : nomi){
            System.out.println(s);
        }
        return nomi;

    }*/



}

