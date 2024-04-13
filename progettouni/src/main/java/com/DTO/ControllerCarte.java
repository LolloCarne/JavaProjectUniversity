package com.DTO;

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
import com.Enum.Seme;

public class ControllerCarte {
    @FXML
    private Button cuoriBtn;
    @FXML
    private Button fioriBtn;
    @FXML
    private Button piccheBtn;
    @FXML
    private TextField numeroField;

    

    @FXML
    void chooseCuoriAction(ActionEvent event) {
        CartaJollySeme c = new CartaJollySeme();
        Seme s = c.getSeme();
        s= s.CUORI;
    }

    @FXML
    void chooseFioriAction(ActionEvent event) {
        CartaJollySeme c = new CartaJollySeme();
        Seme s = c.getSeme();
        s= s.FIORI;
       
    }

    @FXML
    void choosePiccheAction(ActionEvent event) {
        CartaJollySeme c = new CartaJollySeme();
        Seme s = c.getSeme();
        s= s.PICCHE;
    }

    @FXML
    void chooseNumberAction(ActionEvent event) {
        CartaJollyNumero c = new CartaJollyNumero();
        int valore = Integer.parseInt(numeroField.getText());
        c.setValore(valore);
    }
}
