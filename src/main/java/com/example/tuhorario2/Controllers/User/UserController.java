package com.example.tuhorario2.Controllers.User;

import com.example.tuhorario2.Controllers.GeneralTitleController;
import com.example.tuhorario2.Models.Group;
import com.example.tuhorario2.Models.Model;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class UserController extends BorderPane implements Initializable {

    @FXML
    public VBox userEditing;
    public UserEditingController ueControl;

    @FXML
    public VBox shGenerator;
    //TODO Schedule Generator Controller

    @FXML
    public VBox shView;
    //TODO Schedule View Controller

    @FXML
    public VBox filterEditor;
    //TODO filter Editor Controller

    @FXML
    public VBox userMenu;
    public UserMenuController umControl;

    public BorderPane viewBrPn;


    public ArrayList<Group> groupList;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setUsermenu();
        setUserEditing();
    }

    public void setUsermenu(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Fxml/User/UserMenu.fxml"));
            userMenu = fxmlLoader.load();
            this.umControl = fxmlLoader.getController();
            viewBrPn.setLeft(userMenu);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setUserEditing(){
        if (this.userEditing == null){
            userEditing = Model.getInstance().getViewFactory().getUserEditing();
            ueControl = Model.getInstance().getViewFactory().getUeControl();
        }
        viewBrPn.setCenter(userEditing);
    }

    public void deleteGroup(Group g) {
        this.groupList.remove(g);
    }
}
