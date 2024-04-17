package com.DTO;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class CartaPescaDueCarte extends Carta{

    public CartaPescaDueCarte(){
        super();
    }

    public void messaggio(){
        Alert alert = new Alert(AlertType.WARNING,"", ButtonType.OK);  //new alert object
        alert.setTitle("Hai pescato una carta PESCA 2 CARTE!");  //warning box title
        alert.setHeaderText("Prima pescherai 2 carte e successivamente ne dovrai scartare 2!");// Header
        alert.getDialogPane().setPrefSize(200, 100); //sets size of alert box 
    }

    public void pescaDueCarte(){
        //ho fatto un for perchè è più figo ma si possono direttamente chiamare i 2 metod
        for(int i=0; i<2; i++){
            //richiamo del metodo pesca
        }
        for(int i=0; i<2; i++){
            //richiamo del metodo scarta 
        }
    }
}
