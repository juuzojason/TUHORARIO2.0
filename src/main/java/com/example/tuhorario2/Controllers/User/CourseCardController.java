package com.example.tuhorario2.Controllers.User;

import com.example.tuhorario2.Models.Card;
import com.example.tuhorario2.Models.Course;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class CourseCardController implements Initializable, Card<Course> {
    public Pane ColorPaneDec;
    public Button EditButton;
    public Button DeleteButton;
    public Label NameLabel;
    public Label UserLabel;
    public Label InfoLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @Override
    public void connect(Course connector) {

    }

    @Override
    public void updateVisual() {

    }
}
