package com.example.tuhorario2.Controllers.Admin;

import com.example.tuhorario2.Models.Course;
import com.example.tuhorario2.Models.ObjectEditor;
import javafx.fxml.Initializable;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class CourseEditorController implements Initializable, ObjectEditor<Course> {

    public Course cardObject;
    public TextField NameTextField;
    public ColorPicker ColorPicker;
    public VBox ContentVBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @Override
    public VBox getContent() {
        return ContentVBox;
    }

    @Override
    public void setObject(Course object) {
        if (object == null) return;
        this.NameTextField.setText(object.getName());
        String col = object.getColor();
        this.ColorPicker.setValue(Color.valueOf(col));
    }

    @Override
    public Course getObject() {
        return new Course("#"+ColorPicker.getValue().toString().substring(2,8),NameTextField.getText());
    }

    @Override
    public boolean IsEverythingValid() {
        return NameTextField.getText().isEmpty();
    }
}
