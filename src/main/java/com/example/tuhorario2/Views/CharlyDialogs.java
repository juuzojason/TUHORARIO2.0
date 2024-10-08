package com.example.tuhorario2.Views;

import com.example.tuhorario2.Controllers.Admin.CourseEditorController;
import com.example.tuhorario2.Controllers.Admin.GroupEditorController;
import com.example.tuhorario2.Controllers.Admin.HourEditorController;
import com.example.tuhorario2.Controllers.Admin.OptionEditController;
import com.example.tuhorario2.Models.ChoiceHour;
import com.example.tuhorario2.Models.ChoiceOption;
import com.example.tuhorario2.Models.Course;
import com.example.tuhorario2.Models.Group;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.layout.VBox;
import javafx.stage.StageStyle;

import java.io.IOException;

public class CharlyDialogs {

    public ChoiceHour HourPane(ChoiceHour hour){
        try {
            //load basicDialog
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Fxml/Admin/Editors/BasicDialog.fxml"));
            DialogPane cd = fxmlLoader.load();
            CharlyDialog con = fxmlLoader.getController();

            //add content to basicDialog
            FXMLLoader fxmlLoader2 = new FXMLLoader(getClass().getResource("/Fxml/Admin/Editors/HourEditor.fxml"));
            VBox vb = fxmlLoader2.load();
            HourEditorController hed = fxmlLoader2.getController();
            hed.setObject(hour);

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
        } catch (IOException ignored){
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
            e.printStackTrace();
        }
        return null;
    }

    public Group GroupPane(Group op){
        try {
            //load basicDialog
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Fxml/Admin/Editors/BasicDialog.fxml"));
            DialogPane cd = fxmlLoader.load();
            CharlyDialog con = fxmlLoader.getController();

            //add content to basicDialog
            FXMLLoader fxmlLoader2 = new FXMLLoader(getClass().getResource("/Fxml/Admin/Editors/GroupEditor.fxml"));
            VBox vb = fxmlLoader2.load();
            GroupEditorController hed = fxmlLoader2.getController();
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
        } catch (IOException ignored){
        }
        return null;
    }

    public Course CoursePane(Course co) {
        try {
            //load basicDialog
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Fxml/Admin/Editors/BasicDialog.fxml"));
            DialogPane cd = fxmlLoader.load();
            CharlyDialog con = fxmlLoader.getController();

            //add content to basicDialog
            FXMLLoader fxmlLoader2 = new FXMLLoader(getClass().getResource("/Fxml/Admin/Editors/CourseEditor.fxml"));
            VBox vb = fxmlLoader2.load();
            CourseEditorController hed = fxmlLoader2.getController();
            hed.setObject(co);

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
        } catch (IOException ignored){
        }
        return null;
    }
}
