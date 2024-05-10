package com.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.image.Image;

//import com.DTO.BotSmart;
import com.DTO.Carta;
import com.DTO.CartaRubaSpacca;
import com.DTO.CartaSpacca;
import com.DTO.Partita;
import com.DTO.Utente;

public class ControllerPartita implements Initializable {

    private Stage stage;
    private Scene scene;
    private String codice;
    private Partita p;
    private ArrayList<Utente> partecipanti;
    private Mazzo m;
    private MazzoSpacca mSpacca;
    private Utente utenteCorrente;
    private int statoTurno;

    private ImageView selectedImage;
    private ImageView showSelectedCard;
    @FXML
    private Button pescaBtn;
    @FXML
    private Button scartaBtn;
    
    @FXML
    private ImageView cartaDaGioco1;

    @FXML
    private ImageView cartaDaGioco2;

    @FXML
    private ImageView cartaDaGioco4;

    @FXML
    private ImageView cartaDaGioco3;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        /*// Ottieni l'URL dell'immagine
        URL imageUrl = getClass().getResource("file:///C:/Users/bibic/ONeDrive/Desktop/JavaProjectUniverity/JavaProjectUniversity/progettouni/src/main/resources/com/example/img/coltello1.png");
        
    
        if (imageUrl != null) {
            // Crea un'istanza dell'immagine
            System.out.println("URL dell'immagine: " + imageUrl);
            Image image = new Image(imageUrl.toString());
    
            // Imposta l'immagine nella ImageView
            cartaDaGioco1.setImage(image);
        } else {
            System.out.println("Impossibile trovare l'immagine");
        } */ 
    }

    public void start(String codice) {
        System.out.println(codice);
        p = new Partita(codice);
        m = new Mazzo();
        partecipanti = p.getPartecipanti();
        mSpacca = new MazzoSpacca(partecipanti.size());

        System.out.println(p.toString());
        // diamo le carte a tutti i partecipanti
        for (Utente u : partecipanti) {
            for (int i = 0; i < 3; i++) {
                u.mano.add(m.getCartaDiGioco());
            }
        }
        utenteCorrente = partecipanti.get(0);

        setScene(utenteCorrente);

    }


    public void setScene(Utente u) {
    /*if(u instanceof BotSmart) {
        // Impostazione della scena per il bot
        System.out.println(u.mano.toString());
        System.out.println(mSpacca.mazzo.size());  
        // qui dentro settiamo la scena dell'utente BOT
        //wait x
        pesca();
        //wait x
        BotSmart bot = new BotSmart(utenteCorrente);

        switch (bot.gioca()) {
            case "Stesso Seme":
                
                break;
            
            case "Scala":
                
                break;
            default:
                break;
        }

    } else {
        // Impostazione della scena per l'utente umano
        System.out.println(u.mano.toString());
        System.out.println(mSpacca.mazzo.size());
      */  
        
        for (int i = 0; i < u.mano.size(); i++) {
            Carta carta = u.mano.get(i);
            String imagePath = carta.getPath(); 
            String imageUrl = "@img/" + imagePath; 
            Image image = new Image(imageUrl);
            switch (i) {
                case 0:
                    cartaDaGioco1.setImage(image);
                    break;
                case 1:
                    cartaDaGioco2.setImage(image);
                    break;
                case 2:
                    cartaDaGioco3.setImage(image);
                    break;
                // Continua con le altre ImageView
                default:
                    break;
            }
        }
    }


    public void pesca() {
        if (utenteCorrente.mano.size() == 3) {
            Carta pescata = this.m.mazzo.pop();
            // metto la carta nel box
            // delay
            // se carta numero ti chiedo quale carta vuoi scartare se carta effetto esce
            // pop-up che ti chiede se vuoi usare effetto scartare la carta
            if (pescata.getClass().getName().equals("com.DTO.Carta")) {
                System.out.println("Selaziona una carta da scartare");
            }
            // nel caso di carta malus spiego direttamente il malus e puoi schiacciare solo
            // ok per prendere malus e andare avanti

            actionCarta(pescata);
        } else {
            System.out.println("Hai già pescato");
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Errore");
            alert.setHeaderText(null);
            alert.setContentText("Non puoi passare il turno se hai meno di 3 carte, prima devi pescare.");

            alert.showAndWait(); // Mostra il pop-up e attendi che venga chiuso
        }

    }

    public void actionCarta(Carta c) {

        switch (c.getClass().getName()) {
            case "com.DTO.CartaBlocco":

                break;

            case "com.DTO.CartaJollyNumero":

                break;

            case "com.DTO.CartaJollySeme":

                Stage semeStage = new Stage();
                semeStage.initModality(Modality.APPLICATION_MODAL);
                semeStage.setTitle("Scegli il seme");

                ComboBox<String> semeComboBox = new ComboBox<>();
                semeComboBox.getItems().addAll("FORCHETTE", "COLTELLI", "CUCCHIAI");

                Button inviaButton = new Button("Invia");
                inviaButton.setOnAction(e -> {
                    String semeScelto = semeComboBox.getValue();
                    if (semeScelto != null) {
                        // Qui salvi il valore selezionato
                        // Ad esempio, puoi passarlo a un metodo per fare qualcosa con esso
                        // Esempio: metodoPerGestireSeme(semeScelto);
                        utenteCorrente.mano.add(new Carta(semeScelto));
                        System.out.println("Seme selezionato: " + semeScelto);
                        semeStage.close();
                    } else {
                        Alert alert = new Alert(AlertType.WARNING);
                        alert.setTitle("Attenzione");
                        alert.setHeaderText(null);
                        alert.setContentText("Seleziona un seme prima di inviare.");
                        alert.showAndWait();
                    }
                });

                VBox semeLayout = new VBox(10);
                semeLayout.getChildren().addAll(semeComboBox, inviaButton);
                semeLayout.setPrefSize(200, 100);
                semeLayout.setAlignment(Pos.CENTER);

                semeStage.setScene(new Scene(semeLayout));
                semeStage.showAndWait();
                break;

            case "com.DTO.CartaPerdiSpacca":
                System.out.println(
                        "Hai perso la carta Spacca: " + utenteCorrente.getCarteSpacca().remove(utenteCorrente.getCarteSpacca().size() - 1).lettera);
                break;

            case "com.DTO.CartaPescaDueCarte":
                pesca();
                scarta();
                pesca();
                scarta();
                break;

            case "com.DTO.CartaRubaSpacca":

                // pup-up che chiede a quale utente vuoi rubare la carta
                String utenteScelto = "ciccio";
                CartaRubaSpacca ruba = new CartaRubaSpacca();
                Optional<Utente> utenteOptional = partecipanti.stream().filter(p -> p.getNick().equals(utenteScelto)).findFirst();

                utenteOptional.ifPresent(utente -> {
                    for (CartaSpacca cartaSpacca : utente.getCarteSpacca()) {
                        if (canGrab(cartaSpacca)) {
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

    public boolean canGrab(CartaSpacca c) {
        String[] lettere = { "S", "P", "A", "C", "C", "A" };
        ArrayList<CartaSpacca> carteUtente = utenteCorrente.getCarteSpacca();

        if (carteUtente.size() >= lettere.length) {
            return false; // Non posso aggiungere altre carte
        }

        // Itero attraverso i caratteri della parola 'SPACCA'
        for (int i = 0; i < lettere.length; i++) {
            // Se ho già una carta corrispondente nella posizione corrente
            if (carteUtente.size() > i && carteUtente.get(i).equals(lettere[i])) {
                continue; // Continuo con il prossimo carattere
            }
            // Se il carattere corrente non è presente nella lista delle carte utente oppure è
            // già stato aggiunto
            if (!carteUtente.contains(lettere[i]) || (carteUtente.contains(lettere[i]) && carteUtente.indexOf(lettere[i]) < i)) {
                return false; // Non posso aggiungere la carta
            }
        }
        return true; // Tutti i controlli passati, posso aggiungere la carta
    }

    public void passa() {
        if (utenteCorrente.mano.size() == 3) {
            int currentIndex = partecipanti.indexOf(utenteCorrente);
            if (currentIndex == partecipanti.size() - 1) {
                utenteCorrente = partecipanti.get(0);
            } else {
                utenteCorrente = partecipanti.get(currentIndex + 1);
            }

            setScene(utenteCorrente);
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Errore");
            alert.setHeaderText(null);
            alert.setContentText("Non puoi passare il turno se hai meno di 3 carte, prima devi pescare.");

            alert.showAndWait(); // Mostra il pop-up e attendi che venga chiuso
        }

    }

    public void scarta() {

        if (utenteCorrente.mano.size() >= 3) {
            // prendi la carta che viene selezionata e rimuovila

            //aggiungi la carta pescata nella mano dell'utente in scena
            /* 
            if(utenteCorrente.controllaScala()){
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Hai fatto scala");
                alert.setHeaderText(null);
                alert.setContentText("Hai vinto una carta spacca, al prossimo turno dovrai scartare una delle carte in mano");
                alert.showAndWait(); // Mostra il pop-up e attendi che venga chiuso
                
                utenteCorrente.carteSpacca.add(mSpacca.getRandomCard());
                //aggiorna carte spacca utente in scena
            }

            if(utenteCorrente.controllaTreStessoSeme()){
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Hai un tris di semi");
                alert.setHeaderText(null);
                alert.setContentText("Hai vinto una carta spacca, al prossimo turno dovrai scartare una delle carte in mano");
                alert.showAndWait(); // Mostra il pop-up e attendi che venga chiuso

                utenteCorrente.carteSpacca.add(mSpacca.getRandomCard());
                //aggiorna carte spacca utente in scena
            }*/
        } else {
            System.out.println("non puoi scartare ");

            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Errore");
            alert.setHeaderText(null);
            alert.setContentText("Non puoi scartare");

            alert.showAndWait(); // Mostra il pop-up e attendi che venga chiuso
        }

    }

    @FXML
    public void pescaBtnAction(ActionEvent event) {
        pesca();
    }

    @FXML
    public void scartaBtnAction(ActionEvent event) {

    }

}
