package com.example;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.image.Image;

import com.DTO.BotSmart;
//import com.DTO.BotSmart;
import com.DTO.Carta;
import com.DTO.CartaRubaSpacca;
import com.DTO.CartaSpacca;
import com.DTO.Partita;
import com.DTO.Utente;
import com.Enum.Seme;

import javafx.scene.control.TextField;

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

    @FXML
    private TextField nomeUtente;

    
    @FXML
    private ListView carteListView;

    private boolean canPick;

    private Map<ImageView, String> mapImageView;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        
    }

    public void start(String codice) {
        System.out.println(codice);
        p = new Partita(codice);
        m = new Mazzo();
        mapImageView= new HashMap<>();
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
        canPick = true;
    if(u instanceof BotSmart) {
        
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

        System.out.println(u.getNick());
        if (nomeUtente != null) {
            nomeUtente.setText(u.getNick());
        } 
        for (int i = 0; i < u.mano.size(); i++) {
            Carta carta = u.mano.get(i);
            String imagePath = carta.getPath(); 
            Image image = new Image(getClass().getResourceAsStream(imagePath));
            switch (i) {
                case 0:
                    cartaDaGioco1.setImage(image);
                    mapImageView.put(cartaDaGioco1, imagePath);
                    break;
                case 1:
                    cartaDaGioco2.setImage(image);
                    mapImageView.put(cartaDaGioco2, imagePath);
                    break;
                case 2:
                    cartaDaGioco3.setImage(image);
                    mapImageView.put(cartaDaGioco3, imagePath);
                    break;
                // Continua con le altre ImageView
                default:
                    break;
            }
        }
    }
}


    public void pesca() {
        if (utenteCorrente.mano.size() == 3 && canPick) {
            Carta pescata = this.m.mazzo.pop();
            String imagePath = pescata.getPath();
            System.out.println("path:"+imagePath);
            Image image = new Image(getClass().getResourceAsStream(imagePath));
            cartaDaGioco4.setImage(image);
            utenteCorrente.mano.add(pescata);
            mapImageView.put( cartaDaGioco4, imagePath);
            // se carta numero ti chiedo quale carta vuoi scartare se carta effetto esce
            
            if (pescata.getClass().getName().equals("com.DTO.Carta")) {
                System.out.println("Selaziona una carta da scartare");
            }else{
            // nel caso di carta malus spiego direttamente il malus e puoi schiacciare solo
            // ok per prendere malus e andare avanti
                actionCarta(pescata);
            }


            
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

            Random rand = new Random();
            Seme seme = Seme.values()[rand.nextInt(Seme.values().length)];
            Stage NumeroStage = new Stage();
            NumeroStage.initModality(Modality.APPLICATION_MODAL);
            NumeroStage.setTitle("Scegli il Numero");

            ComboBox<Integer> numberCombo = new ComboBox<>();
            numberCombo.getItems().addAll(1, 2, 3,4,5,6,7);

            Button inviaButton = new Button("Invia");
            inviaButton.setOnAction(e -> {
                int numeroScelto = numberCombo.getValue();
                if (numeroScelto != 0) {
                    // Qui salvi il valore selezionato
                    // Ad esempio, puoi passarlo a un metodo per fare qualcosa con esso
                    // Esempio: metodoPerGestireSeme(semeScelto);
                    Carta jollyNumero = new Carta(seme,numeroScelto);
                    String imagePath = jollyNumero.getPath();
                    Image image = new Image(getClass().getResourceAsStream(imagePath));
                    cartaDaGioco4.setImage(image);
                    utenteCorrente.mano.add(jollyNumero);
                    mapImageView.put( cartaDaGioco4, imagePath);
                    utenteCorrente.mano.remove(c);
                    System.out.println("Seme selezionato: " + numeroScelto);
                    NumeroStage.close();
                } else {
                    Alert alert = new Alert(AlertType.WARNING);
                    alert.setTitle("Attenzione");
                    alert.setHeaderText(null);
                    alert.setContentText("Seleziona un nujero prima di inviare.");
                    alert.showAndWait();
                }
            });

            VBox numeroLayout = new VBox(10);
            numeroLayout.getChildren().addAll(numberCombo, inviaButton);
            numeroLayout.setPrefSize(200, 100);
            numeroLayout.setAlignment(Pos.CENTER);

            NumeroStage.setScene(new Scene(numeroLayout));
            NumeroStage.showAndWait();
            break;

            case "com.DTO.CartaJollySeme":

                Stage semeStage = new Stage();
                semeStage.initModality(Modality.APPLICATION_MODAL);
                semeStage.setTitle("Scegli il seme");

                ComboBox<String> semeComboBox = new ComboBox<>();
                semeComboBox.getItems().addAll("cucchiaio", "forchetta", "coltello");

                Button invia = new Button("Invia");
                invia.setOnAction(e -> {
                    String semeScelto = semeComboBox.getValue();
                    if (semeScelto != null) {
                        // Qui salvi il valore selezionato
                        // Ad esempio, puoi passarlo a un metodo per fare qualcosa con esso
                        // Esempio: metodoPerGestireSeme(semeScelto);
                        Random randNum = new Random();
                        int numeroCasuale = randNum.nextInt(7) + 1; 
                        Carta jollySeme = new Carta(semeScelto, numeroCasuale);
                        String imagePath = jollySeme.getPath();
                        Image image = new Image(getClass().getResourceAsStream(imagePath));
                        cartaDaGioco4.setImage(image);
                        utenteCorrente.mano.add(jollySeme);
                        mapImageView.put( cartaDaGioco4, imagePath);
                        utenteCorrente.mano.remove(c);
                        
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
                semeLayout.getChildren().addAll(semeComboBox, invia);
                semeLayout.setPrefSize(200, 100);
                semeLayout.setAlignment(Pos.CENTER);

                semeStage.setScene(new Scene(semeLayout));
                semeStage.showAndWait();
                break;

            case "com.DTO.CartaPerdiSpacca":
                System.out.println(
                        "Hai perso la carta Spacca: " + utenteCorrente.carteSpacca.remove(utenteCorrente.carteSpacca.size() - 1).lettera);
                break;

            /*case "com.DTO.CartaPescaDueCarte":
                pesca();
                scarta();
                pesca();
                scarta();
                break;*/

            case "com.DTO.CartaRubaSpacca":

                // pup-up che chiede a quale utente vuoi rubare la carta
                String utenteScelto = "ciccio";
                CartaRubaSpacca ruba = new CartaRubaSpacca();
                Optional<Utente> utenteOptional = partecipanti.stream().filter(p -> p.getNick().equals(utenteScelto)).findFirst();

                utenteOptional.ifPresent(utente -> {
                    for (CartaSpacca cartaSpacca : utente.carteSpacca) {
                        if (canGrab(cartaSpacca)) {
                            utenteCorrente.carteSpacca.add(cartaSpacca);
                            utente.carteSpacca.remove(cartaSpacca);
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
        ArrayList<CartaSpacca> carteUtente = utenteCorrente.carteSpacca;

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

    public void scarta(ImageView daScartare) {

        canPick = false;
        if (utenteCorrente.mano.size() > 3) {
            removeCardByImageView(daScartare);
            Image image = new Image(getClass().getResourceAsStream(mapImageView.get(cartaDaGioco4)));
            cartaDaGioco4.setImage(null);
            mapImageView.remove(cartaDaGioco4);
            daScartare.setImage(image);

            System.out.println(daScartare.getImage().toString());
            
            if(utenteCorrente.controllaScala()){
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Hai fatto scala");
                alert.setHeaderText(null);
                alert.setContentText("Hai vinto una carta spacca, al prossimo turno dovrai scartare una delle carte in mano");
                alert.showAndWait(); // Mostra il pop-up e attendi che venga chiuso
                
                utenteCorrente.carteSpacca.add(mSpacca.getRandomCard());
                aggiornaSpacca();

                //aggiorna carte spacca utente in scena
            }

            if(utenteCorrente.controllaTreStessoSeme()){
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Hai un tris di semi");
                alert.setHeaderText(null);
                alert.setContentText("Hai vinto una carta spacca, al prossimo turno dovrai scartare una delle carte in mano");
                alert.showAndWait(); // Mostra il pop-up e attendi che venga chiuso

                utenteCorrente.carteSpacca.add(mSpacca.getRandomCard());
                aggiornaSpacca();
                //aggiorna carte spacca utente in scena
            }
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
    public void passaBtnAction(ActionEvent event) {
        passa();
    }
    @FXML
    public void scartaEvent(MouseEvent event) {
        ImageView cartaToccata = (ImageView) event.getSource();
        scarta(cartaToccata);
    }

    public void removeCardByImageView(ImageView iv){
        String url = mapImageView.get(iv);
        for(Carta c : utenteCorrente.mano){
            if(c.getPath().equals(url)){
                utenteCorrente.mano.remove(c);
                break;
            }
        }
        mapImageView.remove(iv);
    }

    public void aggiornaSpacca(){

            ObservableList<CartaSpacca> carteSpacca = FXCollections.observableArrayList(utenteCorrente.carteSpacca);

    // Crea una cella personalizzata per visualizzare le immagini delle carte
    carteListView.setCellFactory(listView -> new ListCell<CartaSpacca>() {
        private final ImageView imageView = new ImageView();
        @Override
        protected void updateItem(CartaSpacca carta, boolean empty) {
            super.updateItem(carta, empty);
            if (empty || carta == null) {
                // Se la cella è vuota o la carta è nulla, imposto la grafica della cella a null
                setGraphic(null);
            } else {
                // Altrimenti, imposto l'immagine della carta nella ImageView
                String imagePath = carta.getPath();
                System.out.println(imagePath);
                Image image = new Image(getClass().getResourceAsStream(imagePath));
                imageView.setImage(image);
                setGraphic(imageView);
            }
        }
    });

    // Imposta la lista di carte dello spacca come elemento da visualizzare nel ListView
    carteListView.setItems(carteSpacca);

    }
}
