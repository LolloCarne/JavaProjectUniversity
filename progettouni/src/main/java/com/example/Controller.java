package com.example;

import java.io.IOException;

import com.DTO.Utente;

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
    void onBtnClick1(ActionEvent event) throws IOException{
        
        Parent root = FXMLLoader.load(getClass().getResource("utenteScene.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void onBtnClick2(ActionEvent event) {

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

}



