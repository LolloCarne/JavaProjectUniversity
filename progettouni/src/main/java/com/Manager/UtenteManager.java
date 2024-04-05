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


    public void deleteUtenteByCode(String code){
        //bianca fai cose qui dentro
    }

    public ArrayList<Utente> leggiJson(){
        
        return new ArrayList<>();
    }
    
}
