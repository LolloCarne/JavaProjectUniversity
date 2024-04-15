package com.example;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.DTO.Partita;
import com.DTO.Utente;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javafx.stage.Stage;
import javafx.scene.Node;

import com.Manager.AdminManager;
import com.Manager.PartitaManager;

public class Controller {

    private Stage stage;
    private Scene scene;


    @FXML
    private Button GiocaBotBtn;

    @FXML
    private Button uniscitiBtn;

    @FXML
    private TextField tfCodice;

    @FXML
    private TextField tfNickname;

    @FXML
    private Button amministratorBtn;

    @FXML
    private Button creaPartitaBtn;


    @FXML
    private Button eliminaUtenteAdmin;

    @FXML
    private Button aggUtenteAdmin;

    @FXML
    private Button creaTorneoAdmin;

    @FXML
    private Button eliminaPartitaAdmin;

    @FXML
    private Button eliminaTorneoAdmin;

    @FXML
    private TextField campoCodice;

    @FXML
    private TextField nickNameField;

    @FXML
    private Button IndietroBtn;


    @FXML
    private Button IndietroBtn2;

    @FXML
    private TextField codiceRegole;

    @FXML
    private Button giocaPartita;

    @FXML
    private ListView<String> listPartecipanti;

    @FXML
    private Button ottieniPartcipanti;

    @FXML
    private Button AggiungiUtente;

    @FXML
    private TextField tfAggiungiUtente;

    @FXML
    private TextField codiceElimina;

    @FXML
    private Button eliminaBtn;

    @FXML
    private Label messagioEliminazione;

     @FXML
    private Button logAdmin;

    @FXML
    private PasswordField pwdAdmin;

    @FXML
    private Button registroAdmin;

    @FXML
    private TextField usernameAdmin;

    @FXML
    private PasswordField pwdAdminReg;

    @FXML
    private Button registrazioneAdmin;

    @FXML
    private TextField usernameAdminReg;

    @FXML
    private Label messaggioLoginErrato;
    

    PartitaManager manager = new PartitaManager();
   

    @FXML
    void goBack(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("homeScene.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void onBtnClick1(ActionEvent event) throws IOException{
        
        Parent root = FXMLLoader.load(getClass().getResource("utenteScene.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void onBtnClick2(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("loginAdminScene.fxml")); //nome scena successiva
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
   
     
     @FXML
    void goToGiocaBot(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("schermataUtente.fxml")); //nome scena successiva
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
     @FXML
    void goToUnisciti(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("uniscitiScene.fxml")); //nome scena successiva
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    
    }

    @FXML
    void onBtnLogin(ActionEvent event) throws IOException {
        String codice = tfCodice.getText();
        String nickname = tfNickname.getText();
        
        Parent root = FXMLLoader.load(getClass().getResource("regoleScene.fxml")); //nome scena successiva
       
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void creaPartitaAction(ActionEvent event) throws IOException{
        //Partita p = new Partita(); 
        Parent root = FXMLLoader.load(getClass().getResource("creazionePartitaScene.fxml")); //nome scena successiva
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

       // campoCodice.setText(p.getCode());

    }

    @FXML
    void addUtenteAction(ActionEvent event) throws IOException{
        /*String codice= campoCodice.getText();
        Partita p = new Partita(codice);

        p.addPartecipante(new Utente(nickNameField.getText()));*/

        Parent root = FXMLLoader.load(getClass().getResource("aggiungiUtente.fxml")); //nome scena successiva
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void creaTorneoAction(ActionEvent event) {

    }

    @FXML
    void eliminaPartitaAction(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("eliminaPartita.fxml")); //nome scena successiva
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void eliminaTorneoAction(ActionEvent event) throws IOException{
        
    }


    @FXML
    void iniziaPartita(ActionEvent event) throws IOException {
        
    
    }

    /*metodo per far si che possa stampare i partecipanti */
    @FXML
    void stampaPartecipanti(ActionEvent event) throws IOException {
        
        String codice = codiceRegole.getText();
        Partita p = new Partita(codice);
        PartitaManager manager = new PartitaManager();
        ArrayList <Utente> partecipanti = manager.getPartecipantiByCode(codice);
        for (Utente u : partecipanti){
            listPartecipanti.getItems().addAll(u.getNick());
        }
        
    }







    @FXML
    void eliminaPartitaDefi(ActionEvent event) throws IOException {
    // Crea una finestra di dialogo di conferma
        Alert confermaEliminazione = new Alert(AlertType.CONFIRMATION);
        confermaEliminazione.setTitle("Conferma Eliminazione");
        confermaEliminazione.setHeaderText(null);
        confermaEliminazione.setContentText("Sei sicuro di voler eliminare la partita?");

        // Aggiungi pulsanti per confermare o annullare l'eliminazione
        confermaEliminazione.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);

        // Mostra la finestra di dialogo e gestisci la risposta dell'utente
        confermaEliminazione.showAndWait().ifPresent(response -> {
            if (response == ButtonType.YES) {
                // Se l'utente conferma, esegui l'eliminazione
                eliminaPartita();
            }
        });
        }

        private void eliminaPartita() {
            // Aggiungi qui il codice per eliminare la partita
            String codice = codiceElimina.getText();
            System.out.println(codice);
            manager.deletePartitaByCode(codice);
            messagioEliminazione.setText("Partita eliminata con successo!");
        }

        @FXML
        void eliminaUtenteAction(ActionEvent event) throws IOException {
            Parent root = FXMLLoader.load(getClass().getResource("eliminaUtenteScene.fxml")); //nome scena successiva
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        

        @FXML
        void logAdminAction(ActionEvent event) throws IOException{
            String username = usernameAdmin.getText();
            String password = pwdAdmin.getText();
            AdminManager manager = new AdminManager();

            int i = 0;
            boolean b = manager.login(username, password);
            if (b==true){
                Parent root = FXMLLoader.load(getClass().getResource("menuAdminScene.fxml")); //nome scena successiva
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
            else{
                messaggioLoginErrato.setText("Username o password errati. Riprova.");

                usernameAdmin.clear();
                pwdAdmin.clear();
                
            }
        }
        
    
        @FXML
        void goToRegistrati(ActionEvent event) throws IOException {
            Parent root = FXMLLoader.load(getClass().getResource("registrazioneAdmin.fxml")); //nome scena successiva
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    
        @FXML
        void registraAdmin(ActionEvent event) throws IOException{
            String username = usernameAdminReg.getText();
            String pwd = pwdAdminReg.getText();

            AdminManager manager = new AdminManager();
            manager.registraAdmin(username, pwd);
            
            Parent root = FXMLLoader.load(getClass().getResource("menuAdminScene.fxml")); //nome scena successiva
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } 
    }






