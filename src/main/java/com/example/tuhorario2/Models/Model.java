package com.example.tuhorario2.Models;

import com.example.tuhorario2.Views.ViewFactory;

import java.util.ArrayList;

public class Model {
    private User user = null;
    private static Model model;
    private final ViewFactory viewFactory;
    private final DBDriver dbDriver;

    private final ArrayList<Group> generalGroupList;
    private Model(){
        viewFactory = new ViewFactory();
        dbDriver = new DBDriver();
        generalGroupList = dbDriver.getGroups();
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

    //TODO logout should:
    // - set user null
    // - change view to login
    // - delete all Cards
    public void LogOut(){

    }


    public User getUser(){
        return this.user;
    }

    public DBDriver getDbDriver() {
        return dbDriver;
    }

    public void deleteGroup(Group group) {
        this.generalGroupList.remove(group);
    }

    public ArrayList<Group> getGeneralGroupList() {
        return generalGroupList;
    }
}
