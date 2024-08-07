package com.example.tuhorario2.Controllers;

import com.example.tuhorario2.Models.DBDriver;
import com.example.tuhorario2.Models.Model;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.application.Platform;

import java.net.URL;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {

    public BorderPane viewBrPn;
    public TextField txtusername;
    public TextField txtpass;
    public TextField txtpass2;
    public Button registerbtn;
    public Hyperlink gobacklink;


    //TODO change the alert so instead of being a popup is a red label telling you what is wrong
    private void handleRegister() {
        String username = txtusername.getText();
        String password = txtpass.getText();
        String confirmPassword = txtpass2.getText();

        if (username.isBlank() || password.isBlank() || confirmPassword.isBlank()) {
            showAlert(Alert.AlertType.ERROR, "Mistake", "All fields must be completed.");
            return;
        }

        if (!password.equals(confirmPassword)) {
            showAlert(Alert.AlertType.ERROR, "Mistake", "Passwords do not match.");
            return;
        }

        if (!Model.getInstance().Register(username,password)){
            showAlert(Alert.AlertType.ERROR, "Error", "There is already a user with username: " + username);
            return;
        }

    }

   /*
    private void handleGoBack() {
        try {
            Stage stage = (Stage) gobacklink.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/path/to/Login.fxml")); //
            Parent root = loader.load();
            stage.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "No se pudo cargar la pantalla de inicio de sesión.");
        }
    }
    */

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        registerbtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                handleRegister();
            }
        });

        gobacklink.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Model.getInstance().getViewFactory().LoginView();
            }
        });
    }
}
