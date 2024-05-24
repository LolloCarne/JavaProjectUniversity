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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
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
import java.util.EmptyStackException;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.image.Image;

import com.DTO.BotDummy;
import com.DTO.BotSmart;
//import com.DTO.BotSmart;
import com.DTO.Carta;
import com.DTO.CartaRubaSpacca;
import com.DTO.CartaSpacca;
import com.DTO.Partita;
import com.DTO.Utente;
import com.Enum.Seme;
import com.Manager.TorneoManager;
import com.DTO.Torneo;

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
    private ImageView imgVs;

    @FXML
    private ImageView imgVp;

    @FXML
    private ImageView imgVa1;

    @FXML
    private ImageView imgVa2;

    @FXML
    private ImageView imgVc1;

    @FXML
    private ImageView imgVc2;

    @FXML
    private TextField nomeGiocatorePartita;

    private boolean canPick;

    private Map<ImageView, String> mapImageView;

    ArrayList <ImageView> imgViewList;

    @FXML
    private Button IndietroBtn;

    @FXML
    private TextField vincitoreSpaccaField;

    @FXML
    private Button spaccaBtn;

    Torneo t; 
    int partiteDaGiocare; 

    @FXML
    private Button saveAndExitBtn;
    
    @FXML
    private Label messaggioVincitore;

    @FXML
    private Label codiceNuovaPartitaTorneo;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        
    }

    public void start(String codice) {
        int partiteGiocate=0;

        t = new Torneo();
        p = new Partita(codice);
        System.out.println("Prova: "+p.getPartecipanti().get(0).codiceTorneo);
        m = new Mazzo();
        mapImageView= new HashMap<>();
        partecipanti = p.getPartecipanti();
        mSpacca = new MazzoSpacca(partecipanti.size());

        codiceNuovaPartitaTorneo.setVisible(false);

        System.out.println(p.toString());
        // diamo le carte a tutti i partecipant
        
        for (Utente u : partecipanti) {
            if(u.mano.isEmpty()){
                for (int i = 0; i < 3; i++) {
                    u.mano.add(m.getCartaDiGioco());
                }
            }

        }
        for(Utente x : partecipanti){
            partiteGiocate+=x.getPartiteVinte();
        }
        partiteDaGiocare=3-partiteGiocate;
        utenteCorrente = partecipanti.get(0);
        imgViewList = new ArrayList<>();

        imgViewList.add(imgVs);
        imgViewList.add(imgVp);
        imgViewList.add(imgVa1);
        imgViewList.add(imgVc1);
        imgViewList.add(imgVc2);
        imgViewList.add(imgVa2);

        setScene(utenteCorrente);
        //setGiocatoreInPartita(u.getNick());
    }


 public void giocaBot(Utente u){

        setScene(u);
        BotSmart bot= new BotSmart(u);



        // Impostazione della scena per il bot
        System.out.println("entrato bot");  

//decido quale carta scartare
ImageView daScartare=null;
        if(u.getNick().endsWith("@DUMMYBOT")){
            //logica scelta carta da scartare bot smart
            int randomNumber = new Random().nextInt(3);
            switch (randomNumber) {
                case 0:
                    daScartare = cartaDaGioco1;
                    break;
                case 1:
                    daScartare = cartaDaGioco2;
                    break;
                case 2:
                    daScartare = cartaDaGioco3;
                    break;
                // Continua con le altre ImageView
                default:
                daScartare = cartaDaGioco1;
                    break;
            }
        }else{
            //logica scelta carta da scartare bot smart
            Carta c = bot.qualeScartare();
            if(c==null){
                int randomNumber = new Random().nextInt(3);
                switch (randomNumber) {
                    case 0:
                        daScartare = cartaDaGioco1;
                        break;
                    case 1:
                        daScartare = cartaDaGioco2;
                        break;
                    case 2:
                        daScartare = cartaDaGioco3;
                        break;
                    // Continua con le altre ImageView
                    default:
                    daScartare = cartaDaGioco1;
                        break;
                }
            }else{
                for (Map.Entry<ImageView, String> entry : mapImageView.entrySet()) {
                    if (entry.getValue().equals(c.getPath())) {
                        daScartare = entry.getKey();
                    }
                }
            }

        }


        pesca();
        System.out.println(u.mano.size());
        Alert alertPescato = new Alert(AlertType.INFORMATION);
        alertPescato.setTitle("Il bot ha pescato");
        alertPescato.setHeaderText(null);
        try {
            alertPescato.setContentText("Carta pescata: "+u.mano.get(u.mano.size()-1));
        } catch (java.lang.IndexOutOfBoundsException e) {
            System.out.println("il bot ha pescato");
        }
        
        alertPescato.showAndWait(); // Mostra il pop-up e attendi che venga chiuso


        //scartare        
       /*  removeCardByImageView(daScartare);
        System.out.println("Percorso da settare: "+mapImageView.get(cartaDaGioco4));
        Image image=null;
        if(mapImageView.get(cartaDaGioco4)==null){
            image = new Image(getClass().getResourceAsStream(u.mano.get(u.mano.size()-1).getPath()));
        }else{
            image = new Image(getClass().getResourceAsStream(mapImageView.get(cartaDaGioco4)));
        }
       
        
        cartaDaGioco4.setImage(null);
        mapImageView.remove(cartaDaGioco4); // Rimuove l'associazione precedente
        daScartare.setImage(image);
        mapImageView.put(daScartare, imagePath); // Aggiunge la nuova associazione*/

        removeCardByImageView(daScartare);
            
        String imagePath = mapImageView.get(cartaDaGioco4);
        if (imagePath != null) {
            Image image = new Image(getClass().getResourceAsStream(imagePath));
            cartaDaGioco4.setImage(null);
            mapImageView.remove(cartaDaGioco4); // Rimuove l'associazione precedente
            daScartare.setImage(image);
            mapImageView.put(daScartare, imagePath); // Aggiunge la nuova associazione

            System.out.println(daScartare.getImage().toString());

        } else {
            System.out.println("Errore: immagine non trovata in mapImageView per cartaDaGioco4.");

        }

        System.out.println(daScartare.getImage().toString());

        System.out.println(u.mano.size());

        //wait x
        Alert alert = new Alert(AlertType.INFORMATION);

        switch (bot.gioca()) {
            case "Stesso Seme":
            
            alert.setTitle("il Bot "+u.getNick() + " Ha un tris di semi");
            alert.setHeaderText(null);
            alert.setContentText("Ha vinto una carta spacca, ora scarterà una carta a sua scelta");
            alert.showAndWait(); // Mostra il pop-up e attendi che venga chiuso

            CartaSpacca cs = mSpacca.getRightCard(utenteCorrente);
            utenteCorrente.carteSpacca.add(cs);
            aggiornaSpacca();
            //controllo che il giocatore non abbia vinto e se fa parte di un torneo
                
                break;

            case "Scala":

            alert.setTitle("il Bot "+u.getNick() + " fatto scala");
            alert.setHeaderText(null);
            alert.setContentText("Ha vinto una carta spacca, ora scarterà una carta a sua scelta");
            alert.showAndWait(); // Mostra il pop-up e attendi che venga chiuso
            
            CartaSpacca cs2 = mSpacca.getRightCard(utenteCorrente);
            utenteCorrente.carteSpacca.add(cs2);
            aggiornaSpacca();
            //controllo che il giocatore non abbia vinto e se fa parte di un torneo

            //aggiorna carte spacca utente in scena
                break;

            default:
                break;
        }
        passa();


    }
 

  public void setScene(Utente u) {
        canPick = true;
        pulisciSpacca();
        aggiornaSpacca();
        cartaDaGioco4.setImage(null);
        
        System.out.println(u.getNick());
        if (nomeUtente != null) {
            nomeUtente.setText(u.getNick());
        } 

        for(Carta c : u.mano){
            System.out.println(c.getPath());
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
        nomeGiocatorePartita.setText(utenteCorrente.getNick());
}


public void pesca() {
    System.out.println("Carte in mano inizio pesca: "+utenteCorrente.mano.size());
    try {
        if (utenteCorrente.mano.size() == 3 && canPick) {
            canPick = false;
            Carta pescata = utenteCorrente.getNick().endsWith("BOT") ? this.m.getCartaDiGioco() : this.m.mazzo.pop();
            if(pescata==null){
                mostraErrore("Il mazzo è finito, vincerà chi ha piu carte spacca!");
                Utente vincitore = partecipanti.get(0);
        
               for(Utente x : partecipanti){
                if(x.carteSpacca.size()>=vincitore.carteSpacca.size()){
                    vincitore=x;
                }
               }
               vittoria(vincitore);
            }else{
                String imagePath = pescata.getPath();
    
                Image image = new Image(getClass().getResourceAsStream(imagePath));
                cartaDaGioco4.setImage(image);
                utenteCorrente.mano.add(pescata);
                mapImageView.put(cartaDaGioco4, imagePath); // Associa il nuovo percorso dell'immagine a cartaDaGioco4
    
                if (pescata.getClass().getName().equals("com.DTO.Carta")) {
                    System.out.println("Seleziona una carta da scartare");
                } else {
                    actionCarta(pescata);
                }
            }

        } else {
            mostraErrore("Non puoi passare il turno se hai meno di 3 carte, prima devi pescare.");
        }
    } catch (EmptyStackException e) {
        mostraErrore("Il mazzo è finito, non puoi pescare.");
        Utente vincitore = partecipanti.get(0);

       for(Utente x : partecipanti){
        if(x.carteSpacca.size()>=vincitore.carteSpacca.size()){
            vincitore=x;
        }
       }
       vittoria(vincitore);
    }

    System.out.println("Carte in mano fine pesca: "+utenteCorrente.mano.size());
}

    public void actionCarta(Carta c) {

        switch (c.getClass().getName()) {

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
                int numeroScelto = numberCombo.getValue()-1;
                if (numeroScelto != -1) {
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
                    alert.setContentText("Seleziona un numero prima di inviare.");
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


            case "com.DTO.CartaRubaSpacca":
                c.setValore(9);
                Button inviaRub = new Button("Invia");
                ChoiceBox<String> choiceBox = new ChoiceBox<>(FXCollections.observableArrayList());
                for (Utente utente : partecipanti) {
                    if (!utente.getNick().equals(utenteCorrente.getNick())) {
                        choiceBox.getItems().add(utente.getNick());
                    }
                }
                VBox root = new VBox(10, choiceBox,inviaRub);
                root.setAlignment(Pos.CENTER);

                // Creiamo una scena e mostriamo il popup
                Stage rubStage = new Stage();
                Scene scene = new Scene(root, 300, 200);
                rubStage.setScene(scene);
                rubStage.setTitle("Seleziona utente da rubare");
                rubStage.show();

                
                inviaRub.setOnAction(event -> {
                String  u = choiceBox.getValue(); // Salviamo il nickname selezionato nella variabile utenteScelto
                if (u != null) {
                        // Qui puoi eseguire altre azioni in base al nickname selezionato, se necessario
                        System.out.println("Utente selezionato: " + u);

                        for (Utente utente : partecipanti) {
                            if (utente.getNick().equals(u)) {
                                if(utente.carteSpacca.size()==0){
                                    Alert alertWarn = new Alert(AlertType.WARNING);
                                    alertWarn.setTitle("Warn");
                                    alertWarn.setHeaderText(null);
                                    alertWarn.setContentText("Il giocatore selezionato non ha carte spacca");
        
                                    alertWarn.showAndWait(); // Mostra il pop-up e attendi che venga chiuso
                                    rubStage.close();
                                    break;
                                }else{
                                    rubSpacca(utente);
                                    aggiornaSpacca();
                                    rubStage.close();
                                    }
                            }
                        }
                    }
                });

                break;

            default:
                break;
        }
    }

    public boolean canGrab(Utente u) {
        String[] lettere = { "S", "P", "A", "C", "C", "A" };
        ArrayList<CartaSpacca> carteUtente = utenteCorrente.carteSpacca;

        if (carteUtente.size() >= lettere.length) {
            return false; // Non posso aggiungere altre carte
        }

        if(carteUtente.size()<u.carteSpacca.size()){
            return true;
        }

        return false;
        
    }

    public void passa() {
        System.out.println("Carte in mano passa: "+utenteCorrente.mano.size());
        if (utenteCorrente.mano.size() == 3) {
            int currentIndex = partecipanti.indexOf(utenteCorrente);
            if (currentIndex == partecipanti.size() - 1) {
                utenteCorrente = partecipanti.get(0);
            } else {
                utenteCorrente = partecipanti.get(currentIndex + 1);
            }
            if(utenteCorrente.getNick().endsWith("BOT")){
                giocaBot(utenteCorrente);
            }else{
                setScene(utenteCorrente);
            }
            
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Errore");
            alert.setHeaderText(null);
            alert.setContentText("Non puoi passare il turno se non hai 3 carte.");

            alert.showAndWait(); // Mostra il pop-up e attendi che venga chiuso
        }

    }

    public void scarta(ImageView daScartare) {
        System.out.println("Carte in mano inzio scarta: "+utenteCorrente.mano.size());

        if (utenteCorrente.mano.size() > 3) {
           
            removeCardByImageView(daScartare);
            
            String imagePath = mapImageView.get(cartaDaGioco4);
            if (imagePath != null) {
                Image image = new Image(getClass().getResourceAsStream(imagePath));
                cartaDaGioco4.setImage(null);
                mapImageView.remove(cartaDaGioco4); // Rimuove l'associazione precedente
                daScartare.setImage(image);
                mapImageView.put(daScartare, imagePath); // Aggiunge la nuova associazione
    
                System.out.println(daScartare.getImage().toString());
    
                // Controlla scala e tris di semi
                controllaVittorie();
            } else {
                System.out.println("Errore: immagine non trovata in mapImageView per cartaDaGioco4.");

            }
        } else {
            System.out.println("Non puoi scartare");
            mostraErrore("Non puoi scartare");
        }
        System.out.println("Carte in mano fine scarta: "+utenteCorrente.mano.size());

    }
    
    private void controllaVittorie() {
        if (utenteCorrente.controllaScala()) {
            mostraInformazione("Hai fatto scala", "Hai vinto una carta spacca!");
            aggiungiCartaSpacca();
        }
    
        if (utenteCorrente.controllaTreStessoSeme()) {
            mostraInformazione("Hai un tris di semi", "Hai vinto una carta spacca!");
            aggiungiCartaSpacca();
        }
    }
    
    private void mostraErrore(String messaggio) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Errore");
        alert.setHeaderText(null);
        alert.setContentText(messaggio);
        alert.showAndWait(); // Mostra il pop-up e attendi che venga chiuso
    }
    
    private void mostraInformazione(String titolo, String messaggio) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(titolo);
        alert.setHeaderText(null);
        alert.setContentText(messaggio);
        alert.showAndWait(); // Mostra il pop-up e attendi che venga chiuso
    }
    
    private void aggiungiCartaSpacca() {
        CartaSpacca cs = mSpacca.getRightCard(utenteCorrente);
        utenteCorrente.carteSpacca.add(cs);
        aggiornaSpacca();
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

    public void removeCardByImageView(ImageView iv) {
        String url = mapImageView.get(iv);
        if (url != null) {
            System.out.println("url da rimuovere: " + url);
            utenteCorrente.mano.removeIf(c -> c.getPath().equals(url));
            mapImageView.remove(iv);
        } else {
            System.out.println("Errore: immagine non trovata in mapImageView per ImageView.");
            // Potresti lanciare un'eccezione o gestire l'errore in altro modo
        }
    }

    public void rubSpacca(Utente u){


        if(canGrab(u)){
            utenteCorrente.carteSpacca.add(u.carteSpacca.get(utenteCorrente.carteSpacca.size()));
        }
        else{
            Alert alertSpacca = new Alert(AlertType.ERROR);
            alertSpacca.setTitle("Non puoi rubare carte spacca a questo giocatore");
            alertSpacca.setHeaderText(null);
            alertSpacca.setContentText("Il giocatore non possiede carte SPACCA oppure ha le tue stesse carte SPACCA");

            alertSpacca.showAndWait(); // Mostra il pop-up e attendi che venga chiuso
        }

    }

    public void aggiornaSpacca(){
        
        String imgPath;
        Image image;
        for(CartaSpacca c : utenteCorrente.carteSpacca){

            imgPath = c.getPath();
            image = new Image(getClass().getResourceAsStream(imgPath));
            imgViewList.get(utenteCorrente.carteSpacca.indexOf(c)).setImage(image);

            
            if(utenteCorrente.carteSpacca.indexOf(c)==5){
                
                vittoria(utenteCorrente);
                
            }
        }
    }

    @FXML
    void goBack(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("homeScene.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    
    public void vittoria(Utente vincitore){
                 //comandi che servono per la schermata finale del vincitore di Spacca
                 
                 cartaDaGioco1.setVisible(false);
                 cartaDaGioco2.setVisible(false);
                 cartaDaGioco3.setVisible(false);
                 cartaDaGioco4.setVisible(false);
                 saveAndExitBtn.setVisible(false);
                 IndietroBtn.setVisible(true);
                 messaggioVincitore.setVisible(true);
                 messaggioVincitore.setText("Il vincitore di SPACCA è " + vincitore.getNick() + "!");

                 vincitore.setPartiteVinte(vincitore.getPartiteVinte()+1);
                 partiteDaGiocare = partiteDaGiocare-1;
                 
                 System.out.println("da giocare" + partiteDaGiocare);
                 svuotaUtenti();
                 salva();
                 try {
                     System.out.println("Codice torneo " + vincitore.getCodiceTorneo() );
                     String codice =vincitore.getCodiceTorneo();
                     if(!codice.equals("null")){
                         
                        t.setPartiteDaGiocare(partiteDaGiocare);
                        t.vincitoreTorneo(partiteDaGiocare, utenteCorrente.getNick());
                        System.out.println("Vincitore"+  vincitore.getNick());
                         
                        //v.getUtente(utenteCorrente);
 
                        if(partiteDaGiocare != 0 ){ //se ci sono ancora partite da giocare del torneo
                            Partita p = new Partita(partecipanti);
                            
                            codiceNuovaPartitaTorneo.setVisible(true);
                            codiceNuovaPartitaTorneo.setText("Il codice della prossima partita del torneo è:  " + p.getCodice());
                            System.out.println("Codice nuova partita: "+p.getCodice());
                             
                        }
                     }
 
                     //settare flag vittoria
                 }
                     
                 catch (Exception e) {
                     // TODO: handle exception
                 }
    }

    public void salva(){
        p.save();
        
    }

    public void svuotaUtenti(){
        for(Utente u : partecipanti){
            u.mano=new ArrayList<>();
            u.carteSpacca = new ArrayList<>();
        }
    }

    public void pulisciSpacca(){
        for(ImageView i : imgViewList){
            if(i.getImage()!=null)
                i.setImage(null);
        }
    }
    

    @FXML
    void saveAndExitBtnAction(ActionEvent event) {
        salva();
    try {
        goBack(event);
    } catch (Exception e) {
        System.out.println("Errore: "+e);
    }
        
    }

    
}
