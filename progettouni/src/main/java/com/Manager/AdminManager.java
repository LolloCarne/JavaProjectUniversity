package com.Manager;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import com.DTO.Admin;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class AdminManager {

    private static final String filename = "progettouni/json/Admin.json";
    private static final ObjectMapper objectMapper = new ObjectMapper()
    .enable(SerializationFeature.INDENT_OUTPUT);

    public boolean login(String username, String password) {
        ArrayList<Admin> admins = leggiJson();
        for (Admin admin : admins) {
            if (admin.getNick().equals(username) && admin.getPassword().equals(password)) {
                return true; // Admin trovato
            }
        }
        return false; // Nessun admin trovato
    }

    public void registraAdmin(String username, String password) {
        ArrayList<Admin> admins = leggiJson();
        if (!adminEsiste(username)) {
            admins.add(new Admin(username, password)); // Creazione di un nuovo admin e aggiunta alla lista
            scriviJson(admins); // Scrittura della lista aggiornata nel file JSON
        }
    }

    private boolean adminEsiste(String username) {
        ArrayList<Admin> admins = leggiJson();
        for (Admin admin : admins) {
            if (admin.getNick().equals(username)) {
                return true; // Admin con lo stesso username trovato
            }
        }
        return false; // Nessun admin con lo stesso username trovato
    }

    private ArrayList<Admin> leggiJson() {
        ArrayList<Admin> adminList = new ArrayList<>();
        try{

            if (Files.exists(Paths.get(filename))) {
                JsonNode rootNode = objectMapper.readTree(Paths.get(filename).toFile());
                for (JsonNode adminNode : rootNode) {
                    
                String username = adminNode.get("nick").asText();
                String password = adminNode.get("password").asText();
                // Aggiungi altri campi se necessario
                adminList.add(new Admin(username, password));
                }
            }
        } catch (IOException e) {
        e.printStackTrace();
        }
        return adminList;
    }

    private void scriviJson(ArrayList<Admin> admins) {
        try {
            String jsonString = objectMapper.writeValueAsString(admins);
            Files.write(Paths.get(filename), jsonString.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }








}

