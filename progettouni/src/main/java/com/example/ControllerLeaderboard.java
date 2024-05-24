package com.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.TreeMap;
import java.util.HashMap;
import java.util.Map;


//import com.DTO.BotSmart;
import com.DTO.Torneo;
import com.DTO.Partita;
import com.DTO.Utente;
import com.Manager.PartitaManager;
import com.Manager.TorneoManager;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Optional;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class ControllerLeaderboard implements Initializable{

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        //roba su scena creazionePartita 
       
    }

    private Stage stage;
    private Scene scene;

    @FXML
    private Button IndietroBtn;

    @FXML
    private Button mostraLeaderBoardBtn;


    @FXML
    private ListView<String> leaderboardList;

    @FXML
    private TextArea leaderboardArea;

    @FXML
    void goBack(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("homeScene.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void mostraLeaderBoardAction(ActionEvent event) {
        System.out.println("si");

        PartitaManager manager = new PartitaManager();
        ArrayList<Partita> leggijson = manager.leggiJson();

        // List to hold all entries for sorting
        List<Map.Entry<String, String>> allEntries = new ArrayList<>();

        for (Partita par : leggijson) {
            HashMap<String, String> vinte = par.partiteVintePartecipanti();
            allEntries.addAll(vinte.entrySet());
        }

        // Comparator for sorting keys in descending order
        Comparator<Map.Entry<String, String>> keyComparator = new Comparator<Map.Entry<String, String>>() {
            @Override
            public int compare(Map.Entry<String, String> e1, Map.Entry<String, String> e2) {
                return e2.getKey().compareTo(e1.getKey());
            }
        };

        // Sort all entries by key in descending order
        allEntries.sort(keyComparator);

        // Clear leaderboardArea before adding new sorted entries
        leaderboardArea.clear();

        // Add sorted entries to the leaderboardArea
        for (Map.Entry<String, String> entry : allEntries) {
            String key = entry.getKey();
            String value = entry.getValue();
            String stringa = key + ", " + value + "\n";
            leaderboardArea.appendText(stringa);
        }
    }

}