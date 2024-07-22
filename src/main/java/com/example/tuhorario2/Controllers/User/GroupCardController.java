package com.example.tuhorario2.Controllers.User;

import com.example.tuhorario2.Models.Card;
import com.example.tuhorario2.Models.Group;
import com.example.tuhorario2.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
        
    }

    @Override
    public void setObject(Group object) {
        this.object = object;
    }

    //TODO edit should popup the edit window and update when finish
    @Override
    public void edit() {
        //creates the popup
        Model.getInstance().getDbDriver().updateGroup(object);
        Update();
    }


    //TODO change button look and function when is not owner


    @Override
    public void Update() {
        if (object == null) return;
        this.ColorPaneDec.setStyle("-fx-style-background-color " + object.getColor());
        this.NameLabel.setText(object.getName());
        String utex = "Created By: " + (isUserOwner() ? "You" : "Other User");
        this.UserLabel.setText(utex);
        String courses = object.getCourseAmount() + " Courses";
        String sem = "Semester: " + object.getSemester();
        this.InfoLabel.setText(courses + " | " + sem);
    }

    public boolean isUserOwner(){
        return (object.getId() == Model.getInstance().getUser().getId());
    }


    //TODO delete should delete the object from the database
    @Override
    public void delete() {
        //Model.getInstance().getDbDriver().deleteGroup(object);
        object.delete();
        this.getParent().getChildrenUnmodifiable().remove(this);
    }


    //TODO copy should create a copy of this Object in the database
    @Override
    public void copy() {

    }

    @Override
    public Pane getPane() {
        return this;
    }
}
