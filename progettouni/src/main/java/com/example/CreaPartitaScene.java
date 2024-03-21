package com.example;

import java.io.IOException;

import com.DTO.Utente;
import com.Helper.Partita;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
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
            MainScreen mainScreen = new MainScreen();
            mainScreen.start(primaryStage);
        });
        Partita p = new Partita();
        Text t = new Text(p.getCodice());
        

        TextField inputTextField = new TextField();
        Text outputText = new Text();

        // Bottone per aggiungere testo
        Button addButton = new Button("Aggiungi Testo");
        addButton.setOnAction(e -> {
            // Ottieni il testo dal campo di testo e lo aggiungi sopra al Text
            String newText = inputTextField.getText();
            outputText.setText(outputText.getText() + "\n" + newText);
            try {
                p.addPartecipante(new Utente(newText));
            } catch (Exception ex) {
                outputText.setText(outputText.getText() + "\n" + "Max 4 giocatori raggiunti");
            }
            // Svuota il campo di testo
            inputTextField.clear();
        });

        VBox root = new VBox(20);
        root.setPadding(new Insets(10));
        root.getChildren().addAll(backButton, inputTextField, addButton, outputText,t);

        this.scene = new Scene(root, 300, 200);

        

        
    }

    public Scene getScene() {
        return this.scene;
    }
}