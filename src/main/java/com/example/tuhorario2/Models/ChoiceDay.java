package com.example.tuhorario2.Models;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ChoiceDay {

    public static final Map<String, String[]> days = Map.of("Eng", new String[]{"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"});

    public static String[] getWeekDays(){
        return days.get("Eng");
    }

    public static String DayOf(byte n){
        return days.get("Eng")[n];
    }


    public static int NumOf(String s){
        return Arrays.asList(days.get("Eng")).indexOf(s);
    }

}
