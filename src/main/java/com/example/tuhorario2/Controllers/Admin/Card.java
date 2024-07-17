package com.example.tuhorario2.Controllers.Admin;

public interface Card<T extends CardObject>{
    public void connect(T connector);
    public void updateVisual();

}
