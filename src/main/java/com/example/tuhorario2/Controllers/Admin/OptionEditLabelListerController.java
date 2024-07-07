package com.example.tuhorario2.Controllers.Admin;

import com.example.tuhorario2.Models.ChoiceHour;
import com.example.tuhorario2.Models.ChoiceOption;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class OptionEditLabelListerController implements Initializable {


    private HBox object = null;

    public Label TextLabel;
    public Button DeleteButton;

    private OptionEditController father = null;
    private FlowPane fatherList = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    public void setText(String text){
        this.TextLabel.setText(text);
    }


    public void setFather(OptionEditController op){
        father = op;
    }

    public void setObject(HBox b){
        this.object = b;
    }

    public void setFatherList(FlowPane fl){
        this.fatherList = fl;
    }

    public void deleteFromList(){
        this.father.enableAddButton();
        this.fatherList.getChildren().remove(object);
    }



}
