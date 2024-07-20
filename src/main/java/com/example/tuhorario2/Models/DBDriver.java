package com.example.tuhorario2.Models;

import java.sql.*;
import java.util.ArrayList;

public class DBDriver {
    Connection c;

    public DBDriver(){
        try{
            this.c = DriverManager.getConnection("jdbc:sqlite:TUHORARIO2BD.db");
            System.out.println("Successful connection");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //function that logins to a USER given its username and password
    public User loginAsUser(String username, String password){
        Statement statement;
        ResultSet result = null;
        try {
            statement = c.createStatement();
            result = statement.executeQuery("SELECT * FROM Users WHERE UserName = '" +username+ "' AND UserPassword = '"+password+"';");
            if (result.isBeforeFirst()){
                int id = Integer.parseInt(result.getString("UserId"));
                return new User(username, id);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Group> getGroups(){
        ArrayList<Group> gs = new ArrayList<>();
        try{
            Statement st = c.createStatement();
            ResultSet result = st.executeQuery("SELECT * FROM Groups");
            while (result.next()){
                int id = result.getInt("GroupID");
                int uid = result.getInt("UserID");
                String color = result.getString("GroupColor");
                String name = result.getString("GroupName");
                int sem = result.getInt("GroupSemester");
                Group g = new Group(id,uid,color,name,(byte) sem);
                g.addCourses(getCourses(id, uid));
                gs.add(g);
            }
            return gs;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return new ArrayList<Group>();
    }


    public ArrayList<Course> getCourses(int groupID, int UserID){
        ArrayList<Course> cs = new ArrayList<>();
        try{
            Statement st = c.createStatement();
            ResultSet result = st.executeQuery("SELECT * FROM Courses WHERE GroupID = " + groupID + ";");
            while (result.next()){
                int id = result.getInt("CourseID");
                String color = result.getString("CourseColor");
                String name = result.getString("CourseName");
                Course g = new Course(id,UserID,color,name);
                g.addOptions(getOptions(id, UserID));
                cs.add(g);
            }
            return cs;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return new ArrayList<Course>();
    }


    public ArrayList<ChoiceOption> getOptions(int courseID, int UserID){
        ArrayList<ChoiceOption> cs = new ArrayList<>();
        try{
            Statement st = c.createStatement();
            ResultSet result = st.executeQuery("SELECT * FROM Options WHERE CourseID = " + courseID + ";");
            while (result.next()){
                int id = result.getInt("CourseID");
                ChoiceOption g = new ChoiceOption(id,UserID);
                g.setHourList(getHours(id));
                g.setDayList(getDays(id));
                g.setLabelList(getLabels(id));
                cs.add(g);
            }
            return cs;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return new ArrayList<ChoiceOption>();
    }


    //returns the days of an option
    public ArrayList<Byte> getDays(int optionID){
        ArrayList<Byte> ds = new ArrayList<>();
        try{
            Statement st = c.createStatement();
            ResultSet result = st.executeQuery("SELECT * FROM DAYS WHERE OptionID = " + optionID + ";");
            while (result.next()){
                byte d = (byte) result.getInt("Day");
                ds.add(d);
            }
            return ds;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return new ArrayList<Byte>();
    }

    //returns the hours of an option
    public ArrayList<byte[]> getHours(int optionID){
        ArrayList<byte[]> ds = new ArrayList<>();
        try{
            Statement st = c.createStatement();
            ResultSet result = st.executeQuery("SELECT * FROM DAYS WHERE OptionID = " + optionID + ";");
            while (result.next()){
                byte bh = (byte) result.getInt("DayStartT");
                byte eh = (byte) result.getInt("DayEndT");
                ds.add(new byte[]{bh,eh});
            }
            return ds;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return new ArrayList<byte[]>();
    }


    //returns the labels given a optionID
    public ArrayList<String> getLabels(int optionID){
        ArrayList<String> s = new ArrayList<>();
        try{
            Statement st = c.createStatement();
            ResultSet result = st.executeQuery("SELECT * FROM LABELS WHERE OptionID = " + optionID + ";");
            while (result.next()){
                String bh = result.getString("LabelText");
                s.add(bh);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return new ArrayList<String>();
    }

    //TODO function that creates a new group

    //TODO function that creates a new course

    //TODO fuction that creates a new option
    // - needs to add all labels to the table as well
    // - also needs to add all the days to the respective table

    //Note MUST CHECK IF THE USER IS THE OWNER
    //TODO function that updates the group
    //TODO function that deletes group

    //Note MUST CHECK IF THE USER IS THE OWNER
    //TODO function that updates course
    //TODO function that delete course

    //Note MUST CHECK IF THE USER IS THE OWNER
    //TODO function that updates the option
    // - must update all labels as well
    // - must update all the days as well

    //TODO function that delete the option
    // - must delete all the label as well
    // - must delete all days as well

    //TODO select functions


}
