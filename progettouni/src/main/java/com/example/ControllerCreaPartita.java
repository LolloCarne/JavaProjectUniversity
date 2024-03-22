package com.example;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.DTO.Partita;
import com.DTO.Utente;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControllerCreaPartita implements Initializable{

    private Stage stage;
    private Scene scene;
        @FXML
    private TextField campoCodice;

    @FXML
    private TextField nickNameField;
      @FXML
    private Button IndietroBtn;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        //roba su scena creazionePartita
        Partita p = new Partita(); 
        campoCodice.setText(p.getCodice());
    }

        @FXML
    void addUtenteAction(ActionEvent event) throws IOException{
        String codice= campoCodice.getText();
        Partita p = new Partita(codice);

        p.addPartecipante(new Utente(nickNameField.getText()));

    }

        @FXML
    void goBack(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("homeScene.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    
}
