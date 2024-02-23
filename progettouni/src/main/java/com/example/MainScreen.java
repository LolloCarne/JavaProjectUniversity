package com.example;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainScreen {
    private Scene scene;
    public void start(Stage primaryStage) {
        // Schermata principale con tre bottoni
        Button creaPartitaButton = new Button("Crea Partita");
        Button uniscitiAPartitaButton = new Button("Unisciti A Partita");
        Button giocaConBotButton = new Button("Gioca con Bot");

        VBox root = new VBox(20);
        root.getChildren().addAll(creaPartitaButton, uniscitiAPartitaButton, giocaConBotButton);

        Scene mainScene = new Scene(root, 300, 200);

        // Azioni dei bottoni
        creaPartitaButton.setOnAction(e -> {
            CreaPartitaScene creaPartitaScene = new CreaPartitaScene(primaryStage);
            primaryStage.setScene(creaPartitaScene.getScene());
        });

        uniscitiAPartitaButton.setOnAction(e -> {
            UniscitiAPartitaScene uniscitiAPartitaScene = new UniscitiAPartitaScene(primaryStage);
            primaryStage.setScene(uniscitiAPartitaScene.getScene());
        });

        giocaConBotButton.setOnAction(e -> {
            GiocaConBotScene giocaConBotScene = new GiocaConBotScene(primaryStage);
            primaryStage.setScene(giocaConBotScene.getScene());
        });

        primaryStage.setScene(mainScene);
        primaryStage.setTitle("Schermata Principale");
        primaryStage.show();
    }

    public Scene getScene() {
        return this.scene;
    }
}
