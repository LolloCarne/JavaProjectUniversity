package com.example;
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
