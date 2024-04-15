package com.DTO;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

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