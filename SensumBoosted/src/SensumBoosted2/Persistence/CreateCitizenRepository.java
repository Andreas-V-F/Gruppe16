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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alex
 */
public class CreateCitizenRepository {

    ConnectRepository connectRepository;
    Connection connection;
    CaseRepository caseRepository;
    ResultSet rs;
    Encryption encrypt;

    public CreateCitizenRepository() {
        connectRepository = new ConnectRepository();
        caseRepository = new CaseRepository();
        connection = connectRepository.getConnection();
        encrypt = new Encryption();
    }

    public void createCitizenInformation(int userId, String firstname, String middlename, String lastname,
            long cpr, String address, int postalcode, String city, String email, int phonenumber,
            String department) {
        try {
            Statement st = connection.createStatement();
            String sql = "INSERT INTO citizen_information "
                    + "(user_id, firstname, middlename, lastname, user_cpr, address, "
                    + "postal_code, city, email, phonenumber, department)"
                    + " VALUES ('" + userId + "','" + firstname + "','" + middlename
                    + "','" + lastname + "'," + cpr + ",'" + address + "'," + postalcode
                    + ",'" + city + "','" + email + "'," + phonenumber + ",'" + department + "')";
            st.executeUpdate(sql);
            st.close();
            caseRepository.createCase(userId, "", "", "", "", "");
        } catch (SQLException ex) {
            Logger.getLogger(UserProfileRepository.class.getName()).log(Level.SEVERE, null, ex);
            
        } 
    }

    public void createCitizenAccount(String firstname, String middlename, String lastname,
            long cpr, String address, int postalcode, String city, String email, int phonenumber,
            String department, String password, String usertype) {
        try {
            Statement st = connection.createStatement();
            String sql = "INSERT INTO users "
                    + "(username, password, user_type, department)"
                    + " VALUES ('" + cpr + "','" + encrypt.encryptString(password) + "','"
                    + usertype + "','" + department + "')";
            st.executeUpdate(sql);
            sql = "SELECT user_id FROM users WHERE username='" + cpr + "';";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                createCitizenInformation(rs.getInt("user_id"), firstname, middlename, lastname, cpr, address, postalcode, city, email, phonenumber, department);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserProfileRepository.class.getName()).log(Level.SEVERE, null, ex);
            
        }
    }

    public boolean checkCprRepo(long cpr) {
        boolean exists = true;
        try {
            Statement st = connection.createStatement();
            String sql = "SELECT user_cpr FROM citizen_information WHERE user_cpr='" + cpr + "';";
            ResultSet rs = st.executeQuery(sql);
            int count = 0;
            while (rs.next()) {
                count++;
            }
            if(count == 0){
                exists = false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserProfileRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return exists;
    }
    
    public boolean checkPhoneRepo(int number) {
        boolean exists = true;
        try {
            Statement st = connection.createStatement();
            String sql = "SELECT phonenumber FROM citizen_information WHERE phonenumber='" + number + "';";
            ResultSet rs = st.executeQuery(sql);
            int count = 0;
            while (rs.next()) {
                count++;
            }
            if(count == 0){
                exists = false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserProfileRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return exists;
    }
}
