package com.example.tuhorario2.Models;

public interface Card<T extends CardObject>{
    public void connect(T connector);
    public void updateVisual();

}
