package com.example;

import java.io.IOException;

import com.DTO.Utente;
import com.Helper.Partita;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import javafx.stage.Stage;
import javafx.scene.Node;

public class Controller {

    private Stage stage;
    private Scene scene;


    @FXML
    private Button GiocaBotBtn;

    @FXML
    private Button uniscitiBtn;

    @FXML
    private TextField tfCodice;

    @FXML
    private TextField tfNickname;

    @FXML
    private Button amministratorBtn;

    @FXML
    private Button creaPartitaBtn;

    @FXML
    private TextField campoCodice;

    @FXML
    private Button IndietroBtn;

    @FXML
    void goBack(ActionEvent event) throws IOException {
       
        Parent root = FXMLLoader.load(getClass().getResource("homeScene.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        
    }

    @FXML
    void onBtnClick1(ActionEvent event) throws IOException{
        
        Parent root = FXMLLoader.load(getClass().getResource("utenteScene.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void onBtnClick2(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("menuAdminScene.fxml")); //nome scena successiva
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
   
     
     @FXML
    void goToGiocaBot(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("schermataUtente.fxml")); //nome scena successiva
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
     @FXML
    void goToUnisciti(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("uniscitiScene.fxml")); //nome scena successiva
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    
    }




    @FXML
    void onBtnLogin(ActionEvent event) {
        try {
            //p.addPartecipante();
        } catch (Exception e) {
            // System.out.print("NUmero giocatori massimo superato!");
        }
    }

    @FXML
    void creaPartitaAction(ActionEvent event) throws IOException{
        Partita p = new Partita(); 
        Parent root = FXMLLoader.load(getClass().getResource("creazionePartitaScene.fxml")); //nome scena successiva
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        String temp= campoCodice.getText();

        campoCodice.setText(temp+p.getCode());

    }

    @FXML
    void addUtenteAction(ActionEvent event) throws IOException{}
}



