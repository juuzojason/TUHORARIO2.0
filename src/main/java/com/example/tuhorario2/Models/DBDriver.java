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


    public boolean createGroup(int userID, String color, String name, int semester) {
        String sql = "INSERT INTO Groups (UserID, GroupColor, GroupName, GroupSemester) VALUES (?, ?, ?, ?)";
        try (PreparedStatement group = c.prepareStatement(sql)) {
            group.setInt(1, userID);
            group.setString(2, color);
            group.setString(3, name);
            group.setInt(4, semester);
            int affectedRows = group.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }



    public boolean createCourse(int groupID, int userID, String color, String name) {
        String sql = "INSERT INTO Courses (GroupID, UserID, CourseColor, CourseName) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = c.prepareStatement(sql)) {
            pstmt.setInt(1, groupID);
            pstmt.setInt(2, userID);
            pstmt.setString(3, color);
            pstmt.setString(4, name);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    //TODO fuction that creates a new option
    // - needs to add all labels to the table as well
    // - also needs to add all the days to the respective table


    public boolean updateGroup(int groupID, int userID, String newColor, String newName, int newSemester) {
        String checkOwnershipSQL = "SELECT * FROM Groups WHERE GroupID = ? AND UserID = ?";
        String updateGroupSQL = "UPDATE Groups SET GroupColor = ?, GroupName = ?, GroupSemester = ? WHERE GroupID = ?";

        try (PreparedStatement pstmtCheck = c.prepareStatement(checkOwnershipSQL);
             PreparedStatement pstmtUpdate = c.prepareStatement(updateGroupSQL)) {

            // Check if the user is the owner
            pstmtCheck.setInt(1, groupID);
            pstmtCheck.setInt(2, userID);
            try (ResultSet rs = pstmtCheck.executeQuery()) {
                if (!rs.next()) {
                    return false; // User is not the owner
                }
            }

            // Update the group
            pstmtUpdate.setString(1, newColor);
            pstmtUpdate.setString(2, newName);
            pstmtUpdate.setInt(3, newSemester);
            pstmtUpdate.setInt(4, groupID);
            int affectedRows = pstmtUpdate.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean deleteGroup(int groupID, int userID) {
        String checkOwnershipSQL = "SELECT * FROM Groups WHERE GroupID = ? AND UserID = ?";
        String deleteGroupSQL = "DELETE FROM Groups WHERE GroupID = ?";
        String deleteCoursesSQL = "DELETE FROM Courses WHERE GroupID = ?";

        try (PreparedStatement pstmtCheck = c.prepareStatement(checkOwnershipSQL);
             PreparedStatement pstmtDeleteGroup = c.prepareStatement(deleteGroupSQL);
             PreparedStatement pstmtDeleteCourses = c.prepareStatement(deleteCoursesSQL)) {

            // Check if the user is the owner
            pstmtCheck.setInt(1, groupID);
            pstmtCheck.setInt(2, userID);
            try (ResultSet rs = pstmtCheck.executeQuery()) {
                if (!rs.next()) {
                    return false; // User is not the owner
                }
            }

            // Delete all courses associated with the group
            pstmtDeleteCourses.setInt(1, groupID);
            pstmtDeleteCourses.executeUpdate();

            // Delete the group
            pstmtDeleteGroup.setInt(1, groupID);
            int affectedRows = pstmtDeleteGroup.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean updateCourse(int courseID, int userID, String newColor, String newName) {
        String checkOwnershipSQL = "SELECT * FROM Courses WHERE CourseID = ? AND UserID = ?";
        String updateCourseSQL = "UPDATE Courses SET CourseColor = ?, CourseName = ? WHERE CourseID = ?";

        try (PreparedStatement course = c.prepareStatement(checkOwnershipSQL);
             PreparedStatement courseupdate = c.prepareStatement(updateCourseSQL)) {

            // Check if the user is the owner
            course.setInt(1, courseID);
            course.setInt(2, userID);
            try (ResultSet rs = course.executeQuery()) {
                if (!rs.next()) {
                    return false; // User is not the owner
                }
            }

            // Update the course
            courseupdate.setString(1, newColor);
            courseupdate.setString(2, newName);
            courseupdate.setInt(3, courseID);
            int affectedRows = courseupdate.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteCourse(int courseID, int userID) {
        String checkOwnershipSQL = "SELECT * FROM Courses WHERE CourseID = ? AND UserID = ?";
        String deleteCourseSQL = "DELETE FROM Courses WHERE CourseID = ?";
        String deleteOptionsSQL = "DELETE FROM Options WHERE CourseID = ?";

        try (PreparedStatement courseCheck = c.prepareStatement(checkOwnershipSQL);
             PreparedStatement DeleteCourse = c.prepareStatement(deleteCourseSQL);
             PreparedStatement DeleteOptions = c.prepareStatement(deleteOptionsSQL)) {

            // Check if the user is the owner
            courseCheck.setInt(1, courseID);
            courseCheck.setInt(2, userID);
            try (ResultSet rs = courseCheck.executeQuery()) {
                if (!rs.next()) {
                    return false; // User is not the owner
                }
            }

            // Delete all options associated with the course
            DeleteOptions.setInt(1, courseID);
            DeleteOptions.executeUpdate();

            // Delete the course
            DeleteCourse.setInt(1, courseID);
            int affectedRows = DeleteCourse.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    //Note MUST CHECK IF THE USER IS THE OWNER
    //TODO function that updates the option
    // - must update all labels as well
    // - must update all the days as well

    //TODO function that delete the option
    // - must delete all the label as well
    // - must delete all days as well

    //TODO select functions


}
