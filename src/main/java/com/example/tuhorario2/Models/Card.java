package com.example.tuhorario2.Models;

import javafx.scene.layout.Pane;

public interface Card<T extends CardObject>{
    public void setObject(T object);
    public void edit();
    public void Update();
    public void delete();
    public void copy();
    public Pane getPane();
}
