/*
package com.Manager;

public class UtenteManager {
    
} */

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


public class UtenteManager {

    private static final String filename="progettouni/json/Utente.json";
    private static final ObjectMapper objectMapper = new ObjectMapper()
    .enable(SerializationFeature.INDENT_OUTPUT);

    public void creaUtente(Utente u){

        ArrayList <Utente> jsonList = leggiJson();
        jsonList.add(u);
       // scriviJson(jsonList);
    }
        public ArrayList<Utente> leggiJson(){
            ArrayList <Utente> nuovaLista = new ArrayList<>();
            try {

                // Leggi il contenuto del file JSON esistente
                if (Files.exists(Paths.get(filename))) {
                    JsonNode listaUtenti= objectMapper.readTree(Paths.get(filename).toFile());
                    for(JsonNode x : listaUtenti){
                    
                    ArrayList <Utente> Nickname = new ArrayList<>();
                    for(JsonNode nick : x.get("Nickname")){
                        Nickname.add(new Utente(nick.toString()));
                    }

                    ArrayList <Utente> Id = new ArrayList<>();
                    for(JsonNode id : x.get("id")){
                        Id.add(new Utente(id.toString()));
                    }
                }

                //pulisco i campi codice da tutti i caratteri di escape che si possono creare nel mapping
                /*String nuovoCodice= x.get("codice").toString().replace("\"", "").replace("\\", ""); 
                nuovaLista.add(new Partita(nuovoCodice,tmpUtenti));
                }*/
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        return nuovaLista;
    }
}
