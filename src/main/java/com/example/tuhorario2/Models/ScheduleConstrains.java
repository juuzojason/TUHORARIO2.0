package com.example.tuhorario2.Models;

import java.util.ArrayList;
import java.util.function.Function;
import javafx.util.Pair;

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


    public void setDefaultConstraints() {
        HourRangeConstraint[0] = 6; // Default min hour
        HourRangeConstraint[1] = 21; // Default max hour
        for (int i = 0; i < DaysConstraint.length; i++) {
            DaysConstraint[i] = false; // No day constraints by default
        }
        LabelConstraint.clear(); // No label constraints by default
        FavoriteLabels.clear(); // No favorite labels by default
        MaxGapTime = 0; // Default max gap
        MaxGapsPerWeek = 0; // Default max gaps per week
        MaxDays = 5; // Default max days
    }


    public ArrayList<String> Favorites(ArrayList<ChoiceOption> schedule) {
        ArrayList<String> favorites = new ArrayList<>();
        for (ChoiceOption option : schedule) {
            for (String label : option.getLabelList()) {
                if (FavoriteLabels.contains(label) && !favorites.contains(label)) {
                    favorites.add(label);
                }
            }
        }
        return favorites;
    }




    public byte gapsPerWeek(ArrayList<ChoiceOption> schedule) {
        byte totalGaps = 0;
        for (ChoiceOption option : schedule) {
            for (byte[] hourPair : option.getHourList()) {
                byte gap = (byte) (hourPair[1] - hourPair[0]);
                totalGaps += gap;
            }
        }
        return totalGaps;
    }


    public byte maxGap(ArrayList<ChoiceOption> schedule) {
        byte maxGap = 0;
        for (ChoiceOption option : schedule) {
            for (byte[] hourPair : option.getHourList()) {
                byte gap = (byte) (hourPair[1] - hourPair[0]);
                if (gap > maxGap) {
                    maxGap = gap;
                }
            }
        }
        return maxGap;
    }


    public byte[] hourRange(ArrayList<ChoiceOption> schedule) {
        byte minHour = 21;
        byte maxHour = 6;
        for (ChoiceOption option : schedule) {
            for (byte[] hourPair : option.getHourList()) {
                if (hourPair[0] < minHour) {
                    minHour = hourPair[0];
                }
                if (hourPair[1] > maxHour) {
                    maxHour = hourPair[1];
                }
            }
        }
        return new byte[]{minHour, maxHour};
    }


    public byte amountDaysOf(ArrayList<ChoiceOption> schedule) {
        boolean[] daysPresent = new boolean[6];
        for (ChoiceOption option : schedule) {
            for (byte day : option.getDayList()) {
                daysPresent[day] = true;
            }
        }
        byte count = 0;
        for (boolean day : daysPresent) {
            if (day) {
                count++;
            }
        }
        return count;
    }



    public static double ScoreOf(ArrayList<ChoiceOption> schedule){
        ScheduleConstrains constraints = new ScheduleConstrains();

        // Calculate the distance between max and min hour
        byte[] hourRange = constraints.hourRange(schedule);
        double maxHour = hourRange[1];
        double minHour = hourRange[0];
        double hourDistance = maxHour - minHour;

        // Calculate the amount of days
        double days = constraints.amountDaysOf(schedule);

        // Calculate the max gap in a day
        double maxGap = constraints.maxGap(schedule);

        double score = 0;
        // Distance between max and min hour, less distance higher score
        score += 100 - hourDistance;

        // Amount of days, fewer days higher score
        score += 100 - days;

        // Max gap in a day, less gap higher score
        score += 100 - maxGap;

        return score;
    }


    public boolean matchesConstraints(ArrayList<ChoiceOption> schedule) {
        byte[] range = hourRange(schedule);
        if (range[0] < HourRangeConstraint[0] || range[1] > HourRangeConstraint[1]) {
            return false;}

        for (ChoiceOption option : schedule) {
            for (byte day : option.getDayList()) {
                if (DaysConstraint[day]) {
                    return false;
                }}}

        for (ChoiceOption option : schedule) {
            for (String label : option.getLabelList()) {
                if (LabelConstraint.contains(label)) {
                    return false;
                }}}

        if (maxGap(schedule) > MaxGapTime) {
            return false;}
        if (gapsPerWeek(schedule) > MaxGapsPerWeek) {
            return false;}
        if (amountDaysOf(schedule) > MaxDays) {
            return false;}
        return true;}
    }