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


    @Override
    public String toString(){return "";}

    public static String toString(byte data){
        return getHour(data) + ":" + getMinute(data);
    }


    //Do: is the value b less than this
    //please tell me there is another way, like a interface or something for now we are using this thing
    public boolean isGreaterThan(ChoiceHour b) {
        return this.data > b.data;
    }



    public int getHour(){
        return getHour(this.data);
    }
    public static int getHour(byte data){
        return (data >> 2) + 6;
    }


    public int getMinute(){
        return getMinute(this.data);
    }

    public static int getMinute(byte data){
        return (data % 4) * 15;
    }

    public byte getData(){
        return this.data;
    }



    public void setHour(int hour){
        if (hour > 21) hour =21;
        if (hour < 6) hour = 6;

        byte minute = (byte) (data % 4);
        data = (byte) ((hour << 2 ) + minute);
    }


    public void setMinute(int minute) {
        if (minute > 45) minute = 45;
        if (minute < 0) minute = 0;
        minute = (minute / 15);

        int hour = (data >> 2);
        data = (byte) ((hour << 2) + minute);
    }

}
