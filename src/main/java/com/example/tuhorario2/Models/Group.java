package com.example.tuhorario2.Models;

import java.util.ArrayList;
import java.util.Optional;

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

}
