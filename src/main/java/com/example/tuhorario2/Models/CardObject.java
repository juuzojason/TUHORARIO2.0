package com.example.tuhorario2.Models;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;

public interface CardObject {

    public void createCard();
    public Pane getCard();
    public void copy();
    public void delete();
}
