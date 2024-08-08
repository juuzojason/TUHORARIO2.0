package com.example.tuhorario2.Controllers.User;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class UserMenuController extends VBox implements Initializable {
    public Button GenerateButton;
    public Button EditButton;
    public Button LogOutBtn;
    public Button WatchButton;
    public Label UsernameLabel;
    public Label GroupNumberLabel;
    public Button ThemesButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
