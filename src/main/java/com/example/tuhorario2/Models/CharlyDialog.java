package com.example.tuhorario2.Models;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.TimerTask;


public class CharlyDialog extends DialogPane implements Initializable{


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

        CancelButton.setCancelButton(true);

    }

    //TODO fix this
    public boolean ObjectValidation(){
        return true;
    }

    public void addContent(VBox con){
        HBox u = (HBox) TitleBar.getChildren().get(0);
        u.setPrefWidth(con.getPrefWidth()-80);


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
