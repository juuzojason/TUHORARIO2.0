package com.example.tuhorario2.Controllers.User;

import com.example.tuhorario2.Controllers.GeneralTitleController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserController extends BorderPane implements Initializable {


    public UserEditingController userEditing;
    public VBox shGenerator;
    public VBox shView;
    public VBox filterEditor;
    public UserMenuController userMenu;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Fxml/GeneralTitleBar.fxml"));
            AnchorPane tb = fxmlLoader.load();
            GeneralTitleController c = fxmlLoader.getController();
            setTop(tb);


            FXMLLoader fxmlLoader2 = new FXMLLoader(getClass().getResource("/Fxml/User/UserEditing.fxml"));
            VBox userEd = fxmlLoader2.load();
            userEditing = fxmlLoader2.getController();

            FXMLLoader fxmlLoader3 = new FXMLLoader(getClass().getResource("/Fxml/User/UserEditing.fxml"));
            VBox userMenu = fxmlLoader3.load();
            userMenu = fxmlLoader3.getController();
            setLeft(userMenu);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
