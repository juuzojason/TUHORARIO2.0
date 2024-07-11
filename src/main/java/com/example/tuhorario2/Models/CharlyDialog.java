package com.example.tuhorario2.Models;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class CharlyDialog implements Initializable{


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
        con.setMinHeight(380);
        con.setMinWidth(620);
        ContentContainer.getChildren().add(0, con);
        ContentContainer.setMinHeight(360);
        Dialog.setPrefWidth(con.getWidth()+20);
        Dialog.setPrefHeight(100+ con.getHeight());
        //TitleBar.setPrefWidth(con.getWidth()+20);
    }

    public String getSignal() {
        return signal;
    }


}
