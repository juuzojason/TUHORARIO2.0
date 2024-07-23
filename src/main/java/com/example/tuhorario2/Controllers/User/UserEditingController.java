package com.example.tuhorario2.Controllers.User;

import com.example.tuhorario2.Models.Group;
import com.example.tuhorario2.Models.Model;
import com.example.tuhorario2.Views.CharlyDialog;
import com.example.tuhorario2.Views.CharlyDialogs;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class UserEditingController extends VBox implements Initializable {

    public FlowPane CardsContainer;
    public Button searchButton;
    public TextField searchTxtField;
    public Label PathLabel;
    public Button BackButton;
    public Button CreateButton;
    public Label CardsLabel;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        UpdateInfo();

        CreateButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                createGroup();
            }
        });

        generateCards();
    }

    public void generateCards(){
        for (Group g : Model.getInstance().getGeneralGroupList()){
            g.createCard();
            CardsContainer.getChildren().add(g.getCard());
            System.out.println(g.getCard());
        }
    }


    public void createGroup(){
        Group g = new CharlyDialogs().GroupPane(null);
        Model.getInstance().getGeneralGroupList().add(g);
        g.createCard();
        CardsContainer.getChildren().add(g.getCard());
        boolean in = Model.getInstance().getDbDriver().createGroup(g);
        System.out.println(in);
    }


    public void UpdateInfo(){
        CardsLabel.setText(Model.getInstance().getGeneralGroupList().size() + " Groups");
    }


    public void deleteCard(Node node) {
        CardsContainer.getChildren().remove(node);
    }
}
