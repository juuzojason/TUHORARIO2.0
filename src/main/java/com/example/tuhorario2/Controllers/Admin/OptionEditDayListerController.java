package com.example.tuhorario2.Controllers.Admin;

import com.example.tuhorario2.Models.CharlyDialogs;
import com.example.tuhorario2.Models.ChoiceDay;
import com.example.tuhorario2.Models.ChoiceHour;
import com.example.tuhorario2.Models.ChoiceOption;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class OptionEditDayListerController implements Initializable {

    public ComboBox<String> DayCB;
    private HBox object = null;


    public Button BeginHourButton;
    public Button EndHourButton;
    public Button DeleteButton;

    private OptionEditController father = null;
    private VBox fatherList = null;


    private byte day = 0;
    private byte beginHour = 8;
    private byte endHour = 16;

    private String signal;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DayCB.setItems(FXCollections.observableArrayList(ChoiceDay.getWeekDays()));
        DayCB.setValue(DayCB.getItems().get(0));
    }

    public void setNormalDay(){
        setDay(ChoiceOption.NormalDay);
        setBeginHour(ChoiceOption.NormalBeginHour);
        setEndHour(ChoiceOption.NormalEndHour);
    }

    public void setDay(byte day){
        this.day = day;
        DayCB.setValue(ChoiceDay.DayOf(day));
    }

    public int getDay(){
        return ChoiceDay.NumOf(this.DayCB.getValue());
    }


    public void setBeginHour(byte beginHour){
        this.beginHour = beginHour;
        BeginHourButton.setText(ChoiceHour.toString(beginHour));
    }

    public byte getBeginHour(){
        return this.beginHour;
    }

    public void setEndHour(byte endHour){
        this.endHour = endHour;
        EndHourButton.setText(ChoiceHour.toString(endHour));
    }

    public byte getEndHour(){
        return this.endHour;
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
        if (this.fatherList.getChildren().size() == 2) return;
        this.father.enableAddButton();
        this.fatherList.getChildren().remove(object);
        this.father.removeDayControllerList(this);
    }


    public void OnBeginHourButton() {
        Byte hour = new CharlyDialogs().HourPane(null); //TODO change this
        if (hour != null){
            setBeginHour(hour);
            if (beginHour > endHour) setEndHour(hour);
        }
    }



    public void OnEndHourButton() {
        Byte hour = new CharlyDialogs().HourPane(null); //TODO change this
        if (hour != null){
            setEndHour(hour);
            if (beginHour > endHour) setBeginHour(hour);
        }
    }

}
