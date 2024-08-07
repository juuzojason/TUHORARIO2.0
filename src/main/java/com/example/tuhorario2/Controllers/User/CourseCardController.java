package com.example.tuhorario2.Controllers.User;

import com.example.tuhorario2.Models.Card;
import com.example.tuhorario2.Models.Course;
import com.example.tuhorario2.Models.Group;
import com.example.tuhorario2.Models.Model;
import com.example.tuhorario2.Views.CharlyDialogs;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class CourseCardController extends Pane implements Initializable, Card<Course> {
    public Pane ColorPaneDec;
    public Button EditButton;
    public Button DeleteButton;
    public Label NameLabel;
    public Label UserLabel;
    public Label InfoLabel;
    public Pane Background;


    private Course object;

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

        Background.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                openCourse();
            }
        });
    }

    //checks if the user is the owner and updates the look depending on the ownership
    public boolean isUserOwner(){
        boolean b = (object.getUid() == Model.getInstance().getUser().getId()) || object.getUid() == -1;
        if (b) OwnerLook();
        else notOwnerLook();
        return b;
    }


    public void OwnerLook(){}
    public void notOwnerLook(){}

    //TODO fancy
    // - create function for Hovering
    // - create function for unHovering
    public void Hovering(){
        ColorPaneDec.setPrefWidth(ColorPaneDec.getWidth() + 9);
    }
    public void unHovering(){
        ColorPaneDec.setPrefWidth(ColorPaneDec.getWidth() - 9);
    }


    public void openCourse(){Model.getInstance().selectCourse(object);}


    @Override
    public void setObject(Course object) {
        this.object = object;
    }

    @Override
    public void edit() {
        Course g = new CharlyDialogs().CoursePane(object);
        Model.getInstance().getDbDriver().updateCourse(object);
        Update();
    }

    @Override
    public void Update() {
        if (object == null) return;
        this.ColorPaneDec.setStyle("-fx-background-color: " + object.getColor().toUpperCase() + ";");
        //System.out.println(ColorPaneDec.getStyle());
        this.NameLabel.setText(object.getName());
        String utex = "By: " + (isUserOwner() ? "You" : "Other User");
        this.UserLabel.setText(utex);
        String options = object.getChoiceOptions().size() + " Options";
        String labels = object.getLabelAmount() + " Labels";
        this.InfoLabel.setText(options + " | " + labels);
    }

    @Override
    public void delete() {
        Model.getInstance().getDbDriver().deleteCourse(object);
        object.delete();
        Model.getInstance().getViewFactory().getUeControl().deleteCard(Background);
    }

    @Override
    public void copy() {
    }

    @Override
    public Pane getPane() {
        return this;
    }
}
