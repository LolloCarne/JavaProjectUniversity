package com.example;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import com.DTO.CartaJollyNumero;
import com.DTO.CartaJollySeme;
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
        Seme s = c.getSeme().CUORI;
      
    }

    @FXML
    void chooseFioriAction(ActionEvent event) {
        CartaJollySeme c = new CartaJollySeme();
        Seme s = c.getSeme().FIORI;
        
       
    }

    @FXML
    void choosePiccheAction(ActionEvent event) {
        CartaJollySeme c = new CartaJollySeme();
        Seme s = c.getSeme().PICCHE;
       
    }

    @FXML
    void chooseNumberAction(ActionEvent event) {
        CartaJollyNumero c = new CartaJollyNumero();
        int valore = Integer.parseInt(numeroField.getText());
        c.setValore(valore);
    }
}
