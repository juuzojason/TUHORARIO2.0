package com.example.tuhorario2.Controllers;

import com.example.tuhorario2.Models.DBDriver;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.application.Platform;

public class RegisterController {

    @FXML
    private TextField txtusername;
    @FXML
    private TextField txtpass;
    @FXML
    private TextField txtpass2;
    @FXML
    private Button registerbtn;
    @FXML
    private Hyperlink gobacklink;


    private DBDriver dbDriver;

    public void initialize() {
        dbDriver = new DBDriver();
    }

    @FXML
    private void handleRegister() {
        String username = txtusername.getText();
        String password = txtpass.getText();
        String confirmPassword = txtpass2.getText();

        if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Mistake", "All fields must be completed.");
            return;
        }

        if (!password.equals(confirmPassword)) {
            showAlert(Alert.AlertType.ERROR, "Mistake", "Passwords do not match.");
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
}
