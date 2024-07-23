package com.example.tuhorario2.Models;

import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

public class Course implements CardObject {

    //the id used to update and delete
    private int id;
    private int uid;
    private String Color = "#000000";
    private String name;

    private List<ChoiceOption> choiceOptions;


    //private CourseCardController card;


    public Course(int id, int uid, String color, String name) {
        this.id = id;
        this.uid = uid;
        this.Color = color;
        this.name = name;
        this.choiceOptions = new ArrayList<>();
    }

    public Course(String color, String name) {
        this.Color = color;
        this.name = name;
        this.choiceOptions = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ChoiceOption> getChoiceOptions() {
        return choiceOptions;
    }

    public void setChoiceOptions(List<ChoiceOption> choiceOptions) {
        this.choiceOptions = choiceOptions;
    }

    public void addChoiceOption(ChoiceOption choiceOption) {
        choiceOptions.add(choiceOption);
    }

    public void removeChoiceOption(ChoiceOption choiceOption) {
        choiceOptions.remove(choiceOption);
    }

    public void addOptions(ArrayList<ChoiceOption> options){
        this.choiceOptions.addAll(options);
    }

    public String getColor(){
        return this.Color;
    }

    public List<ChoiceOption> findOptionsByLabel(String label) {
        List<ChoiceOption> result = new ArrayList<>();
        for (ChoiceOption option : choiceOptions) {
            if (option.hasLabel(label)) {
                result.add(option);
            }
        }
        return result;
    }


    public int getUid() {
        return uid;
    }

    @Override
    public String toString(){
        return "Course{" +
                "id=" + id +
                ", uid=" + uid +
                ", Color='" + Color + '\'' +
                ", name='" + name + '\'' +
                ", choiceOptions=" + choiceOptions +
                '}';

    }



    //TODO create this method like in Group
    @Override
    public void createCard() {
    }


    //TODO recreate this method like in Group
    @Override
    public Pane getCard() {
        return null;
    }


    //this function copies this course and returns the object
    @Override
    public Course copy() {
        Course c = new Course(Color,name);
        c.setUID(Model.getInstance().getUser().getId());
        return c;
    }

    private void setUID(int id) {
        this.uid = id;
    }


    //TODO exactly how it is in Group
    @Override
    public void delete() {

    }

    @Override
    public void readFormat() {

    }

    @Override
    public String writeFormat() {
        return "";
    }
}
