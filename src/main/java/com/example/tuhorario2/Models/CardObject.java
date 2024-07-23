package com.example.tuhorario2.Models;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;

public interface CardObject {

    public void createCard();
    public Pane getCard();
    public Object copy();
    public void delete();


    public void readFormat();
    public String writeFormat();
}
