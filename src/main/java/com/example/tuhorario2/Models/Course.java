package com.example.tuhorario2.Models;

import com.example.tuhorario2.Controllers.Admin.CardObject;

import java.util.ArrayList;
import java.util.List;

public class Course implements CardObject {

    //the id used to update and delete
    private int id;
    private String user = "";
    private String Color = "#000000";
    private String name;

    private List<ChoiceOption> choiceOptions;


    //private CourseCardController card;


    public Course(String name, int days) {
        this.name = name;
        this.choiceOptions = new ArrayList<>();
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

    public List<ChoiceOption> findOptionsByLabel(String label) {
        List<ChoiceOption> result = new ArrayList<>();
        for (ChoiceOption option : choiceOptions) {
            if (option.hasLabel(label)) {
                result.add(option);
            }
        }
        return result;
    }


    @Override
    public void read(String json) {

    }

    @Override
    public void createCard() {

    }

    @Override
    public void updateCard() {

    }
}
