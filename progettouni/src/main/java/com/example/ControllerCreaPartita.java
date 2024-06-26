package com.example;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.DTO.BotDummy;
import com.DTO.BotSmart;
import com.DTO.Partita;
import com.DTO.Utente;
import com.Manager.PartitaManager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

//CONTROLLER SOLO PER CREA PARTITA SCENE
public class ControllerCreaPartita implements Initializable{

    private Stage stage;
    private Scene scene;
    
    @FXML
    private TextField campoCodice;

    @FXML
    private TextField nickNameField;
    
    @FXML
    private Button IndietroBtn;

    @FXML
    private ListView<String> nickNameList;

    @FXML
    private Button aggiungiPartecipanteBtn;

    @FXML
    private Button rimuoviPartecipanteBtn;

    @FXML
    private Button creaPartitaDefi;

    String codice;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        Partita p = new Partita(); 
        campoCodice.setText(p.getCodice());
    }

    public String getCodice(){
        return this.codice;
    }

    @FXML
    void addUtenteAction(ActionEvent event) throws IOException{
        String codice= campoCodice.getText();
        PartitaManager manager = new PartitaManager();
        Partita p = new Partita(codice);
        String nickname=nickNameField.getText();

        if (!nickname.isEmpty()) {
            try{
                p.addPartecipante(new Utente(nickname));
            }catch(Error e){
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Errore");
                alert.setHeaderText(null);
                alert.setContentText("non puoi aggiungere piu di 4 partecipanti");
    
                alert.showAndWait();
            }

            updateNickNameList(manager.getNickNamesList(p));
            nickNameField.clear(); // Cancella il campo di testo dopo l'aggiunta
        }
        


    }

    //metodo per aggiumngere i bot in partita
    public void addBotAction(ActionEvent event){
        String codice= campoCodice.getText();
        Partita p = new Partita(codice);
        PartitaManager pManager = new PartitaManager();
        try{
            p.addPartecipante(new BotSmart());
        }catch(Error e){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Errore");
            alert.setHeaderText(null);
            alert.setContentText("non puoi aggiungere piu di 4 partecipanti");

            alert.showAndWait();
        }
        updateNickNameList(pManager.getNickNamesList(p));
    }

    //metodo per aggiungere i bot Stupid in partita
    public void addBotDummyAction(ActionEvent event){
        String codice= campoCodice.getText();
        Partita p = new Partita(codice);
        PartitaManager pManager = new PartitaManager();
        try{
            p.addPartecipante(new BotDummy());
        }catch(Error e){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Errore");
            alert.setHeaderText(null);
            alert.setContentText("non puoi aggiungere piu di 4 partecipanti");
            alert.showAndWait();
        }
        updateNickNameList(pManager.getNickNamesList(p));
    }

    public void updateNickNameList(ArrayList<String> nicks){
        nickNameList.getItems().clear();
        nickNameList.getItems().addAll(nicks);
    }

    @FXML
    void rimuoviPartecipanteAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("rimuoviPartecipantePopup.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();

        String codice= campoCodice.getText();
        Partita p = new Partita(codice);
        // Una volta chiusa la finestra di pop-up, gestisci la rimozione del partecipante
        RimuoviPartecipantePopupController popupController = loader.getController();
        String nicknameToRemove = popupController.getNickname();
        System.out.println(nicknameToRemove);
        if (nicknameToRemove != null) {
            PartitaManager manager = new PartitaManager();
            manager.removeUtenteByNick(p, nicknameToRemove);
            updateNickNameList(manager.getNickNamesList(p));
    }
}

@FXML
    void creaPartitaDefiAction(ActionEvent event) throws IOException{
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Creazione Partita");
        alert.setHeaderText(null);
        alert.setContentText("La creazione della partita è avvenuta con successo!");
        alert.getButtonTypes().setAll(ButtonType.OK);
        alert.showAndWait().ifPresent(response -> {
            
            if (response == ButtonType.OK) {
                try {
                   goBack(event);
                } catch(IOException e) {
                    e.printStackTrace();

                    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                    errorAlert.setTitle("Errore");
                    errorAlert.setHeaderText(null);
                    errorAlert.setContentText("Si è verificato un errore durante il ritorno alla schermata home.");
                    errorAlert.showAndWait();
                }
            }
        });
    }


    @FXML
    void goBack(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("homeScene.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    
}
