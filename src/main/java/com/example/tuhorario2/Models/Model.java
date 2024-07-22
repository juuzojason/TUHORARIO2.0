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

    //TODO: we have to register a new person, but we first have to make sure there is not another person with the same username
    // - we also need to return if the registration was successful or not
    public boolean Register() {
        return false;
    }



    public ViewFactory getViewFactory() {
        return viewFactory;
    }


    public User loginAsUser(String username, String password) {
        this.user = dbDriver.loginAsUser(username,password);
        return this.user;
    }


    public User getUser(){
        return this.user;
    }

    public DBDriver getDbDriver() {
        return dbDriver;
    }
}
