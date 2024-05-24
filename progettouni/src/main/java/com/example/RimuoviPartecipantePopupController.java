package com.example;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

//gestione pop up in caso di eliminazione utente nella scena di CreaPartita
public class RimuoviPartecipantePopupController {
    @FXML
    private TextField nicknameField;
    private String nickname;

    @FXML
    void conferma(ActionEvent event) {
        nickname = nicknameField.getText();
        closePopup(event);
    }

    @FXML
    void annulla(ActionEvent event) {
        closePopup(event);
    }

    private void closePopup(ActionEvent event) {
        ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
    }

    public String getNickname() {
        return nickname;
    }
}
