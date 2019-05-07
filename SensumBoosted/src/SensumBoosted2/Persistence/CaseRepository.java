/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SensumBoosted2.Persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import SensumBoosted2.Domain.Case;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sensumboosted.GUI.FXMLDocumentController;

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
            String sql = "INSERT INTO sager (case_id,user_id,isopen,text) VALUES ('" + sagsId + "','" + case1.getUser().getID() + "','true','" + case1.getText() + "');";
            rs = st.executeQuery(sql);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void closeAllCases(Case case1) {
        try {
            Statement st = connection.createStatement();
            String sql = "UPDATE sager SET isopen = 'false' WHERE user_id = '" + case1.getUser().getID() + "';";
            rs = st.executeQuery(sql);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public boolean hasOpenCase(Case case1) {
        try {
            Statement st = connection.createStatement();
            String sql = "SELECT user_id FROM sager WHERE user_id='" + case1.getUser().getID() + "' AND isopen='true';";
            rs = st.executeQuery(sql);
            rs.next();
            if (rs.getInt(1) == case1.getUser().getID()) {
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
            String sql = "UPDATE sager SET text = '" + case1.getText() + "', edit_date ='" + new Date() + "' WHERE user_id ='" + case1.getUser().getID() + "' AND isopen ='true';";
            rs = st.executeQuery(sql);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public int findCaseID(Case case1) {
        try {
            Statement st = connection.createStatement();
            String sql = "SELECT case_id FROM sager WHERE user_id='" + case1.getUser().getID() + "' AND isopen='true';";
            rs = st.executeQuery(sql);
            rs.next();
            return rs.getInt(1);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return -1;
    }

    public String CaseText(Case case1) {
        if (hasOpenCase(case1)) {
            try {
                Statement st = connection.createStatement();
                String sql = "SELECT text FROM sager WHERE user_id='" + case1.getUser().getID() + "' AND isopen = 'true';";
                rs = st.executeQuery(sql);
                rs.next();
                return rs.getString(1);
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return "";
    }

    public List getPreviousCases(Case case1) {
        List<Case> cases = new ArrayList<>();
        try {
            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM sager WHERE user_id='" + case1.getUser().getID() + "';");
            while (rs.next()) {
                cases.add(new Case(rs.getString("text"), rs.getDate("added_date"), rs.getDate("edit_date")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return cases;
    }
}
