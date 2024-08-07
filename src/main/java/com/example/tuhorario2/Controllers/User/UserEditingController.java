package com.example.tuhorario2.Controllers.User;

import com.example.tuhorario2.Models.ChoiceOption;
import com.example.tuhorario2.Models.Course;
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
import javafx.scene.layout.Background;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.function.Predicate;

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
                Model ins = Model.getInstance();
                if (ins.getSelectedCourse() != null){
                    createOption();
                }else if (ins.getSelectedGroup() != null){
                    createCourse();
                } else createGroup();
            }
        });

        BackButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Back();
            }
        });

        searchButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                generateCards();
            }
        });

        generateCards();
    }

    public void Back(){
        Model ins = Model.getInstance();
        if (ins.getSelectedCourse() != null){
            ins.unselectCourse();
        }else if (ins.getSelectedGroup() != null){
            ins.unselectGroup();
        }
        UpdateInfo();
    }


    public void generateCards(){
        removeCards();

        Model ins = Model.getInstance();
        if (ins.getSelectedCourse() != null){
            for (ChoiceOption o : ins.getSelectedCourse().getChoiceOptions()){
                o.createCard();
                CardsContainer.getChildren().add(o.getCard());
            }
        }else if (ins.getSelectedGroup() != null){
            for (Course c : ins.getSelectedGroup().getCourses()){
                //TODO replace with search function: it has to check if the name is similar to the searchBar
                //if (!(c.getName().)) continue;
                c.createCard();
                CardsContainer.getChildren().add(c.getCard());
            }
        } else {
            for (Group g : ins.getGeneralGroupList()){
                //TODO replace with search function: it has to check if the name is similar to the searchBar
                //if (!(g.getName().)) continue;
                g.createCard();
                CardsContainer.getChildren().add(g.getCard());
            }
        }
        UpdateInfo();
    }

    public void removeCards(){
        for (int i = CardsContainer.getChildren().size()-1; i >= 0 ; i--){
            CardsContainer.getChildren().remove(i);
        }
    }


    public void createGroup(){
        Group g = new CharlyDialogs().GroupPane(null);
        if (g == null) return;
        Model.getInstance().getGeneralGroupList().add(g);
        g.createCard();
        CardsContainer.getChildren().add(g.getCard());
        boolean in = Model.getInstance().getDbDriver().createGroup(g);
        System.out.println(in);
    }

    //TODO create Option
    public void createOption(){
        ChoiceOption c = new CharlyDialogs().OptionPane(null);
        if (c == null) return;
        Course selected = Model.getInstance().getSelectedCourse();
        selected.getChoiceOptions().add(c);
        c.createCard();
        CardsContainer.getChildren().add(c.getCard());
        boolean in = Model.getInstance().getDbDriver().createOption(c, selected.getId());
    }

    //TODO create Course
    public void createCourse(){
        Course g = new CharlyDialogs().CoursePane(null);
        if (g == null) return;
        Group selected = Model.getInstance().getSelectedGroup();
        selected.getCourses().add(g);
        g.createCard();
        CardsContainer.getChildren().add(g.getCard());
        boolean in = Model.getInstance().getDbDriver().createCourse(g, selected.getId());
    }


    public void UpdateInfo(){
        Model ins = Model.getInstance();

        //removes the back button if necessary
        if (ins.getSelectedGroup() == null) {BackButton.setDisable(true); BackButton.setVisible(false);}
        else {BackButton.setDisable(false); BackButton.setVisible(true);}

        //sets the group path
        String s = "";
        if (ins.getSelectedGroup() != null) s = ins.getSelectedGroup().getName();
        if (ins.getSelectedCourse() != null) s += "/" + ins.getSelectedCourse().getName();
        PathLabel.setText(s);


        //sets the quantity of the objects
        String quantity = (ins.getSelectedGroup() == null) ? " Groups" : "";
        quantity = (ins.getSelectedGroup() != null && ins.getSelectedCourse() == null) ? " Courses" : quantity;
        quantity = (ins.getSelectedCourse() == null) ? quantity : " Options";
        CardsLabel.setText(CardsContainer.getChildren().size() + quantity);
    }


    public void deleteCard(Node node) {
        CardsContainer.getChildren().remove(node);
    }
}
