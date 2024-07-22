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
        ResultSet result;
        try {
            statement = c.createStatement();
            result = statement.executeQuery("SELECT * FROM Users WHERE UserName = '" +username+ "' AND UserPassword = '"+password+"';");
            if (result.isBeforeFirst()){
                int id = Integer.parseInt(result.getString("UserId"));
                return new User(username, id);
            }
        } catch (SQLException e){
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
            return new ArrayList<String>();
        }
        return s;
    }


    public boolean createGroup(Group g) {
        int UserID = Model.getInstance().getUser().getId();
        String color = g.getColor();
        String name = g.getName();
        int semester = g.getSemester();
        String sql = "INSERT INTO Groups (UserID, GroupColor, GroupName, GroupSemester) VALUES (?, ?, ?, ?)";
        try (PreparedStatement group = c.prepareStatement(sql)) {
            group.setInt(1, UserID);
            group.setString(2, color);
            group.setString(3, name);
            group.setInt(4, semester);
            int affectedRows = group.executeUpdate();

            g.setUID(UserID);
            return affectedRows > 0;
        } catch (SQLException e) {
            return false;
        }
    }


    public boolean insertCourse(Course course, int GroupID) {
        //Checks the ownership
        if (course.getUid() != Model.getInstance().getUser().getId())  return false;


        String sql = "INSERT INTO Courses (CourseName, CourseColor, GroupID) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = c.prepareStatement(sql)) {
            pstmt.setInt(3, GroupID);
            pstmt.setString(2, course.getColor());
            pstmt.setString(1, course.getName());
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            return false;
        }
    }


    public boolean updateGroup( Group g) {
        //Checks the ownership
        if (g.getuserid() != Model.getInstance().getUser().getId())  return false;

        int groupID = g.getId();
        String newColor = g.getColor();
        String newName = g.getName();
        int newSemester = g.getSemester();

        String updateGroupSQL = "UPDATE Groups SET GroupColor = ?, GroupName = ?, GroupSemester = ? WHERE GroupID = ?";

        try (PreparedStatement Update = c.prepareStatement(updateGroupSQL)) {
            // Update the group
            Update.setString(1, newColor);
            Update.setString(2, newName);
            Update.setInt(3, newSemester);
            Update.setInt(4, groupID);
            int affectedRows = Update.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }



    public boolean deleteGroup(Group group) {
        //Checks the ownership
        if (group.getuserid() != Model.getInstance().getUser().getId())  return false;


        String deleteGroupSQL = "DELETE FROM Groups WHERE GroupID = ?";
        String deleteCourseSQL = "DELETE FROM Courses WHERE GroupID = ? AND CourseID = ?";

        try (PreparedStatement DeleteGroup = c.prepareStatement(deleteGroupSQL);
             PreparedStatement DeleteCourse = c.prepareStatement(deleteCourseSQL)) {


            for (Course course : group.getCourses()) {
                DeleteCourse.setInt(1, group.getId());
                DeleteCourse.setInt(2, course.getId());
                DeleteCourse.executeUpdate();
            }


            DeleteGroup.setInt(1, group.getId());
            int affectedRows = DeleteGroup.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }



    public boolean updateCourse(ChoiceOption choiceOption) {
        if(choiceOption.getUid() != Model.getInstance().getUser().getId()){
            return false;
        }
        String deleteSQL = "DELETE FROM Days WHERE OptionID = ?; " + "DELETE FROM Labels WHERE OptionID = ?";
        String insertDaySQL = "INSERT INTO Days (OptionID, Day) VALUES (?, ?)";
        String insertLabelSQL = "INSERT INTO Labels (OptionID, LabelText) VALUES (?, ?)";

        int optionID = choiceOption.getId();
        int userID = choiceOption.getUid();

        try (PreparedStatement deleteStmt = c.prepareStatement(deleteSQL);
             PreparedStatement insertDayStmt = c.prepareStatement(insertDaySQL);
             PreparedStatement insertLabelStmt = c.prepareStatement(insertLabelSQL)) {

            // Delete old entries
            deleteStmt.setInt(1, optionID);
            deleteStmt.setInt(2, optionID);
            deleteStmt.setInt(3, optionID);
            deleteStmt.executeUpdate();

            // Insert new days
            insertDays(choiceOption);


            // Insert new label
            insertLabels(choiceOption);

            return true;
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



    public boolean updateOption(ChoiceOption choiceOption) {
        //Checks the ownership
        if (choiceOption.getUid() != Model.getInstance().getUser().getId())  return false;


        String insertDaySQL = "INSERT INTO Days (OptionID, Day) VALUES (?, ?)";
        String insertHourSQL = "INSERT INTO Hours (OptionID, DayStartT, DayEndT) VALUES (?, ?, ?)";
        String insertLabelSQL = "INSERT INTO Labels (OptionID, LabelText) VALUES (?, ?)";

        int optionID = choiceOption.getId();

        try (
                PreparedStatement insertDayStmt = c.prepareStatement(insertDaySQL);
                PreparedStatement insertHourStmt = c.prepareStatement(insertHourSQL);
                PreparedStatement insertLabelStmt = c.prepareStatement(insertLabelSQL)
        ) {

            deleteDays(choiceOption);
            deleteLabels(choiceOption);

            insertDays(choiceOption);
            insertLabels(choiceOption);

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }



    public boolean deleteOption(ChoiceOption co) {
        //Checks the ownership
        if (co.getUid() != Model.getInstance().getUser().getId())  return false;

        String deleteOptionSQL = "DELETE FROM Options WHERE OptionID = " + co.getId();

        try (PreparedStatement DeleteOption = c.prepareStatement(deleteOptionSQL)) {
            deleteLabels(co);
            deleteDays(co);

            return DeleteOption.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteLabels(ChoiceOption co){
        String deleteLabelsSQL = "DELETE FROM Labels WHERE OptionID = " + co.getId();
        try (PreparedStatement DeleteLabels = c.prepareStatement(deleteLabelsSQL);) {
            DeleteLabels.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean deleteDays(ChoiceOption co){
        String deleteLabelsSQL = "DELETE FROM Days WHERE OptionID = " + co.getId();
        try (PreparedStatement DeleteLabels = c.prepareStatement(deleteLabelsSQL);) {
            DeleteLabels.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }



    public synchronized boolean insertOption(ChoiceOption choiceOption, int CourseID){
        String insertLabel = "INSERT INTO Options (CourseID) VALUES("+CourseID+")";
        int OpID = 0;

        Statement statement;
        ResultSet result = null;
        try {
            //Inserts the new option
            PreparedStatement insertLabelStmt = c.prepareStatement(insertLabel);
            insertLabelStmt.addBatch();
            insertLabelStmt.executeBatch();


            //updates the option_object's ID
            statement = c.createStatement();
            result = statement.executeQuery("SELECT MAX(OptionID) AS op from Options");
            OpID = Integer.parseInt(result.getString("op"));
            choiceOption.setId(OpID);

            //Inserts option labels and days
            insertLabels(choiceOption);
            insertDays(choiceOption);

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public synchronized boolean insertLabels(ChoiceOption co){
        int OptionID = co.getId();
        ArrayList<String> labels = co.getLabelList();
        String insertLabel = "INSERT INTO LABELS (optionID, LabelText) VALUES(?,?)";

        try {
            for (String label : labels) {
                PreparedStatement insertLabelStmt = c.prepareStatement(insertLabel);
                insertLabelStmt.setInt(1,OptionID);
                insertLabelStmt.setString(2, label);
                insertLabelStmt.addBatch();
                insertLabelStmt.executeBatch();
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public synchronized boolean insertDays(ChoiceOption co){
        ArrayList<Byte> days = co.getDayList();
        ArrayList<byte[]> hours = co.getHourList();
        int OptionID = co.getId();

        try {
            for (int i = 0; i < days.size(); i++) {
                String insertLabel = "INSERT INTO DAYS (optionID, Day, DayStartT,DayEndT) VALUES("+OptionID+","+days.get(i)+
                        ","+hours.get(i)[0]+","+hours.get(i)[1]+")";
                PreparedStatement insertLabelStmt = c.prepareStatement(insertLabel);
                insertLabelStmt.addBatch();
                insertLabelStmt.executeBatch();
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
