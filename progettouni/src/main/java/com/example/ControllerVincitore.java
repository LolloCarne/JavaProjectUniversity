package com.example;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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

public class ControllerVincitore implements Initializable{
    private Stage stage;
    private Scene scene;

    private Utente u;

    @FXML
    private Button IndietroBtn;

    @FXML
    private TextField vincitoreSpaccaField;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        //roba su scena creazionePartita 
        setTesto();
    }
     

    @FXML
    void goBack(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("homeScene.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void setTesto(){
        vincitoreSpaccaField.setText(u.getNick());
    }

    public void getUtente(Utente ut){
        u=ut;
    }

    }
    
