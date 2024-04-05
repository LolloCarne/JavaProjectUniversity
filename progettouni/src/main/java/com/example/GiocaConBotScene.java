package com.example;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GiocaConBotScene {

        private Scene scene;
        private Stage primaryStage;
        public GiocaConBotScene(Stage primaryStage) {
        // Inizializza la scena per "Gioca con Bot"
        this.primaryStage = primaryStage;
        // Inizializza la scena per "Crea Partita"
        Button backButton = new Button("Indietro");
        backButton.setOnAction(e -> {
            // Torna alla schermata principale
           // MainScreen mainScreen = new MainScreen();
           // mainScreen.start(primaryStage);
        });

        VBox root = new VBox(20);
        root.getChildren().addAll(backButton);

        this.scene = new Scene(root, 300, 200);
    }

    public Scene getScene() {
        return this.scene;
    }
}
