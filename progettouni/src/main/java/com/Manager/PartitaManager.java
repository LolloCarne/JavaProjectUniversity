package com.Manager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Optional;

import com.DTO.Partita;
import com.DTO.Utente;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class PartitaManager {

    private static final String filename="progettouni/json/Partite.json";
    private static final ObjectMapper objectMapper = new ObjectMapper()
    .enable(SerializationFeature.INDENT_OUTPUT);
    
   
    /*metodo che viene chiamato quando viene istanziato un nuovo oggetto partita,
    Inserisce la nuova partita dentro al json*/
    public void creaPartita(Partita p) {

        ArrayList <Partita> jsonList = leggiJson();

        jsonList.add(p);

        scriviJson(jsonList);
    
    }


    /*Recupera una partita partendo da un codice, viene chiamata dal costruttore della 
     * classe partita quando si vuole recuperare una partita partendo dal codice
    */
    public ArrayList<Utente> getPartecipantiByCode(String codice){

        ArrayList<Partita> partiteList = leggiJson();
        
        ArrayList<Utente> partecipanti = new ArrayList<>();
        
        Optional<Partita> partitaOptional = partiteList.stream()
                            .filter(partita -> partita.getCodice().equals(codice))
                            .findFirst();
        
        partitaOptional.ifPresent(partita -> {
            partecipanti.addAll(partita.getPartecipanti()); // Aggiungi tutti i partecipanti alla lista partecipanti
        });
        
        return partecipanti;    
    
    }
    

    public ArrayList<Partita> leggiJson(){
        ArrayList <Partita> nuovaLista = new ArrayList<>();
        try {

            // Leggi il contenuto del file JSON esistente
            if (Files.exists(Paths.get(filename))) {
                JsonNode listaParite= objectMapper.readTree(Paths.get(filename).toFile());
                for(JsonNode x : listaParite){
                
                ArrayList <Utente> tmpUtenti = new ArrayList<>();
                for(JsonNode utente : x.get("Partecipanti")){
                    tmpUtenti.add(new Utente(utente.toString()));
                }
                //pulisco i campi codice da tutti i caratteri di escape che si possono creare nel mapping
                String nuovoCodice= x.get("codice").toString().replace("\"", "").replace("\\", ""); 
                nuovaLista.add(new Partita(nuovoCodice,tmpUtenti));
                }

            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        
        return nuovaLista;
    }

    public void scriviJson(ArrayList<Partita> partite) {
        try {
            String jsonString = objectMapper.writeValueAsString(partite);
            Files.write(Paths.get(filename), jsonString.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void deletePartitaByCode(String codice){
        ArrayList<Partita> partite = leggiJson();
        for(int i = 0; i<partite.size(); i++){
            System.out.println(partite.get(i).getCodice());
            if(partite.get(i).getCodice().equals(codice)){
                
                partite.remove(i);
            }
        }
        scriviJson(partite);
    } 

    
}
