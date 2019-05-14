/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SensumBoosted2.Persistence;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import sensumboosted.Domain.Admin;
import sensumboosted.Domain.CaseWorker;
import sensumboosted.GUI.FXMLDocumentController;
import sensumboosted.Persistence.Log;

/**
 *
 * @author Mikkel Hoeyberg
 */
public class LoginRepository {

    private ConnectRepository connectRepository;
    private LogRepository logRepository;

    public LoginRepository() {
    }

    public boolean validateInDatabase(String loginUsername, String loginPassword) {
        String attempt;
        boolean b = false;
        connectToDatabase();
        if (checkDatabaseForUser(loginUsername, loginPassword)) {
            b = true;
        }
        if (b) {
            attempt = "har logget ind i systemet";
        } else {
            attempt = "har forsøgt at logge ind i systemet";
        }
        logLoginAttempt(loginUsername, attempt);
        return b;
    }

    private void connectToDatabase() {
        connectRepository = new ConnectRepository();
    }

    private boolean checkDatabaseForUser(String loginUsername, String loginPassword) {
        loginPassword = new Encryption().encryptString(loginPassword);
        boolean checked = false;
        String sql = "SELECT username,password FROM users WHERE username='" + loginUsername + "' AND password='" + loginPassword + "'";
        try {
            ResultSet rs = connectRepository.getConnection().createStatement().executeQuery(sql);
            while (rs.next()) {
                checked = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("DEN BLIVER FANGET HER");
        }
        System.out.println("checked: " + checked);
        return checked;
    }

    private void logLoginAttempt(String username, String attempt) {
        try {
            logRepository = new LogRepository();
            logRepository.createLog("LoginLog.txt");
            logRepository.getLogger().setLevel(Level.ALL);
            logRepository.getLogger().info("Bruger: \"" + username + "\" " + attempt);
        } catch (SecurityException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String[] returnStaffInformation(String loginUsername) {
        String[] staffinfo = new String[3];
        try {
            Statement st = connectRepository.getConnection().createStatement();
            String sql = "SELECT user_type,user_id,department FROM users WHERE username='" + loginUsername + "';";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                staffinfo[0] = rs.getString("user_type");
                staffinfo[1] = rs.getString("user_id");
                staffinfo[2] = rs.getString("department");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return staffinfo;
    }

}
