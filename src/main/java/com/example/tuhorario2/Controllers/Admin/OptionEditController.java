package com.example.tuhorario2.Controllers.Admin;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        CloseButton.setOnAction(event -> {
            // Handle the close button click here
            Stage stage = (Stage) CloseButton.getScene().getWindow();
            stage.close(); // Close the stage
        });
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

    public void AddLabelLister() {
        String tfText = AddLabelTextField.getText();
        if (tfText.isBlank() || tfText.isEmpty()) return;
        if (LabelFlowPane.getChildren().size() >= MaxLabels) return;
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

            AddLabelTextField.setText(tfText);
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

}
