package com.example;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.DTO.Partita;
import com.DTO.Utente;

public class ControllerPartita implements Initializable{
    
    private Stage stage;
    private Scene scene;
    private String codice;
    private Partita p;
    private Mazzo m;
    private MazzoSpacca mSpacca;
    private Utente utenteCorrente;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        

    }

    public void start(String codice) {
        System.out.println(codice);
        p= new Partita(codice);
        m= new Mazzo();
        mSpacca = new MazzoSpacca(p.getPartecipanti().size());

        System.out.println(p.toString());
        //diamo le carte a tutti i partecipanti 
        for(Utente u : p.getPartecipanti()){
            for(int i=0; i<2; i++){
                u.mano.add(m.getCartaDiGioco());
            }
        }
        utenteCorrente = p.getPartecipanti().get(0);

        setScene(utenteCorrente);

    }

    public void setScene(Utente u){
        System.out.println(u.mano.toString());
        System.out.println(mSpacca.mazzo.size());
        // una volta che la scena sarà pronta qui dentro verranno settate le varie carte in base al giocatore
    }


}
