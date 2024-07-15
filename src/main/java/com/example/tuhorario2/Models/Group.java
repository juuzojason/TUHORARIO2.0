package com.example.tuhorario2.Models;

import java.util.ArrayList;
import java.util.Optional;
import java.util.List;

public class Group {

    private String name;
    private byte semester;

    private ArrayList<ChoiceOption> options;

    public Group(String name, byte s){
        this.name = name;
        this.semester = s;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSemester(byte semester) {
        this.semester = semester;
    }

    public byte getSemester() {
        return semester;
    }

    public String getName() {
        return name;
    }

    public void addOption(ChoiceOption e){
        this.options.add(e);
    }

    public void removeOption(ChoiceOption e) {
        this.options.remove(e);
    }

    // Optional: If you want to remove an option by index
    public void removeOption(int index) {
        if (index >= 0 && index < options.size()) {
            options.remove(index);
        }
    }

    // Optional: If you want to find an option by its properties before removing
    public boolean removeOption(String label) {
        for (ChoiceOption option : options) {
            if (option.hasLabel(label)) {
                return options.remove(option);
            }
        }
        return false;
    }

    // Finds all options that have a specific day in their dayList ;)
    public List<ChoiceOption> findOptionsByDay(byte day) {
        List<ChoiceOption> result = new ArrayList<>();
        for (ChoiceOption option : options) {
            if (option.getDayList().contains(day)) {
                result.add(option);
            }
        }
        return result;
    }

    // Finds all options that have a specific label in their labelList :D
    public List<ChoiceOption> findOptionsByLabel(String label) {
        List<ChoiceOption> result = new ArrayList<>();
        for (ChoiceOption option : options) {
            if (option.hasLabel(label)) {
                result.add(option);
            }
        }
        return result;
    }



}
