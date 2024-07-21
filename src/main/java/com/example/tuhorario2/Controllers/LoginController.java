package com.example.tuhorario2.Controllers;

import com.example.tuhorario2.Models.DBDriver;
import com.example.tuhorario2.Models.Model;
import com.example.tuhorario2.Models.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {


    public TextField txtuser;
    public PasswordField txtpass;
    public Button loginbtn;
    public Hyperlink singup;

    public BorderPane viewBrPn;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loginbtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                handleLogin();
            }
        });
    }

    private void handleLogin() {
        String username = txtuser.getText();
        String password = txtpass.getText();

        if (username.isEmpty() || password.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Mistake", "All fields must be completed.");
            return;
        }

        User user = Model.getInstance().loginAsUser(username, password);

        if (user != null) {
            System.out.println("Login successful.   User ID:" + user.getId());
        } else {
            showAlert(Alert.AlertType.ERROR, "Mistake", "Incorrect username or password.");
        }
    }



    private void handleSignUp() {
        Model.getInstance().Register();
    }



    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


}