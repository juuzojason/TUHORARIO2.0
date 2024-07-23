package com.example.tuhorario2;

import com.example.tuhorario2.Models.*;
import com.example.tuhorario2.Views.CharlyDialogs;
import com.example.tuhorario2.Views.ViewFactory;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.swing.text.View;
import java.util.ArrayList;
import java.util.Arrays;


public class AppTest extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.initStyle(StageStyle.UNDECORATED);
        // Show the dialog and wait
//        ChoiceOption cho = new ChoiceOption();
//        cho.setLabelList(new ArrayList<>(Arrays.asList("OOOOOOOOOOOOOOO", "OOOOOOOOOOOOOOO","OOOOOOOOOOOOOOO", "OOOOOOOOOOOOOOO")));
//        cho.setDayList(new ArrayList<Byte>(Arrays.asList((byte) 0,(byte) 2,(byte) 2,(byte) 4)));
//        cho.setHourList(new ArrayList<byte[]>(Arrays.asList(new byte[]{8,16},new byte[]{12,16},new byte[]{16,24},new byte[]{16,24})));
//        ChoiceOption co = new CharlyDialogs().OptionPane(cho);
//        System.out.println(co.toString());


        Model.getInstance().getViewFactory().LoginView();

    }
}
