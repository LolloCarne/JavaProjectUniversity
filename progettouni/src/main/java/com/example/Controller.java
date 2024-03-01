package com.example;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;

public class Controller {

    private Stage stage;
    private Scene scene;
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

}

