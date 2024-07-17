package com.example.tuhorario2.Models;

import javafx.scene.control.Label;
import javafx.util.Pair;
import java.util.ArrayList;

public class ChoiceOption {

    public static byte NormalDay = 0;
    public static byte NormalBeginHour = 0;
    public static byte NormalEndHour = 10;


    private boolean active;

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
        active = true;

    }

    public boolean isActive(){
        return active;
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

    public void setActive(boolean active){
        this.active = active;

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


    //Do: when a day and a begin-end hour crosses or matches with any of this's
    //Returns: true or false, matches or not
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        ChoiceOption that = (ChoiceOption) obj;

        return dayList.equals(that.dayList) && hourList.equals(that.hourList);
    }


    public boolean crossesWith(ChoiceOption other) {
        for (int i = 0; i < this.dayList.size(); i++) {
            byte thisDay = this.dayList.get(i);
            Pair<Byte, Byte> thisHourPair = this.hourList.get(i);
            for (int j = 0; j < other.dayList.size(); j++) {
                byte otherDay = other.dayList.get(j);
                Pair<Byte, Byte> otherHourPair = other.hourList.get(j);
                if (thisDay == otherDay) {
                    byte thisBegin = thisHourPair.getKey();
                    byte thisEnd = thisHourPair.getValue();
                    byte otherBegin = otherHourPair.getKey();
                    byte otherEnd = otherHourPair.getValue();
                    if ((otherBegin < thisEnd && otherEnd > thisBegin)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }



    //TODO Encript method
    public String encript() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < dayList.size(); i++) {
            sb.append(dayList.get(i)).append(",").append(hourList.get(i).getKey())
                    .append(",").append(hourList.get(i).getValue()).append(";");
        }
        return sb.toString();
    }



    //TODO List of dayOptions in a string kind of a toString
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Labels: ").append(labelList.toString()).append("\n");
        sb.append("Days and Hours:\n");
        for (int i = 0; i < dayList.size(); i++) {
            sb.append("Day: ").append(dayList.get(i)).append(", Begin: ")
                    .append(hourList.get(i).getKey()).append(", End: ")
                    .append(hourList.get(i).getValue()).append("\n");
        }
        sb.append("Active: ").append(active).append("\n");
        return sb.toString();
    }



    //TODO hasLabel tells you if this has a label
    public boolean hasLabel(String label) {
        return labelList.contains(label);
    }

}
