/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SensumBoosted2.Persistence;

import SensumBoosted2.Domain.Case;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 *
 * @author Andreas Frederiksen
 */
public class CaseRepository {

    ConnectRepository connectRepository;
    DiaryRepository diaryRepository;
    private Connection connection;
    private ResultSet rs;

    public CaseRepository() {
        connectRepository = new ConnectRepository();
        connection = connectRepository.getConnection();
        diaryRepository = new DiaryRepository();
    }

    public void createCase(int userID, String inquiryText, String inquirer, String assessmentText, String taskPurpose, String taskGoal) {
        closeAllCases(userID);
        long caseId = System.currentTimeMillis();
        try {
            Statement st = connection.createStatement();
            String sql = "INSERT INTO sager (case_id,user_id,isopen,inquiry_text,edit_date,inquirer,assessment,taskpurpose,taskgoal) VALUES ('" + caseId + "','" + userID + "','true','" + inquiryText + "','" + new Date() + "','" + inquirer + "','" + assessmentText + "','" + taskPurpose + "','" + taskGoal + "');";
            st.executeUpdate(sql);
            st.close();
            System.out.println("calling method");
            diaryRepository.createDiary(caseId, userID);
            System.out.println("called method");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("YEEET");
        } 

    }

    public void closeAllCases(int userID) {
        try {
            Statement st = connection.createStatement();
            String sql = "UPDATE sager SET isopen = 'false' WHERE user_id = '" + userID + "';";
            st.executeUpdate(sql);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public boolean hasOpenCase(int userID) {
        try {
            Statement st = connection.createStatement();
            String sql = "SELECT user_id FROM sager WHERE user_id='" + userID + "' AND isopen='true';";
            rs = st.executeQuery(sql);
            rs.next();
            if (rs.getInt(1) == userID) {
                return true;
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    public void saveCase(int userID, String inquiryText, String inquirer, String assessmentText, String taskPurpose, String taskGoal) {

        try {
            Statement st = connection.createStatement();
            String sql = "UPDATE sager SET inquiry_text = '" + inquiryText + "', inquirer='" + inquirer + "', assessment='" + assessmentText + "', taskpurpose='" + taskPurpose + "', taskgoal='" + taskGoal + "', edit_date ='" + new Date() + "' WHERE user_id ='" + userID + "' AND isopen ='true';";
            st.executeUpdate(sql);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public int findCaseID(Case case1) {
        try {
            Statement st = connection.createStatement();
            String sql = "SELECT case_id FROM sager WHERE user_id='" + case1.getUserInfo().getUserid() + "' AND isopen='true';";
            rs = st.executeQuery(sql);
            rs.next();
            return rs.getInt(1);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return -1;
    }

    public String[] CaseText(int userID) {
        String inquiryText = null;
        String inquirerText = null;
        String assesmentText = null;
        String taskPurposeText = null;
        String taskGoalText = null;
        if (hasOpenCase(userID)) {
            try {
                Statement st = connection.createStatement();
                String sql = "SELECT inquiry_text, inquirer, assessment, taskpurpose, taskgoal FROM sager WHERE user_id='" + userID + "' AND isopen = 'true';";
                rs = st.executeQuery(sql);
                while (rs.next()) {
                    inquiryText = rs.getString("inquiry_text");
                    inquirerText = rs.getString("inquirer");
                    assesmentText = rs.getString("assessment");
                    taskPurposeText = rs.getString("taskpurpose");
                    taskGoalText = rs.getString("taskgoal");
                }
                String[] caseStrings = {inquiryText, inquirerText, assesmentText, taskPurposeText, taskGoalText};
                return caseStrings;
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return null;
    }

    public List getPreviousCases(int userID) {
        List<Case> cases = new ArrayList<>();
        try {
            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM sager WHERE user_id='" + userID + "' AND isopen ='false';");
            while (rs.next()) {
                cases.add(new Case(rs.getString("inquiry_text"), rs.getDate("added_date"), rs.getDate("edit_date"), rs.getString("assessment")));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return cases;
    }

    public Case selectPreviousCase(Case case1) {
        Case case2 = null;
        try {
            Statement st = connection.createStatement();
            String sql = "SELECT * FROM sager WHERE inquiry_text='" + case1.getInquiryText() + "' AND added_date='" + case1.getAddedDate() + "';";
            rs = st.executeQuery(sql);
            while (rs.next()) {
                case2 = new Case(case1.getUserInfo(), rs.getString("inquiry_text"), rs.getDate("added_date"), rs.getDate("edit_date"), rs.getString("inquirer"), rs.getString("assessment"), rs.getString("taskpurpose"), rs.getString("taskgoal"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return case2;
    }
}
