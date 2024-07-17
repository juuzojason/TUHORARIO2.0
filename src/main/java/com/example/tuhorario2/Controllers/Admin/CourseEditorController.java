package com.example.tuhorario2.Controllers.Admin;

import com.example.tuhorario2.Models.Course;
import com.example.tuhorario2.Models.ObjectEditor;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class CourseEditorController implements Initializable, ObjectEditor<Course> {
    //TODO FXML
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
    //TODO METHODS

    @Override
    public VBox getContent() {
        return null;
    }

    @Override
    public void setObject(Course object) {

    }

    @Override
    public Course getObject() {
        return null;
    }

    @Override
    public boolean IsEverythingValid() {
        return false;
    }

}
