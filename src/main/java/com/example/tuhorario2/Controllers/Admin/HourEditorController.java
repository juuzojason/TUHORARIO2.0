package com.example.tuhorario2.Controllers.Admin;

import com.example.tuhorario2.Models.ChoiceHour;
import com.example.tuhorario2.Models.ChoiceOption;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class HourEditorController implements Initializable {


    public Spinner<Integer> HoursSpinner;
    public Button AcceptButton;
    public ChoiceBox<String> MinuteChoiceBox;
    public Button CancelButton;

    private String signal = "";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(6,21);
        valueFactory.setValue(6);
        HoursSpinner.setValueFactory(valueFactory);


        MinuteChoiceBox.setItems(FXCollections.observableArrayList("00","15","30","45"));
        MinuteChoiceBox.setValue(MinuteChoiceBox.getItems().get(0));


        CancelButton.setOnAction(event -> {
            signal = "Cancel";
            Stage stage = (Stage) CancelButton.getScene().getWindow();
            stage.close();
        });



        AcceptButton.setOnAction(event -> {
            signal = "Accept";
            Stage stage = (Stage) AcceptButton.getScene().getWindow();
            stage.close();
        });

        CancelButton.setCancelButton(true);
    }

    public String getSignal(){
        return this.signal;
    }

    public byte getHour(){
        return (new ChoiceHour(HoursSpinner.getValue(),Integer.parseInt(MinuteChoiceBox.getValue()))).getData();
    }


    public void setHour(Byte hour) {
    }
}
