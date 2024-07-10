package com.example.tuhorario2.Models;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class CharlyDialog<T, C extends ObjectEditor<T>> extends Dialog<T> implements Initializable{


    private Label TitleLabel;
    private Button CloseButton;
    private VBox ContentContainer;
    private Button CancelButton;
    private Button AcceptButton;

    private String signal;
    private C controller;

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
            if (!this.ObjectValidation()) return;
            signal = "Accept";
            Stage stage = (Stage) AcceptButton.getScene().getWindow();
            stage.close();
        });

        CancelButton.setCancelButton(true);
    }

    public boolean ObjectValidation(){
        if (controller == null) return false;
        return controller.IsEverythingValid();
    }

    public void addContent(C con, Node content){
        controller = con;
        ContentContainer.getChildren().add(0,content);
    }


}
