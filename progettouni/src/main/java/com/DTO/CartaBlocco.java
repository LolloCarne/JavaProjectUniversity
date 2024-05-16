package com.DTO;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;


public class CartaBlocco extends Carta {
    
    public void messaggio(){
        Alert alert = new Alert(AlertType.WARNING,"", ButtonType.OK);  //new alert object
        alert.setTitle("Hai pescato una carta blocco!");  //warning box title
        alert.setHeaderText("Salti questo turno!!!");// Header
        //alert.setContentText("File already exists. Overwrite?"); //Discription of warning
        alert.getDialogPane().setPrefSize(200, 100); //sets size of alert box 
    }
     
   
    public CartaBlocco(String path){
        super();
        super.setPath(path);
    }

    //SETTARE UN ALERT PER IL MESSAGGIO DI SALTO TURNO 
    //+ INVOCARE UN METODO PER PASSARE AL PROX GIOCATORE 
}

