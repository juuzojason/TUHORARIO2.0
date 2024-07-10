package com.example.tuhorario2.Models;

import java.util.ArrayList;
import java.util.List;

public class course {

    private String name;
    private String ID;
    private int days;
    private List<ChoiceOption> choiceOptions;

    public course(String name, int days) {
        this.name = name;
        this.days = days;
        this.choiceOptions = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Course Name: ").append(name).append("\n");
        sb.append("Course ID: ").append(ID).append("\n");
        sb.append("Number of Days: ").append(days).append("\n");
        sb.append("Choice Options:\n");
        for (ChoiceOption option : choiceOptions) {
            sb.append(option.toString()).append("\n");
        }
        return sb.toString();
    }
}
