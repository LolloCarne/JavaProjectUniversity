package com.example;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

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
import javafx.scene.control.Button;
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


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        //roba su scena creazionePartita
        Partita p = new Partita(); 
        campoCodice.setText(p.getCodice());
    }

        @FXML
    void addUtenteAction(ActionEvent event) throws IOException{
        String codice= campoCodice.getText();
        PartitaManager manager = new PartitaManager();
        Partita p = new Partita(codice);
        String nickname=nickNameField.getText();

        if (!nickname.isEmpty()) {
            p.addPartecipante(new Utente(nickname));
            updateNickNameList(manager.getNickNamesList(p));
            nickNameField.clear(); // Cancella il campo di testo dopo l'aggiunta
        }
        


    }

    public void updateNickNameList(ArrayList<String> nicks){
        nickNameList.getItems().clear();
        nickNameList.getItems().addAll(nicks);
    }

    @FXML
void rimuoviPartecipanteAction(ActionEvent event) throws IOException {
    // Apri la finestra di pop-up
    
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
    void goBack(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("homeScene.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    
}
