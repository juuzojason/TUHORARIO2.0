package com.example.tuhorario2.Models;

import com.example.tuhorario2.Controllers.Admin.HourEditorController;
import com.example.tuhorario2.Controllers.Admin.OptionEditController;
import com.example.tuhorario2.Controllers.Admin.OptionEditDayListerController;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.stage.StageStyle;

import java.io.IOException;

public class CharlyDialogs {

    public Byte HourPane(Byte hour){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Fxml/Admin/Editors/HourEditor.fxml"));
            DialogPane dialogPane = fxmlLoader.load();
            HourEditorController hed = fxmlLoader.getController();
            hed.setHour(hour);

            // Create the dialog
            Dialog<String> dialog = new Dialog<>();
            dialog.initStyle(StageStyle.UNDECORATED);
            dialog.setDialogPane(dialogPane);

            // Show the dialog and wait
            dialog.showAndWait();
            if (hed.getSignal().equals("Accept")){
                return hed.getHour();
            }
        } catch (IOException e){
        }
        return null;
    }



    public ChoiceOption OptionPane(ChoiceOption op){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Fxml/Admin/Editors/OptionEdit.fxml"));
            DialogPane dialogPane = fxmlLoader.load();
            OptionEditController hed = fxmlLoader.getController();
            hed.setChoiceOption(op);

            // Create the dialog
            Dialog<String> dialog = new Dialog<>();
            dialog.initStyle(StageStyle.UNDECORATED);
            dialog.setDialogPane(dialogPane);

            // Show the dialog and wait
            dialog.showAndWait();
            if (hed.getSignal().equals("Accept")){
                return hed.getChoiceOption();
            }
        } catch (IOException e){
        }
        return null;
    }


}
