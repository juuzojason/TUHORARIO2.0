package com.example.tuhorario2.Models;

import com.example.tuhorario2.Controllers.User.OptionCardController;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.ArrayList;

public class ChoiceOption implements CardObject{

    public static byte NormalDay = 0;
    public static byte NormalBeginHour = 0;
    public static byte NormalEndHour = 10;


    private boolean active;

    //the id used to update and delete
    private int id = -1;
    private int uid;
    //List of labels
    private ArrayList<String> labelList;
    //List of Days
    private ArrayList<Byte> dayList;
    //List of begin and end hours
    private ArrayList<byte[]> hourList;


    private Pane card;
    private OptionCardController cardController;



    public ChoiceOption (int id, int uid){
        this.id = id;
        this.uid = uid;
        labelList = new ArrayList<>();
        dayList = new ArrayList<>();
        hourList = new ArrayList<>();
        active = true;

    }

    public ChoiceOption (){
        labelList = new ArrayList<>();
        dayList = new ArrayList<>();
        hourList = new ArrayList<>();
        active = true;

    }

    public int getId() {
        return id;
    }

    public int getUid() {
        return uid;
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

    public ArrayList<byte[]> getHourList() {
        return hourList;
    }

    public void setLabelList(ArrayList<String> labelList) {
        this.labelList = labelList;
    }

    public void setDayList(ArrayList<Byte> dayList) {
        this.dayList = dayList;
    }

    public void setHourList(ArrayList<byte[]> hourList) {
        this.hourList = hourList;
    }

    public void setActive(boolean active){
        this.active = active;
    }



    //Method add day
    //Do: adds the day and the begin-end hour
    public void addDay(byte day, byte bhour, byte ehour){
        dayList.add(day);
        hourList.add(new byte[]{bhour,ehour});
    }

    //Method remove day
    //Do: remove both the day and the begin-end hour by the index in the array
    public void removeDay(int i){
        dayList.remove(i);
        hourList.remove(i);
    }


    public void addLabel(String s){
        this.labelList.add(s);
    }



    //Method equals
    //returns: true or false, does not compare labels
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        ChoiceOption that = (ChoiceOption) obj;

        return dayList.equals(that.dayList) && hourList.equals(that.hourList);
    }

    //Crosses with choiceOption
    //Do: when a day and a begin-end hour crosses or matches with any of this's
    //Returns: true or false, matches or not
    public boolean crossesWith(ChoiceOption other) {
        for (int i = 0; i < this.dayList.size(); i++) {
            byte thisDay = this.dayList.get(i);
            byte[] thisHourPair = this.hourList.get(i);
            for (int j = 0; j < other.dayList.size(); j++) {
                byte otherDay = other.dayList.get(j);
                byte[] otherHourPair = other.hourList.get(j);
                if (thisDay == otherDay) {
                    byte thisBegin = thisHourPair[0];
                    byte thisEnd = thisHourPair[1];
                    byte otherBegin = otherHourPair[0];
                    byte otherEnd = otherHourPair[1];
                    if ((thisBegin < otherEnd && thisEnd > otherBegin) ||
                            (otherBegin < thisEnd && otherEnd > thisBegin)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }



    public String encript() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < dayList.size(); i++) {
            sb.append(dayList.get(i)).append(",").append(hourList.get(i)[0])
                    .append(",").append(hourList.get(i)[1]).append(";");
        }
        return sb.toString();
    }



    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Labels: ").append(labelList.toString()).append("\n");
        sb.append("Days and Hours:\n");
        for (int i = 0; i < dayList.size(); i++) {
            sb.append("Day: ").append(dayList.get(i)).append(", Begin: ")
                    .append(hourList.get(i)[0]).append(", End: ")
                    .append(hourList.get(i)[1]).append("\n");
        }
        return sb.toString();
    }



    //TODO hasLabel tells you if this has a label similar to the text, has to be changed
    public boolean hasLabel(String label) {
        return labelList.contains(label);
    }


    public void setId(int id) {
        this.id = id;
    }

    @Override
    public void createCard() {
        if (card == null){
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Fxml/User/OptionCard.fxml"));
                card = fxmlLoader.load();
                this.cardController = fxmlLoader.getController();
                this.cardController.setObject(this);
                this.cardController.Update();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public Pane getCard() {
        return card;
    }

    @Override
    public ChoiceOption copy() {
        ChoiceOption cop = new ChoiceOption();
        cop.setHourList((ArrayList<byte[]>) hourList.clone());
        cop.setDayList((ArrayList<Byte>) dayList.clone());
        cop.setLabelList((ArrayList<String>) labelList.clone());
        return cop;
    }

    @Override
    public void delete() {
        Model.getInstance().deleteOption(this);
    }

    @Override
    public void readFormat() {
    }

    @Override
    public String writeFormat() {
        return "";
    }
}
