package com.example.tuhorario2.Controllers.Admin;

import com.example.tuhorario2.Models.Group;
import com.example.tuhorario2.Models.ObjectEditor;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class GroupEditorController implements Initializable, ObjectEditor<Group> {


    public TextField NameTextField;
    public Spinner<Integer> SemesterSpinner;
    public VBox content;
    public javafx.scene.control.ColorPicker ColorPicker;
    private SpinnerValueFactory<Integer> valueFactory;

    //TODO FXML
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,32);
        valueFactory.setValue(1);
        SemesterSpinner.setValueFactory(valueFactory);
    }


    @Override
    public VBox getContent() {
        return this.content;
    }

    //TODO implementable METHODS
    @Override
    public void setObject(Group object) {
        if (object == null) return;
        NameTextField.setText(object.getName());
        valueFactory.setValue((int) object.getSemester());
        ColorPicker.setValue(Color.valueOf(object.getColor()));
    }

    @Override
    public Group getObject() {
        return new Group("#"+ColorPicker.getValue().toString().substring(2,8),NameTextField.getText(),SemesterSpinner.getValue().byteValue());
    }

    @Override
    public boolean IsEverythingValid() {
        return NameTextField.getText().isEmpty();
    }


}
