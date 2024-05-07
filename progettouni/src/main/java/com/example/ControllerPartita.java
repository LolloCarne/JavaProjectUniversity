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
import java.util.Optional;
import java.util.ResourceBundle;


import com.DTO.Carta;
import com.DTO.CartaRubaSpacca;
import com.DTO.CartaSpacca;
import com.DTO.Partita;
import com.DTO.Utente;

public class ControllerPartita implements Initializable{
    
    private Stage stage;
    private Scene scene;
    private String codice;
    private Partita p;
    private ArrayList <Utente> partecipanti;
    private Mazzo m;
    private MazzoSpacca mSpacca;
    private Utente utenteCorrente;
    private int statoTurno;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        

    }

    public void start(String codice) {
        System.out.println(codice);
        p= new Partita(codice);
        m= new Mazzo();
        partecipanti=p.getPartecipanti();
        mSpacca = new MazzoSpacca(partecipanti.size());
       
        System.out.println(p.toString());
        //diamo le carte a tutti i partecipanti 
        for(Utente u : partecipanti){
            for(int i=0; i<2; i++){
                u.mano.add(m.getCartaDiGioco());
            }
        }
        utenteCorrente = partecipanti.get(0);

        setScene(utenteCorrente);

    }

    public void setScene(Utente u){
        //qui dentro settiamo la scena dell'utente successivo
        System.out.println(u.mano.toString());
        System.out.println(mSpacca.mazzo.size());
        //pesca();
    }

    public void pesca(){
        Carta pescata= this.m.mazzo.pop();
        //metto la carta nel box
        //delay
        //se carta numero ti chiedo quale carta vuoi scartare se carta effetto esce pop-up  che ti chiede se vuoi usare effetto scartare la carta
        if(pescata.getClass().getName().equals("com.DTO.Carta")){
            System.out.println("Selaziona una carta da scartare");
        }
        //nel caso di carta malus spiego direttamente il malus e puoi schiacciare solo ok per prendere malus e andare avanti

        actionCarta(pescata);
    }

    public void actionCarta(Carta c){

        switch (c.getClass().getName()) {
            case "com.DTO.CartaBlocco":
                
                break;

            case "com.DTO.CartaJollyNumero":
                
                break;
            
            case "com.DTO.CartaJollySeme":
                
                break;
            
            case "com.DTO.CartaPerdiSpacca":
                 System.out.println("Hai perso la carta Spacca: " + utenteCorrente.getCarteSpacca().remove(utenteCorrente.getCarteSpacca().size()-1).lettera);
                break;

            case "com.DTO.CartaPescaDueCarte":
                pesca();
                scarta();
                pesca();
                scarta();
                break;

            case "com.DTO.CartaRubaSpacca":
                
                //pup-up che chiede a quale utente vuoi rubare la carta
                String utenteScelto="ciccio";
                CartaRubaSpacca ruba = new CartaRubaSpacca();
                Optional<Utente> utenteOptional = partecipanti.stream()
                .filter(p -> p.getNick().equals(utenteScelto))
                .findFirst();

                utenteOptional.ifPresent(utente->{
                    for(CartaSpacca cartaSpacca: utente.getCarteSpacca()){
                        if(canGrab(cartaSpacca)){
                         utenteCorrente.carteSpacca.add(cartaSpacca);
                         utente.getCarteSpacca().remove(cartaSpacca);  
                        }
                    }

                });
                break;

            default:
                break;
        }
    }

    public boolean canGrab(CartaSpacca c){

        //controllo che la carta spacca che rubo dal mio avversario posso prenderla effettivamente o la ho gia
        return true;
    }

    public void passa(){
        int currentIndex = partecipanti.indexOf(utenteCorrente);
        if(currentIndex == partecipanti.size()-1){
            utenteCorrente = partecipanti.get(0);
        }else{
            utenteCorrente = partecipanti.get(currentIndex+1);
        }

        setScene(utenteCorrente);
    }

    public void scarta(){

    }

}
