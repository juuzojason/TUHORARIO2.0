package com.example.tuhorario2.Views;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;


public class CharlyDialog extends DialogPane implements Initializable{

    private double xOffset = 0;
    private double yOffset = 0;

    public DialogPane Dialog;
    public HBox TitleBar;
    public Label TitleLabel;
    public Button CloseButton;
    public VBox ContentContainer;
    public Button CancelButton;
    public Button AcceptButton;
    private String signal;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setButtonActions();
    }

    public final void setButtonActions(){
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
            if (!this.ObjectValidation()) return;
            signal = "Accept";
            Stage stage = (Stage) AcceptButton.getScene().getWindow();
            stage.close();
        });

        setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });

        TitleBar.setOnMouseDragged(event -> {
            //Dialog.setLayoutX(event.getScreenX() - xOffset);
            //Dialog.setLayoutX(event.getScreenY() - yOffset);
        });

        CancelButton.setCancelButton(true);

    }

    //TODO fix this
    public boolean ObjectValidation(){
        return true;
    }

    public void addContent(VBox con){

        Dialog.setMinWidth(con.getPrefWidth()+20);
        Dialog.setMinHeight(con.getPrefHeight()+20+ TitleBar.getPrefHeight()+40);

        ContentContainer.setPrefWidth(con.getPrefWidth()+20);
        ContentContainer.setPrefHeight(con.getPrefHeight()+60);
        System.out.println(ContentContainer.getWidth() + " " + ContentContainer.getHeight());


        ContentContainer.getChildren().add(0, con);

    }

    public String getSignal() {
        return signal;
    }


}
