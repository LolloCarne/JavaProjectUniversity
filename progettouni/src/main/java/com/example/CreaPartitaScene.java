package com.example;

import java.io.IOException;

import com.Helper.Partita;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CreaPartitaScene {

private Scene scene;
private Stage primaryStage;

    public CreaPartitaScene (Stage primaryStage) {
        // Inizializza la scena per "Crea Partita"
        this.primaryStage = primaryStage;
        // Inizializza la scena per "Crea Partita"
        Button backButton = new Button("Indietro");
        backButton.setOnAction(e -> {
            // Torna alla schermata principale
            primaryStage.setScene(new MainScreen().getScene());
        });
        Partita p = new Partita();
        Text t = new Text(p.getCode());
        

        VBox root = new VBox(20);
        root.getChildren().addAll(backButton,t);

        this.scene = new Scene(root, 300, 200);

        

        
    }

    public Scene getScene() {
        return this.scene;
    }
}