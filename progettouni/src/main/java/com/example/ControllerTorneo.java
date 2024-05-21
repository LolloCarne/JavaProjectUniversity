package com.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.HashMap;
import java.util.Map;
import javafx.fxml.FXMLLoader;

import java.util.Dictionary;
import java.util.Hashtable;

import javafx.stage.Stage;
import javafx.scene.Node;


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

public class ControllerTorneo implements Initializable {

    

    @FXML
    private TextField nomiTorneo;

    @FXML
    private Button numBotButton;

    @FXML
    private TextField numeroBot;

    @FXML
    private TextField numeroPartecipanti;

    @FXML
    private Button okayNomiTorneoButton;

    @FXML
    private Button okayNumButton;

    @FXML
    private Button smartBotButton;

    @FXML
    private Button stupidBotButton;
    @FXML
    private Button iniziaBtn;
    
    @FXML
    private TextField codiceRegole;

    @FXML
    private Button GiocaBotBtn;

    public int flagSmart=0;
    public int flagStupid=0;
    private Stage stage;
    private Scene scene;
    
    public String nome;
    public int n;
    public TorneoManager manager = new TorneoManager();
    
    Torneo t = new Torneo();
    private static final String filename="progettouni/json/Torneo.json";
    private static final ObjectMapper objectMapper = new ObjectMapper()
    .enable(SerializationFeature.INDENT_OUTPUT);
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        //roba su scena creazionePartita 
        
    }
   
    
    //setta i partecipanti del torneoo 
    @FXML
    void okayNextAction(ActionEvent event) {
        nome = nomiTorneo.getText();
        //n = Integer.parseInt(numeroPartecipanti.getText());
        manager.scriviDizionario(nome, n);
        
    }

    /*@FXML
    void okayNumAction(ActionEvent event) {
        //nome = nomiTorneo.getText();
        n = Integer.parseInt(numeroPartecipanti.getText());
       // manager.scriviDizionario(nome,n);
        

    }*/

    @FXML
    void okayNumBotAction(ActionEvent event) {
        String numero = numeroBot.getText();
        if (flagSmart==1){
            //creo bot numero bot smart
            flagSmart=0;
            //partecipantiToreno.put(//bot);
        }
        else if(flagStupid ==1){
            //creo bot numero bot stupid
            flagStupid=0;
             //partecipantiToreno.put(//bot);
        }
    }

    @FXML
    void smartBotAction(ActionEvent event) {
        int flagSmart =1;
    }

    @FXML
    void stupidBotAction(ActionEvent event) {
        int flagStupid =1;
    }

    
    @FXML
    void goBack(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("homeScene.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void iniziaTorneoAction(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Codice Prima Partita");
        alert.setHeaderText(null);
        TorneoManager m = new TorneoManager();
        alert.setContentText(m.creaPartiteTorneo().getCodice() + "\nSalva il codice ed accedi come utente tramite esso.");
        alert.showAndWait(); // Mostra il pop-up e attendi che venga chiuso
    }

    
}
