package com.example.tuhorario2;

import com.example.tuhorario2.Models.DBDriver;
import com.example.tuhorario2.Views.CharlyDialogs;
import com.example.tuhorario2.Models.ChoiceOption;
import javafx.application.Application;
import javafx.stage.Stage;

public class AppTest extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        // Show the dialog and wait
        ChoiceOption op = new CharlyDialogs().OptionPane(null);
        DBDriver driver = new DBDriver();
    }
}
