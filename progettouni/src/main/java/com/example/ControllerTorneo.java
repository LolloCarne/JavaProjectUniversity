package com.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import com.DTO.Torneo;
import com.Manager.TorneoManager;

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

    private String nomiBot="";

    public int flagSmart=0;
    public int flagStupid=0;
    private Stage stage;
    private Scene scene;
    
    public String nome;
    public int n;
    public TorneoManager manager;
    String nomeTotal="";
    
    Torneo t = new Torneo();
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
                
    }

    @FXML
    void smartBotAction(ActionEvent event) {
        manager = new TorneoManager();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Okay Bot Smart aggiunto");
        alert.setHeaderText(null);
        alert.setContentText("\nAggiuno bot smart.");
        alert.showAndWait(); // Mostra il pop-up e attendi che venga chiuso

        String nomeBot = manager.creaBotSmart();

        //stringa con tutti i nomi dei bot, sia Stupid che Smart
        nomiBot =nomiBot + nomeBot  + "," ;  
    }

    @FXML
    void stupidBotAction(ActionEvent event) {
        manager = new TorneoManager();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Okay Bot Stupid aggiunto");
        alert.setHeaderText(null);
        alert.setContentText("\nAggiuno bot stupid.");
        alert.showAndWait(); // Mostra il pop-up e attendi che venga chiuso
        String nomeBot = manager.creaBotStupid();
        //stringa con tutti i nomi dei bot, sia Stupid che Smart
        nomiBot =nomiBot + nomeBot  + "," ;
        System.out.println("nomi Bot" + nomiBot);
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
        TorneoManager manager = new TorneoManager();
        nome = nomiTorneo.getText();
        nomeTotal = nome + "," + nomiBot;
        manager.scriviDizionario(nomeTotal, n);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Codice Prima Partita");
        alert.setHeaderText(null);
        
        alert.setContentText(manager.creaPartiteTorneo().getCodice() + "\nSalva il codice ed accedi come utente tramite esso.");
        alert.showAndWait(); // Mostra il pop-up e attendi che venga chiuso
    }
   
}
