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

import java.awt.event.MouseEvent;
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
        txtuser.setText("Dummy");
        txtpass.setText("DUMMYDUMMY");

        loginbtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                handleLogin();
            }
        });

        singup.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Model.getInstance().getViewFactory().RegisterView();
            }
        });
    }


    //TODO change the alert so instead of being a popup is a red label telling you what is wrong
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
            Model.getInstance().getViewFactory().UserView();
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