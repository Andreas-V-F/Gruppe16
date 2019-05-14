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
    private Connection connection;
    private ResultSet rs;

    public CaseRepository() {
        connectRepository = new ConnectRepository();
        connection = connectRepository.getConnection();
    }

    public void createCase(Case case1) {
        closeAllCases(case1);
        int sagsId = (int) (Math.random() * 1000);
        try {
            Statement st = connection.createStatement();
            String sql = "INSERT INTO sager (case_id,user_id,isopen,inquiry_text,edit_date,inquirer,assessment,taskpurpose,taskgoal) VALUES ('" + sagsId + "','" + case1.getUserInfo().getUserid() + "','true','" + case1.getInquiryText() + "','" + new Date() + "','" + case1.getInquirer() + "','" + case1.getAssessment() + "','" + case1.getTaskPurpose() + "','" + case1.getTakeGoal() + "');";
            rs = st.executeQuery(sql);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void closeAllCases(Case case1) {
        try {
            Statement st = connection.createStatement();
            String sql = "UPDATE sager SET isopen = 'false' WHERE user_id = '" + case1.getUserInfo().getUserid() + "';";
            rs = st.executeQuery(sql);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public boolean hasOpenCase(Case case1) {
        try {
            Statement st = connection.createStatement();
            String sql = "SELECT user_id FROM sager WHERE user_id='" + case1.getUserInfo().getUserid() + "' AND isopen='true';";
            rs = st.executeQuery(sql);
            rs.next();
            if (rs.getInt(1) == case1.getUserInfo().getUserid()) {
                return true;
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    public void saveCase(Case case1) {

        try {
            Statement st = connection.createStatement();
            String sql = "UPDATE sager SET inquiry_text = '" + case1.getInquiryText() + "', inquirer='" + case1.getInquirer() + "', assessment='" + case1.getAssessment() + "', taskpurpose='" + case1.getTaskPurpose() + "', taskgoal='" + case1.getTakeGoal() + "', edit_date ='" + new Date() + "' WHERE user_id ='" + case1.getUserInfo().getUserid() + "' AND isopen ='true';";
            rs = st.executeQuery(sql);
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

    public Case CaseText(Case case1) {
        Case case2 = null;
        if (hasOpenCase(case1)) {
            try {
                Statement st = connection.createStatement();
                String sql = "SELECT * FROM sager WHERE user_id='" + case1.getUserInfo().getUserid()+ "' AND isopen = 'true';";
                rs = st.executeQuery(sql);
                while (rs.next()) {
                    case2 = new Case(case1.getUserInfo(), rs.getString("inquiry_text"), rs.getDate("added_date"), rs.getDate("edit_date"), rs.getString("inquirer"), rs.getString("assessment"), rs.getString("taskpurpose"), rs.getString("taskgoal"));
                }
                return case2;
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return null;
    }

    public List getPreviousCases(Case case1) {
        List<Case> cases = new ArrayList<>();
        try {
            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM sager WHERE user_id='" + case1.getUserInfo().getUserid() + "' AND isopen ='false';");
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
