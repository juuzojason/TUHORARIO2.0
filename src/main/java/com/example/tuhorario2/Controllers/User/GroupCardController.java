package com.example.tuhorario2.Controllers.User;

import com.example.tuhorario2.Models.Card;
import com.example.tuhorario2.Models.Group;
import com.example.tuhorario2.Models.Model;
import com.example.tuhorario2.Views.CharlyDialogs;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class GroupCardController extends Pane implements Initializable, Card<Group> {
    public Pane Background;
    public Pane ColorPaneDec;
    public Button EditButton;
    public Button DeleteButton;
    public Label NameLabel;
    public Label UserLabel;
    public Label InfoLabel;

    public FlowPane container;


    private Group object;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //set the deleteButton function
        DeleteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (isUserOwner()){
                    delete();
                }
            }
        });

        //set the edit function
        EditButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (isUserOwner()){
                    edit();
                }
            }
        });

        Background.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Hovering();
            }
        });
        Background.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                unHovering();
            }
        });

    }

    @Override
    public void setObject(Group object) {
        this.object = object;
    }

    //this function creates a popup to edit this object and then updates the database and this card
    @Override
    public void edit() {
        Group g = new CharlyDialogs().GroupPane(object);
        Model.getInstance().getDbDriver().updateGroup(object);
        Update();
    }

    //TODO function when clicking in the card


    //TODO fancy
    // - create function for Hovering
    // - create function for unHovering
    public void Hovering(){
        ColorPaneDec.setPrefWidth(ColorPaneDec.getWidth() + 9);
    }
    public void unHovering(){
        ColorPaneDec.setPrefWidth(ColorPaneDec.getWidth() - 9);
    }

    //TODO change when is not owner:
    // - it should change the function of the button edit so it copies the Group
    // - it should have another look (button edit)
    // - also delete the remove button disable it or make it invisible
    // - create other function so it changes the look back how it was
    public void notOwnerLook(){}
    public void OwnerLook(){}


    //this function updates the look of the card so it matches its object
    @Override
    public void Update() {
        if (object == null) return;
        this.ColorPaneDec.setStyle("-fx-background-color: " + object.getColor().toUpperCase() + ";");
        System.out.println(ColorPaneDec.getStyle());
        this.NameLabel.setText(object.getName());
        String utex = "Created By: " + (isUserOwner() ? "You" : "Other User");
        this.UserLabel.setText(utex);
        String courses = object.getCourseAmount() + " Courses";
        String sem = "Semester: " + object.getSemester();
        this.InfoLabel.setText(courses + " | " + sem);
    }

    //checks if the user is the owner and updates the look depending on the ownership
    public boolean isUserOwner(){
        //System.out.println(object.getuserid() + "  " + Model.getInstance().getUser().getId());
        boolean b = (object.getuserid() == Model.getInstance().getUser().getId()) || object.getuserid() == -1;
        if (b) OwnerLook();
        else notOwnerLook();
        return b;
    }

    //this function deletes the object from te database from the group list and the card from the flow pane
    @Override
    public void delete() {
        Model.getInstance().getDbDriver().deleteGroup(object);
        object.delete();
        Model.getInstance().getViewFactory().getUeControl().deleteCard(Background);
    }


    //TODO should
    // - create a copy of the object
    // - add the object to the database
    // - add the object to the group list
    // - create the card
    // - add the card to the flow pane
    @Override
    public void copy() {

    }

    @Override
    public Pane getPane() {
        return this;
    }
}
