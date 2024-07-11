package com.example.tuhorario2.Models;

import com.example.tuhorario2.Controllers.Admin.HourEditorController;
import com.example.tuhorario2.Controllers.Admin.OptionEditController;
import com.example.tuhorario2.Controllers.Admin.OptionEditDayListerController;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.layout.VBox;
import javafx.stage.StageStyle;

import java.io.IOException;

public class CharlyDialogs {

    public ChoiceHour HourPane(ChoiceHour hour){
        try {


            //add content to basicDialog
            FXMLLoader fxmlLoader2 = new FXMLLoader(getClass().getResource("/Fxml/Admin/Editors/HourEditor.fxml"));
            HourEditorController hed = fxmlLoader2.getController();
            VBox vb = fxmlLoader2.load();
            hed.setObject(hour);

            //load basicDialog
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Fxml/Admin/Editors/BasicDialog.fxml"));
            DialogPane cd = fxmlLoader.load();
            CharlyDialog con = fxmlLoader.getController();
            con.addContent(vb);

            // Create the dialog
            Dialog<String> dialog = new Dialog<>();
            dialog.initStyle(StageStyle.UNDECORATED);
            dialog.setDialogPane(cd);

            // Show the dialog and wait
            dialog.showAndWait();
            if (con.getSignal().equals("Accept")){
                return hed.getObject();
            }
        } catch (IOException e){
        }
        return null;
    }


    public ChoiceOption OptionPane(ChoiceOption op){
        try {
            //load basicDialog
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Fxml/Admin/Editors/BasicDialog.fxml"));
            DialogPane cd = fxmlLoader.load();
            CharlyDialog con = fxmlLoader.getController();

            //add content to basicDialog
            FXMLLoader fxmlLoader2 = new FXMLLoader(getClass().getResource("/Fxml/Admin/Editors/OptionEdit.fxml"));
            VBox vb = fxmlLoader2.load();
            OptionEditController hed = fxmlLoader2.getController();
            hed.setObject(op);


            con.addContent(vb);

            // Create the dialog
            Dialog<String> dialog = new Dialog<>();
            dialog.initStyle(StageStyle.UNDECORATED);
            dialog.setDialogPane(cd);

            // Show the dialog and wait
            dialog.showAndWait();
            if (con.getSignal().equals("Accept")){
                return hed.getObject();
            }
        } catch (IOException e){
            System.out.println(e);
        }
        return null;
    }


}
