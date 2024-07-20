package com.example.tuhorario2.Controllers;

import com.example.tuhorario2.Models.DBDriver;
import com.example.tuhorario2.Models.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField txtuser;
    @FXML
    private PasswordField txtpass;
    @FXML
    private Button loginbtn;
    @FXML
    private Hyperlink singup;

    private DBDriver dbDriver;

    public void initialize() {
        dbDriver = new DBDriver();
    }

    @FXML
    private void handleLogin() {
        String username = txtuser.getText();
        String password = txtpass.getText();

        if (username.isEmpty() || password.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Mistake", "All fields must be completed.");
            return;
        }

        User user = dbDriver.loginAsUser(username, password);

        if (user != null) {
            System.out.println("Login successful.   User ID:" + user.getId());
        } else {
            showAlert(Alert.AlertType.ERROR, "Mistake", "Incorrect username or password.");
        }
    }


    /*@FXML
    private void handleSignUp() {
        try {
            Stage stage = (Stage) singup.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/path/to/Registro.fxml"));
            Parent root = loader.load();
            stage.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "No se pudo cargar la pantalla de registro.");
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