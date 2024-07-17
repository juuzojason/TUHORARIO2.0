package com.example.tuhorario2.Models;

import java.util.ArrayList;
import java.util.function.Function;

public class ScheduleConstrains {

    //Score functions defines a way to give a different score function to all property of a schedule
    //each function is discussed, the function doesn't have to be linear
    public static ArrayList<Function<Double, Double>> scoreFunctions = new ArrayList<>();

    static {
        scoreFunctions.add(x -> 10 - Math.log10(x)); //first one maxHour
        scoreFunctions.add(Math::exp); //second one minHour
        scoreFunctions.add(x -> 70 / x); //third one amount of days
        scoreFunctions.add(Math::log); //fourth the amount of favorite labels
        scoreFunctions.add(x -> x + 1); //fifth the maxGap
        scoreFunctions.add(x -> x / 3.0); //sixth the maxGapPerWeek
    }

    private byte[] HourRangeConstraint = new byte[2]; //Min and Max hour you want class
    //it's an array with two bytes one for the minHour you want and other for the maxTime you want
    private boolean[] DaysConstraint = new boolean[6]; //days ignored while choosing
    //this is a 6 bool array where each position represents if you dislike a schedule with that day
    private ArrayList<String> LabelConstraint = new ArrayList<>();
    //represents all labels you want to avoid
    private ArrayList<String> FavoriteLabels = new ArrayList<>();
    //this represents your favorite labels, it determines the score of a schedule
    private byte MaxGapTime; //Max gap between all days
    private byte MaxGapsPerWeek; //The sum of the max gap of all days
    private byte MaxDays; //max amount of days you want


    public void setMaxGapsPerWeek(byte maxGapsPerWeek) {
        MaxGapsPerWeek = maxGapsPerWeek;
    }

    public byte getMaxGapsPerWeek() {
        return MaxGapsPerWeek;
    }

    public void setMaxGapTime(byte maxGapTime) {
        MaxGapTime = maxGapTime;
    }

    public byte getMaxGapTime() {
        return MaxGapTime;
    }

    public void setMaxDays(byte maxDays) {
        MaxDays = maxDays;
    }

    public byte getMaxDays() {
        return MaxDays;
    }

    public void setDaysConstraint(boolean[] daysConstraint) {
        DaysConstraint = daysConstraint;
    }

    public void addLabelConstraint(String s){
        LabelConstraint.add(s);
    }

    public void removeLabelConstraint(String s){
        LabelConstraint.remove(s);
    }


    public void addFavoriteLabel(String s){
        FavoriteLabels.add(s);
    }

    public void removeFavoriteLabel(String s){
        FavoriteLabels.remove(s);
    }


    public void setMinHour(byte b){
        HourRangeConstraint[0] = b;
    }
    public void setMaxHour(byte b){
        HourRangeConstraint[1] = b;
    }

    //TODO sets all the constraint to a default value;
    public void setDefaultConstraints(){
    }

    //TODO returns the favorite labels that the schedule has
    public ArrayList<String> Favorites(ArrayList<ChoiceOption> schedule){
        return null;
    }

    //TODO returns the sum of the max Gap of each day
    public static byte gapsPerWeek(ArrayList<ChoiceOption> schedule){
        return 0;
    }

    //TODO returns the max Gap between all days
    public static byte maxGap(ArrayList<ChoiceOption> schedule){
        return 0;
    }

    //TODO returns the range of hour of the schedule (max and min hour)
    public static byte[] hourRange(ArrayList<ChoiceOption> schedule){
        return null;
    }

    //TODO returns the amount of days of a schedule which is just a list of options
    public static byte amountDaysOf(ArrayList<ChoiceOption> schedule){
        return 0;
    }

    //TODO function scoreOf: gives you the score of a schedule (which is just a list of options) using the scoreFunctions
    //this is going to be used for choosing the recommended schedule
    public static double ScoreOf(ArrayList<ChoiceOption> schedule){
        double score = 0;
        // add all score functions results
        return score;
    }

    //TODO function matchesConstraints: tells you if is a valid schedule or not
    //this will be used for checking when a option can be added or not
    public boolean matchesConstraints(ArrayList<ChoiceOption> schedule){
        //checks the HourRangeConstraint

        //checks the DaysConstraint

        //checks the LabelConstraint

        //checks the MaxGap constraint

        //checks the MaxGapsPerWeek constraint

        //check the MaxDay constraint
        return true;
    }

}
