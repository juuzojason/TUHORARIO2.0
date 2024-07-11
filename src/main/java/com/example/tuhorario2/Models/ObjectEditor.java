package com.example.tuhorario2.Models;

import javafx.scene.layout.VBox;

public interface ObjectEditor<T>{
    public VBox getContent();
    public void setObject(T object);
    public T getObject();
    public boolean IsEverythingValid();
}
