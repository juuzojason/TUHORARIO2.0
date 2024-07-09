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
import javafx.util.Pair;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class OptionEditController implements Initializable {

    public static final int MaxLabels = 6;
    public static final int MaxLabelLength = 15;

    public Button CloseButton;
    public TextField AddLabelTextField;
    public Button AddLabelButton;
    public FlowPane LabelFlowPane;
    public ArrayList<OptionEditLabelListerController> LabelControllerList;
    public Button AddDayButton;
    public VBox DayVBox;
    public ArrayList<OptionEditDayListerController> DayControllerList;

    public Button CancelButton;
    public Button AcceptButton;
    public DialogPane root;


    private String signal;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        LabelControllerList = new ArrayList<>();
        DayControllerList = new ArrayList<>();

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
            this.DayControllerList.add(DayController);
            DayController.setObject(DayLister);
            DayController.setFather(this);
            DayController.setFatherList(DayVBox);
            DayController.setNormalDay();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void AddDayLister(Byte day, Byte bHour, Byte eHour){
        if (DayVBox.getChildren().size() >= 4) disableAddButton();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/Fxml/Admin/Listers/OptionEditDayLister.fxml"));
            HBox DayLister = fxmlLoader.load();
            DayVBox.getChildren().add(DayVBox.getChildren().size()-1,DayLister);

            OptionEditDayListerController DayController = fxmlLoader.getController();
            this.DayControllerList.add(DayController);
            DayController.setObject(DayLister);
            DayController.setFather(this);
            DayController.setFatherList(DayVBox);
            DayController.setDay(day);
            DayController.setBeginHour(bHour);
            DayController.setEndHour(eHour);

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
            this.LabelControllerList.add(labelController);
            labelController.setObject(LabelLister);
            labelController.setFather(this);
            labelController.setFatherList(LabelFlowPane);
            labelController.setText(tfText);

            AddLabelTextField.setText("");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void AddLabelLister(String s) {
        if (s.isBlank() || s.isEmpty()) return;
        if (LabelFlowPane.getChildren().size() >= MaxLabels) {
            disableLabelButton();
            return;
        }
        if (s.length() > MaxLabelLength) return;

        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/Fxml/Admin/Listers/OptionEditLabelLister.fxml"));
            HBox LabelLister = fxmlLoader.load();
            LabelFlowPane.getChildren().add(LabelLister);

            OptionEditLabelListerController labelController = fxmlLoader.getController();
            this.LabelControllerList.add(labelController);
            labelController.setObject(LabelLister);
            labelController.setFather(this);
            labelController.setFatherList(LabelFlowPane);
            labelController.setText(s);

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

    public void removeLabelControllerList(OptionEditLabelListerController e){
        this.LabelControllerList.remove(e);
    }

    public void removeDayControllerList(OptionEditDayListerController e){
        this.DayControllerList.remove(e);
    }


    //TODO fix this method : have to do some crutial checks before
    public ChoiceOption getChoiceOption() {
        ChoiceOption newer = new ChoiceOption();
        for (OptionEditLabelListerController optionEditLabelListerController : LabelControllerList) {
            String lab = optionEditLabelListerController.getText();
            newer.addLabel(lab);
        }

        for (OptionEditDayListerController e : DayControllerList) {
            newer.addDay((byte) e.getDay(), e.getBeginHour(), e.getEndHour());
        }

        return newer;
    }

    //TODO create this method
    public void setChoiceOption(ChoiceOption op) {
        ArrayList<String> lblList = op.getLabelList();
        for (String u : lblList){
            this.AddLabelLister(u);
        }

        ArrayList<Byte> dayList = op.getDayList();
        ArrayList<Pair<Byte,Byte>> hoursList = op.getHourList();
        for (int i = 0; i < dayList.size(); i++) {
            Byte bh = hoursList.get(i).getKey(); //beginHour
            Byte eh = hoursList.get(i).getValue(); //End Hour
            this.AddDayLister(dayList.get(i),bh,eh);
        }

    }

}
