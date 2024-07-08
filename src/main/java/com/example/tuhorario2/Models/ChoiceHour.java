package com.example.tuhorario2.Models;

public class ChoiceHour {
    //the 8-bit data use to store the hour and the minute
    private byte data;


    public ChoiceHour(int hour, int minute){
        if (minute > 45) minute = 45;
        if (minute < 0) minute = 0;
        minute = (minute / 15);

        if (hour > 21) hour = 21;
        if (hour < 6) hour = 6;
        hour = (hour - 6);

        data += (byte) hour;
        data = (byte) (data << 2);
        data += (byte) minute;
    }

    public ChoiceHour(byte data){
        this.data = data;
    }
    //TODO necesary constructors

    //TODO method return the hour in 24h format 00:00
    @Override
    public String toString(){return "";}

    public static String toString(byte data){
        return getHour(data) + ":" + getMinute(data);
    }

    //TODO method
    //Do: is the value b less than this
    //please tell me there is another way, like a interface or something for now we are using this thing
    public boolean isGreaterThan(ChoiceHour b){return true;}


    //TODO method get Hour
    public int getHour(){
        return getHour(this.data);
    }
    public static int getHour(byte data){
        return (data >> 2) + 6;
    }

    //TODO method get Minute
    public int getMinute(){
        return getMinute(this.data);
    }

    public static int getMinute(byte data){
        return (data % 4) * 15;
    }

    public byte getData(){
        return this.data;
    }


    //TODO method set Hour

    //TODO method set Minute

}
