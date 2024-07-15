package com.example.tuhorario2.Models;

import java.util.ArrayList;
import java.util.Optional;
import java.util.List;
import java.util.function.Function;

public class Group {

    private String name;
    private byte semester;
    private String user;

    private ArrayList<Course> courses;

    public Group(String name, byte s) {
        this.name = name;
        this.semester = s;
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
            Function<ArrayList<ChoiceOption>,Double> fun = ScheduleConstrains::ScoreOf;
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
}