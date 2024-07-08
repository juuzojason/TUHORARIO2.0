package com.example.tuhorario2.Controllers.Admin;

import com.example.tuhorario2.Models.ChoiceOption;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class OptionEditController implements Initializable {

    public static final int MaxLabels = 6;
    public static final int MaxLabelLength = 15;

    public Button CloseButton;
    public TextField AddLabelTextField;
    public Button AddLabelButton;
    public FlowPane LabelFlowPane;
    public Button AddDayButton;
    public VBox DayVBox;

    public Button CancelButton;
    public Button AcceptButton;
    public DialogPane root;


    private String signal;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        CloseButton.setOnAction(event -> {
            signal = "Close";
            Stage stage = (Stage) CloseButton.getScene().getWindow();
            stage.close();
        });


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

        AddDayLister();
    }

    @FXML
    public void AddDayLister(){
        if (DayVBox.getChildren().size() >= 4) disableAddButton();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/Fxml/Admin/Listers/OptionEditDayLister.fxml"));
            HBox DayLister = fxmlLoader.load();
            DayVBox.getChildren().add(DayVBox.getChildren().size()-1,DayLister);

            OptionEditDayListerController DayController = fxmlLoader.getController();
            DayController.setObject(DayLister);
            DayController.setFather(this);
            DayController.setFatherList(DayVBox);
            DayController.setNormalDay();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getSignal() {
        return signal;
    }

    public void AddLabelLister() {
        String tfText = AddLabelTextField.getText();
        if (tfText.isBlank() || tfText.isEmpty()) return;
        if (LabelFlowPane.getChildren().size() >= MaxLabels) {
            disableLabelButton();
            return;
        }
        if (tfText.length() > MaxLabelLength) return;

        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/Fxml/Admin/Listers/OptionEditLabelLister.fxml"));
            HBox LabelLister = fxmlLoader.load();
            LabelFlowPane.getChildren().add(LabelLister);

            OptionEditLabelListerController labelController = fxmlLoader.getController();
            labelController.setObject(LabelLister);
            labelController.setFather(this);
            labelController.setFatherList(LabelFlowPane);
            labelController.setText(tfText);

            AddLabelTextField.setText("");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private void disableAddButton(){
        this.AddDayButton.setDisable(true);
    }

    public void enableAddButton(){
        this.AddDayButton.setDisable(false);
    }


    private void disableLabelButton(){
        this.AddLabelButton.setDisable(true);
    }

    public void enableLabelButton(){
        this.AddLabelButton.setDisable(false);
    }

    //TODO create this method
    public ChoiceOption getChoiceOption() {
        return null;
    }

    //TODO create this method
    public void setChoiceOption(ChoiceOption op) {
    }

}
