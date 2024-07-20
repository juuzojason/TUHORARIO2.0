package com.example.tuhorario2.Models;

import com.example.tuhorario2.Views.ViewFactory;

import java.util.ArrayList;

public class Model {
    private User user = null;
    private static Model model;
    private final ViewFactory viewFactory;
    private final DBDriver dbDriver;

    private ArrayList<Group> groups;

    private Model(){
        viewFactory = new ViewFactory();
        dbDriver = new DBDriver();
    }


    public static synchronized Model getInstance(){
        if (model == null){
            model = new Model();
        }
        return model;
    }

    public ViewFactory getViewFactory() {
        return viewFactory;
    }

    public void Login(){

    }


}
