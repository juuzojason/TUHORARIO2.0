package com.example.tuhorario2.Controllers.Admin;

import com.example.tuhorario2.Models.Group;
import com.example.tuhorario2.Models.ObjectEditor;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class GroupEditorController implements Initializable, ObjectEditor<Group> {


    public TextField NameTextField;
    public Spinner<Integer> SemesterSpinner;
    public VBox content;

    //TODO FXML
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    @Override
    public VBox getContent() {
        return this.content;
    }

    //TODO implementable METHODS
    @Override
    public void setObject(Group object) {
    }

    @Override
    public Group getObject() {
        return null;
    }

    @Override
    public boolean IsEverythingValid() {
        return false;
    }


}
