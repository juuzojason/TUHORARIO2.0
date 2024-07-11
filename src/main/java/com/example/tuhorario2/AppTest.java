package com.example.tuhorario2;

import com.example.tuhorario2.Controllers.Admin.OptionEditController;
import com.example.tuhorario2.Models.CharlyDialogs;
import com.example.tuhorario2.Models.ChoiceOption;
import com.example.tuhorario2.Models.Group;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Optional;

public class AppTest extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        // Show the dialog and wait
        Group op = new CharlyDialogs().GroupPane(null);

    }
}
