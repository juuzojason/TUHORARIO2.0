package com.example.tuhorario2.Models;

import javafx.scene.control.Label;
import javafx.util.Pair;
import java.util.ArrayList;

public class ChoiceOption {

    public static byte NormalDay = 0;
    public static byte NormalBeginHour = 0;
    public static byte NormalEndHour = 10;
    //List of labels
    private ArrayList<String> labelList;
    //List of Days
    private ArrayList<Byte> dayList;
    //List of begin and end hours
    private ArrayList<Pair<Byte,Byte>> hourList;


    public ChoiceOption (){
        labelList = new ArrayList<>();
        dayList = new ArrayList<>();
        hourList = new ArrayList<>();
    }

    public ArrayList<String> getLabelList() {
        return labelList;
    }

    public ArrayList<Byte> getDayList() {
        return dayList;
    }

    public ArrayList<Pair<Byte, Byte>> getHourList() {
        return hourList;
    }

    //TODO method equals
    //returns: true or false, does not compare labels

    //DONETODO method add day
    //Do: adds the day and the begin-end hour
    public void addDay(byte day, byte bhour, byte ehour){
        dayList.add(day);
        hourList.add(new Pair<>(bhour,ehour));
    }

    //DONETODO method remove day
    //Do: remove both the day and the begin-end hour by the index in the array
    public void removeDay(int i){
        dayList.remove(i);
        hourList.remove(i);
    }


    public void addLabel(String s){
        this.labelList.add(s);
    }

    //TODO method crosses with choiceOption
    //Do: when a day and a begin-end hour crosses or matches with any of this's
    //Returns: true or false, matches or not



    //TODO Encript method

    //TODO List of dayOptions in a string kind of a toString

    //TODO hasLabel tells you if this has a label

}
