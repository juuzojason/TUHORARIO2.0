package com.example.tuhorario2.Controllers.Admin;

import com.example.tuhorario2.Models.ChoiceHour;
import com.example.tuhorario2.Models.ChoiceOption;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class OptionEditDayListerController implements Initializable {

    private HBox object = null;


    public Button DayButton;
    public Button BeginHourButton;
    public Button EndHourButton;
    public Button DeleteButton;

    private OptionEditController father = null;
    private VBox fatherList = null;
    private byte day = 0;
    private byte beginHour = 8;
    private byte endHour = 16;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setNormalDay(){
        setDay(ChoiceOption.NormalDay);
        setBeginHour(ChoiceOption.NormalBeginHour);
        setEndHour(ChoiceOption.NormalEndHour);
    }

    public void setDay(byte day){
        this.day = day;
        DayButton.setText(day+"");
    }

    public void setBeginHour(byte beginHour){
        this.beginHour = beginHour;
        BeginHourButton.setText(ChoiceHour.toString(beginHour));
    }

    public void setEndHour(byte endHour){
        this.endHour = endHour;
        EndHourButton.setText(ChoiceHour.toString(endHour));
    }


    public void setFather(OptionEditController op){
        father = op;
    }

    public void setObject(HBox b){
        this.object = b;
    }

    public void setFatherList(VBox fl){
        this.fatherList = fl;
    }

    public void deleteFromList(){
        if (this.fatherList.getChildren().size() == 1) return;
        this.father.enableAddButton();
        this.fatherList.getChildren().remove(object);
    }



}
