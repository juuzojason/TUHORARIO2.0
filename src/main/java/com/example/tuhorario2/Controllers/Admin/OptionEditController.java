package com.example.tuhorario2.Controllers.Admin;

import com.example.tuhorario2.Models.ChoiceOption;
import com.example.tuhorario2.Models.ObjectEditor;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class OptionEditController implements Initializable, ObjectEditor<ChoiceOption> {

    public static final int MaxLabels = 6;
    public static final int MaxLabelLength = 15;

    public VBox content;

    public TextField AddLabelTextField;
    public Button AddLabelButton;
    public FlowPane LabelFlowPane;
    public ArrayList<OptionEditLabelListerController> LabelControllerList;
    public Button AddDayButton;
    public VBox DayVBox;
    public ArrayList<OptionEditDayListerController> DayControllerList;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        LabelControllerList = new ArrayList<>();
        DayControllerList = new ArrayList<>();

        AddLabelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                AddLabelLister(AddLabelTextField.getText());
            }
        });
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
        this.LabelFlowPane.getChildren().remove(e);
    }

    public void removeDayControllerList(OptionEditDayListerController e){
        this.DayControllerList.remove(e);
        this.DayVBox.getChildren().remove(e);
    }




    @Override
    public VBox getContent() {
        return this.content;
    }


    @Override
    public void setObject(ChoiceOption op) {

        if (op == null){
            AddDayLister();
            return;
        }

        for (Node d : LabelFlowPane.getChildren()){
            LabelFlowPane.getChildren().remove(d);
        }
        LabelControllerList = new ArrayList<>();

        ArrayList<String> lblList = op.getLabelList();
        for (String u : lblList){
            this.AddLabelLister(u);
        }

        ArrayList<Byte> dayList = op.getDayList();
        ArrayList<byte[]> hoursList = op.getHourList();
        for (int i = 0; i < dayList.size(); i++) {
            Byte bh = hoursList.get(i)[0]; //beginHour
            Byte eh = hoursList.get(i)[1]; //End Hour
            this.AddDayLister(dayList.get(i),bh,eh);
        }

    }

    //Gets the option edited
    @Override
    public ChoiceOption getObject() {
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


    @Override
    public boolean IsEverythingValid() {
        // Check that the lists are not empty and that the labels are not null or empty
        if (LabelControllerList == null || LabelControllerList.isEmpty() ||
                DayControllerList == null || DayControllerList.isEmpty() ||
                LabelControllerList.stream().anyMatch(c -> c.getText() == null || c.getText().trim().isEmpty())) {
            return false;
        }

        // Check that the days have valid hours
        return DayControllerList.stream().allMatch(e -> {
            Byte bh = e.getBeginHour(), eh = e.getEndHour();
            return bh != null && eh != null
                    && bh >= 0 && bh < 24
                    && eh >= 0 && eh < 24
                    && bh < eh;
        });
    }


}
