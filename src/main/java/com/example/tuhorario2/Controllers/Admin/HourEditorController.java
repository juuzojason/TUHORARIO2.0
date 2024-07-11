package com.example.tuhorario2.Controllers.Admin;

import com.example.tuhorario2.Models.ChoiceHour;
import com.example.tuhorario2.Models.ChoiceOption;
import com.example.tuhorario2.Models.ObjectEditor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class HourEditorController implements Initializable, ObjectEditor<ChoiceHour> {


    public Spinner<Integer> HoursSpinner;
    public ChoiceBox<String> MinuteChoiceBox;

    public VBox content;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(6,21);
        valueFactory.setValue(6);
        HoursSpinner.setValueFactory(valueFactory);


        MinuteChoiceBox.setItems(FXCollections.observableArrayList("00","15","30","45"));
        MinuteChoiceBox.setValue(MinuteChoiceBox.getItems().get(0));

    }

    public byte getHour(){
        return (new ChoiceHour(HoursSpinner.getValue(),Integer.parseInt(MinuteChoiceBox.getValue()))).getData();
    }


    public void setHour(Byte hour) {
    }


    @Override
    public VBox getContent() {
        return this.content;
    }

    @Override
    public void setObject(ChoiceHour object) {
        setHour(object.getData());
    }

    @Override
    public ChoiceHour getObject() {
        return new ChoiceHour(getHour());
    }

    //TODO check if a validation is needed or not
    @Override
    public boolean IsEverythingValid() {
        return false;
    }
}
