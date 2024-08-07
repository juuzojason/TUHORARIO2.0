package com.example.tuhorario2.Controllers.User;

import com.example.tuhorario2.Models.*;
import com.example.tuhorario2.Views.CharlyDialogs;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.net.URL;
import java.util.ResourceBundle;

public class OptionCardController extends Pane implements Initializable, Card<ChoiceOption> {
    public Pane InteractablePane;
    public Button DeleteButton;
    public Label LabelLabel;
    public Label NumLabel;
    public Button EditButton;
    public HBox DayPane;

    //TODO change sky shades so it represents correctly the hour, 1st: 6am, 2nd: 8am, 3rd: 10am ... 8th: 8pm
    public static String[] skyShades = new String[]{"#1b3d8b","#1b3d8b","#1b3d8b","#1b3d8b","#1b3d8b","#1b3d8b","#1b3d8b","#1b3d8b"};

    public ChoiceOption object;
    public VBox BackGround;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DeleteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (isUserOwner()) delete();
            }
        });

        EditButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (isUserOwner()) edit();
            }
        });
    }

    @Override
    public void setObject(ChoiceOption object) {
        this.object = object;
    }

    //TODO fancy stuff
    public void Hovering(){}
    //TODO fancy stuff
    public void unHovering(){}


    @Override
    public void edit() {
        ChoiceOption co = new CharlyDialogs().OptionPane(object);
        Model.getInstance().getDbDriver().updateOption(object);
        Update();
    }

    @Override
    public void Update() {
        if (object == null) return;
        isUserOwner();

        //Set the label thing
        if (!object.getLabelList().isEmpty()){
            String fstLab = object.getLabelList().get(0);
            LabelLabel.setText(fstLab);
            NumLabel.setText((object.getLabelList().size()-1) + "..");
        } else {
            LabelLabel.setText("NoLabels");
            NumLabel.setText("");
        }

        //Builds the days
        clearDays();
        for (int i = 0; i < object.getDayList().size(); i++){
            BackGround.getChildren().add(createDayPane(ChoiceDay.DayOf(object.getDayList().get(i)),object.getHourList().get(i)));
        }

    }

    //Removes all days
    public void clearDays(){
        for (int i = 1; i < BackGround.getChildren().size(); i++){
            BackGround.getChildren().remove(i);
        }
    }


    public HBox createDayPane(String dayOfWeek, byte[] hour) {
        String timeRange = "";
        timeRange += ChoiceHour.toString(hour[0]);
        timeRange += " - ";
        timeRange += ChoiceHour.toString(hour[1]);
        HBox dayPane = new HBox();
        dayPane.setAlignment(Pos.CENTER);
        dayPane.setPrefHeight(25.0);
        dayPane.setPrefWidth(180.0);
        dayPane.setSpacing(10.0);
        dayPane.setStyle("-fx-background-color: #F0F0F0; -fx-border-color: #A0A0A0;");

        Label dayLabel = new Label(dayOfWeek);
        dayLabel.setPrefHeight(16.0);

        Label timeLabel = new Label(timeRange);
        timeLabel.setPrefHeight(16.0);
        Font smallFont = new Font(10.0);
        timeLabel.setFont(smallFont);

        Pane colorPane = new Pane();
        colorPane.setMaxHeight(Double.NEGATIVE_INFINITY);
        colorPane.setPrefHeight(15.0);
        colorPane.setPrefWidth(15.0);
        colorPane.setStyle("-fx-background-color: "+skyShades[ChoiceHour.getHour(hour[1])-6]+"; -fx-background-radius: 10;");

        dayPane.getChildren().addAll(dayLabel, timeLabel, colorPane);

        return dayPane;
    }

    //TODO change when is not owner:
    // - it should change the function of the button edit so it copies the Group
    // - it should have another look (button edit)
    // - also delete the remove button disable it or make it invisible
    // - create other function so it changes the look back how it was
    public void notOwnerLook(){}
    public void OwnerLook(){}


    //checks if the user is the owner and updates the look depending on the ownership
    public boolean isUserOwner(){
        //System.out.println(object.getuserid() + "  " + Model.getInstance().getUser().getId());
        boolean b = (object.getUid() == Model.getInstance().getUser().getId()) || object.getUid() == -1;
        if (b) OwnerLook();
        else notOwnerLook();
        return b;
    }

    @Override
    public void delete() {
        Model.getInstance().getDbDriver().deleteOption(object);
        object.delete();
        Model.getInstance().getViewFactory().getUeControl().deleteCard(BackGround);
    }

    @Override
    public void copy() {

    }

    @Override
    public Pane getPane() {
        return this;
    }
}
