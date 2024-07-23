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



    @FXML
    public Label errorLabel;
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



    //DONE
    private void handleLogin() {
        String username = txtuser.getText();
        String password = txtpass.getText();

        // Clear the previous error message
        errorLabel.setText("");

        if (username.isEmpty() || password.isEmpty()) {
            errorLabel.setText("All fields must be completed.");
            return;
        }

        User user = Model.getInstance().loginAsUser(username, password);

        if (user != null) {
            System.out.println("Login successful. User ID:" + user.getId());
            Model.getInstance().getViewFactory().UserView();
        } else {
            errorLabel.setText("Incorrect username or password.");
        }
    }



    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


}