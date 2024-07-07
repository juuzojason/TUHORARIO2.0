package com.example.tuhorario2.Models;

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
    private ArrayList<Pair<ChoiceHour,ChoiceHour>> hourList;

    //TODO method equals
    //returns: true or false, does not compare labels

    //TODO method add day
    //Do: adds the day and the begin-end hour

    //TODO method remove day
    //Do: remove both the day and the begin-end hour by the index in the array

    //TODO method crosses with choiceOption
    //Do: when a day and a begin-end hour crosses or matches with any of this's
    //Returns: true or false, matches or not

    //TODO Encript method

    //TODO List of dayOptions in a string kind of a toString

    //TODO hasLabel tells you if this has a label

}
