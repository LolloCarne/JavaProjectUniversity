package com.Manager;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.DTO.Partita;
import com.DTO.Utente;

import java.io.*;

public class SessioneManager {
    private static final String runtimeFile= "progettouni/runtime/Partita.json";


    private static JSONObject leggiPartitaRegister() {
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = null;
        try (FileReader reader = new FileReader(runtimeFile)) {
            Object obj = parser.parse(reader);
            jsonObject = (JSONObject) obj;
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    private static void scriviPartitaRegister(JSONObject jsonObject) {
        try (FileWriter file = new FileWriter(runtimeFile)) {
            file.write(jsonObject.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void iniziaPartita(Partita p){
        
        JSONObject jsonObj = new JSONObject();

        jsonObj.put("codice",p.getCodice());

        for(Utente u : p.getPartecipanti()){
            JSONObject giocatore = new JSONObject();
            giocatore.put("carteSpacca", "");
            giocatore.put("carteInMano", "");

            jsonObj.put((String) u.getNick(), giocatore);
        }

        scriviPartitaRegister(jsonObj);
    }

    public Partita loadCurrentSession(){
        JSONObject jsonObjectLetto = leggiPartitaRegister();
        Partita p= new Partita(jsonObjectLetto.get("codice").toString());
        return p;
    }



}
