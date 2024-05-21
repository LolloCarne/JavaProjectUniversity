package com.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage primaryStage) throws IOException {
        // Schermata principale con tre bottoni
          try {
            Parent root = FXMLLoader.load(getClass().getResource("homeScene.fxml"));
            primaryStage.setTitle("SPACCA");
            primaryStage.setResizable(false);
            primaryStage.setScene(new Scene(root));
            primaryStage.show();

            primaryStage.setOnCloseRequest(event -> {
                event.consume();
                esci(primaryStage);
            });
           
        }catch(Exception e){
            e.printStackTrace();
        }
    }

        public void esci(Stage primaryStage){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Esci");
        alert.setHeaderText("Stai per uscire dal gioco");
        alert.setContentText("Sei sicuro di voler uscire?");

        if(alert.showAndWait().get() == ButtonType.OK){
            primaryStage.close();
        }
    }


    public static void main(String[] args) {
        launch();
    }

}