package com.example.tuhorario2;

import com.example.tuhorario2.Models.Course;
import com.example.tuhorario2.Models.DBDriver;
import com.example.tuhorario2.Models.Group;
import com.example.tuhorario2.Views.CharlyDialogs;
import com.example.tuhorario2.Models.ChoiceOption;
import javafx.application.Application;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;


public class AppTest extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        // Show the dialog and wait
        ChoiceOption cho = new ChoiceOption();
        cho.setLabelList(new ArrayList<>(Arrays.asList("OOOOOOOOOOOOOOO", "OOOOOOOOOOOOOOO","OOOOOOOOOOOOOOO", "OOOOOOOOOOOOOOO")));
        cho.setDayList(new ArrayList<Byte>(Arrays.asList((byte) 0,(byte) 2,(byte) 2,(byte) 4)));
        cho.setHourList(new ArrayList<byte[]>(Arrays.asList(new byte[]{8,16},new byte[]{12,16},new byte[]{16,24},new byte[]{16,24})));
        ChoiceOption co = new CharlyDialogs().OptionPane(cho);
        System.out.println(co.toString());
    }
}
