package com.example.tuhorario2.Models;

import com.example.tuhorario2.Controllers.User.GroupCardController;
import com.example.tuhorario2.Controllers.User.UserController;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.ArrayList;

public class Group implements CardObject {

    //the id used to update and delete
    private int id = -1;
    private int uid;
    private String color = "#000000";
    private String name;
    private byte semester;


    private GroupCardController cardController;
    private Pane card;

    private ArrayList<Course> courses = new ArrayList<>();

    public Group(int id,int uid, String color, String name, byte semester){
        this.id = id;
        this.uid = uid;
        this.color = color;
        this.name = name;
        this.semester = semester;
    }

    public Group(String color, String name, byte semester){
        this.color = color;
        this.name = name;
        this.semester = semester;
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

    public void addCourse(Course e) {
        this.courses.add(e);
    }

    public void addCourses(ArrayList<Course> courses){
        this.courses.addAll(courses);
    }


    public void removeCourse(Course e) {
        this.courses.remove(e);
    }

    // Optional: If you want to remove an option by index
    public void removeOption(int index) {
        if (index >= 0 && index < courses.size()) {
            courses.remove(index);
        }
    }

    // Optional: If you want to find an option by its properties before removing
    public int getCourseAmount() {
        return courses.size();
    }

    public ArrayList<Course> getCourses() {
        return this.courses;
    }

//    // Finds all options that have a specific day in their dayList ;)
//    public List<ChoiceOption> findOptionsByDay(byte day) {
//        List<ChoiceOption> result = new ArrayList<>();
//        for (Course option : courses) {
//            if (option.getDayList().contains(day)) {
//                result.add(option);
//            }
//        }
//        return result;
//    }


    //this function is going to be used to generate all possible schedules NOTE: for it to work you have to call like this (null,0,yourConstraintObject)
    public ArrayList<ArrayList<ChoiceOption>> ScheduleList(ArrayList<ChoiceOption> scs, int nCourse, ScheduleConstrains constrains){
        if (scs == null) scs = new ArrayList<>();
        ArrayList<ArrayList<ChoiceOption>> schedules = new ArrayList<>();

        Course c = courses.get(nCourse);
        for (ChoiceOption co : c.getChoiceOptions()){
            //verifies if it collides with any of the chosen options
            boolean colides = false;
            for (ChoiceOption coChosen : scs){
                if (coChosen.crossesWith(co)){
                    colides = true;
                    break;
                }
            }
            //Avoid Collision
            if (colides) continue;

            //Add the option to the stack
            scs.add(co);
            //if the option doesn't match constraints then just remove and continues
            if (!constrains.matchesConstraints(scs)){
                scs.remove(co);
                continue;
            }
            //if is the last course we add a copy of the List of options to the return array
            if (nCourse == getCourseAmount() - 1){
                schedules.add((ArrayList<ChoiceOption>) scs.clone());
            } else { //otherwise we add all schedules what can be created with our current choices
                schedules.addAll(ScheduleList(scs, nCourse+1, constrains));
            }
            //We remove the option of the stack and then continue with the next option
            scs.remove(co);
        }
        return schedules;
    }



    public Pane getCard(){
        return this.card;
    }

    //TODO create card
    @Override
    public void createCard() {
        if (card == null){
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Fxml/User/GroupCard.fxml"));
                card = fxmlLoader.load();
                this.cardController = fxmlLoader.getController();
                this.cardController.setObject(this);
                this.cardController.Update();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }



    //TODO copy must create an exact copy of this in the database
    @Override
    public void copy() {

    }
    public int getuserid(){
        return this.uid;
    }

    @Override
    public void delete(){
        Model.getInstance().deleteGroup(this);
    }


    public String getColor() {
        return this.color;
    }

    public void setUID(int uid){
        this.uid = uid;
    }

    public void setColor(String s) {
        this.color = s;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

