package com.DTO;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class CartaPerdiSpacca extends Carta{
    public boolean b;

    public CartaPerdiSpacca(String path){

        super();
        this.b= false;
        super.setPath(path);
    }
    
    public void messaggio(){
        Alert alert = new Alert(AlertType.WARNING,"", ButtonType.OK);  //new alert object
        alert.setTitle("Hai pescato una carta PERDI SPACCA!");  //warning box title
        alert.setHeaderText("Alla prossima partita che vincerai non riceverai la carta spacca!!!");// Header
    }

    //lo setti a true così che quando il giocatore perde la partita ha il flag a true e poi lo risetti a false dopo che 
    //finisce il turno di vittoria

    public void perdiCarta(){
        b=true;
    }

}
