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


    //TODO fix choiceOption card creation
    public void generateCards(){
        removeCards();

        Model ins = Model.getInstance();
        if (ins.getSelectedCourse() != null){
            for (ChoiceOption o : ins.getSelectedCourse().getChoiceOptions()){
                //o.createCard();
                //
                //
            }
        }else if (ins.getSelectedGroup() != null){
            for (Course c : ins.getSelectedGroup().getCourses()){
                c.createCard();
                CardsContainer.getChildren().add(c.getCard());
            }
        } else {
            for (Group g : ins.getGeneralGroupList()){
                g.createCard();
                CardsContainer.getChildren().add(g.getCard());
            }
        }
    }

    public void removeCards(){
        for (Node n : CardsContainer.getChildren()) CardsContainer.getChildren().remove(n);
    }


    public void createGroup(){
        Group g = new CharlyDialogs().GroupPane(null);
        Model.getInstance().getGeneralGroupList().add(g);
        g.createCard();
        CardsContainer.getChildren().add(g.getCard());
        boolean in = Model.getInstance().getDbDriver().createGroup(g);
        System.out.println(in);
    }

    //TODO create Option
    public void createOption(){}
    //TODO create Course
    public void createCourse(){}


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
        quantity += (ins.getSelectedGroup() == null) ? "" : " Courses";
        quantity += (ins.getSelectedCourse() == null) ? "" : " Options";
        CardsLabel.setText(Model.getInstance().getGeneralGroupList().size() + quantity);
    }


    public void deleteCard(Node node) {
        CardsContainer.getChildren().remove(node);
    }
}
