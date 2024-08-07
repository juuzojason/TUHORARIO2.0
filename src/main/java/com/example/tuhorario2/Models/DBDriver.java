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

    public void registerUser(String username, String password) {
        //register
        String insertLabel = "INSERT INTO Users (UserName, UserPassword) VALUES('"+username+"','"+password+"')";
        try {
            PreparedStatement insertLabelStmt = c.prepareStatement(insertLabel);
            insertLabelStmt.addBatch();
            insertLabelStmt.executeBatch();
        }catch (SQLException e) {
        }
    }


    public boolean userExists(String username){
        Statement statement;
        ResultSet result;
        try {
            statement = c.createStatement();
            result = statement.executeQuery("SELECT * FROM Users WHERE UserName = '" +username+ "'");
            if (result.next()) return true;

        } catch (SQLException e){
        }
        return false;
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
                int id = result.getInt("OptionID");
                ChoiceOption g = new ChoiceOption(id,UserID);
                System.out.println("HORAS" + getHours(id).toString());
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
            ResultSet result = st.executeQuery("SELECT * FROM LABELS WHERE OptionIDd = " + optionID + ";");
            while (result.next()){
                String bh = result.getString("LabelText");
                s.add(bh);
            }
        } catch (SQLException e){
            return new ArrayList<String>();
        }
        return s;
    }


    public synchronized boolean createGroup(Group g) {
        int UserID = Model.getInstance().getUser().getId();
        String color = g.getColor();
        String name = g.getName();
        int semester = g.getSemester();
        int gid;


        String sql = "INSERT INTO Groups (UserID, GroupColor, GroupName, GroupSemester) VALUES (?, ?, ?, ?);";
        try (PreparedStatement group = c.prepareStatement(sql)) {
            group.setInt(1, UserID);
            group.setString(2, color);
            group.setString(3, name);
            group.setInt(4, semester);
            int affectedRows = group.executeUpdate();

            //Set the group user id
            g.setUID(UserID);

            // get the group id
            Statement st = c.createStatement();
            ResultSet result = st.executeQuery("SELECT MAX(GroupID) AS id FROM GROUPS;");
            gid = result.getInt("id");
            g.setId(gid);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }


    public synchronized boolean createCourse(Course course, int GroupID) {
        //Checks the ownership
        if (Model.getInstance().getSelectedGroup().getuserid() != Model.getInstance().getUser().getId())  return false;


        String sql = "INSERT INTO Courses (CourseName, CourseColor, GroupID) VALUES (?, ?, ?);";
        try {
            PreparedStatement pstmt = c.prepareStatement(sql);
            pstmt.setInt(3, GroupID);
            pstmt.setString(2, course.getColor());
            pstmt.setString(1, course.getName());
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            return false;
        }
    }


    public void updateGroup(Group g) {
        //Checks the ownership
        if (g.getuserid() != Model.getInstance().getUser().getId())  return;

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

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public void deleteGroup(Group group) {
        //Checks the ownership
        if (group.getuserid() != Model.getInstance().getUser().getId())  return;


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

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public void updateCourse(Course course) {
        if (course.getUid() != Model.getInstance().getUser().getId())  return;

        int courseID = course.getId();
        String newColor = course.getColor();
        String newName = course.getName();

        String updateGroupSQL = "UPDATE COURSES SET CourseColor = ?, CourseName = ? WHERE CourseID = ?";

        try (PreparedStatement Update = c.prepareStatement(updateGroupSQL)) {
            // Update the group
            Update.setString(1, newColor);
            Update.setString(2, newName);
            Update.setInt(3, courseID);
            int affectedRows = Update.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteCourse(Course course) {
        String checkOwnershipSQL = "SELECT * FROM Courses WHERE CourseID = ?";
        String deleteCourseSQL = "DELETE FROM Courses WHERE CourseID = ?";
        String deleteOptionsSQL = "DELETE FROM Options WHERE CourseID = ?";
        int courseID = course.getId();

        try (PreparedStatement courseCheck = c.prepareStatement(checkOwnershipSQL);
             PreparedStatement DeleteCourse = c.prepareStatement(deleteCourseSQL);
             PreparedStatement DeleteOptions = c.prepareStatement(deleteOptionsSQL)) {

            // Check if the user is the owner
            courseCheck.setInt(1, courseID);
            try (ResultSet rs = courseCheck.executeQuery()) {
                if (!rs.next()) {
                    return; // User is not the owner
                }
            }

            // Delete all options associated with the course
            DeleteOptions.setInt(1, courseID);
            DeleteOptions.executeUpdate();

            // Delete the course
            DeleteCourse.setInt(1, courseID);
            int affectedRows = DeleteCourse.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public boolean updateOption(ChoiceOption choiceOption) {
        //Checks the ownership
        if (choiceOption.getUid() != Model.getInstance().getUser().getId()) return false;


        int optionID = choiceOption.getId();
        deleteDays(choiceOption);
        deleteLabels(choiceOption);

        insertDays(choiceOption);
        insertLabels(choiceOption);
        return true;
    }



    public boolean deleteOption(ChoiceOption co) {
        //Checks the ownership
        if (co.getUid() != Model.getInstance().getUser().getId())  return false;

        String deleteOptionSQL = "DELETE FROM Options WHERE OptionID = " + co.getId() +";";

        try (PreparedStatement DeleteOption = c.prepareStatement(deleteOptionSQL)) {
            deleteLabels(co);
            deleteDays(co);
            System.out.println(deleteOptionSQL);
            DeleteOption.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteLabels(ChoiceOption co){
        String deleteLabelsSQL = "DELETE FROM Labels WHERE OptionIDd = " + co.getId() + ";";
        try {
            PreparedStatement DeleteLabels = c.prepareStatement(deleteLabelsSQL);
            DeleteLabels.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean deleteDays(ChoiceOption co){
        String deleteLabelsSQL = "DELETE FROM Days WHERE OptionID = " + co.getId() + ";";
        try (PreparedStatement DeleteLabels = c.prepareStatement(deleteLabelsSQL);) {
            DeleteLabels.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }



    public synchronized boolean createOption(ChoiceOption choiceOption, int CourseID){
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
            result = statement.executeQuery("SELECT MAX(OptionID) AS op FROM Options;");
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
        String insertLabel = "INSERT INTO LABELS (optionIDd, LabelText) VALUES("+OptionID;

        try {
            for (String label : labels) {
                PreparedStatement insertLabelStmt = c.prepareStatement(insertLabel + ",'"+label+"');");
                System.out.println(insertLabel + ",'"+label+"');");
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
                        ","+hours.get(i)[0]+","+hours.get(i)[1]+");";
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
