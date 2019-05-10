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
    ResultSet rs;
    Encryption encrypt;

    // VIRKER IKKE... FIND UD AF PROBLEMET
    public void createCitizenInformation(String firstname, String middlename, String lastname,
            int cpr, String address, int postalcode, String city, String email, int phonenumber,
            String department) {
        try {
            Statement st = connection.createStatement();
            String sql = "INSERT INTO citizen_information "
                    + "(firstname, middlename, lastname, cpr, address, "
                    + "postal_code, city, email, phonenumber, department)"
                    + " VALUES ('" + firstname + "','" + middlename
                    + "','" + lastname + "'," + cpr + ",'" + address + "'," + postalcode
                    + ",'" + city + "','" + email + "'," + phonenumber + ",'" + department + "')";

            st.executeUpdate(sql);
            rs = st.getGeneratedKeys();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserProfileRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void createCitizenAccount(String username, String password, String usertype) {
        try {
            Statement st = connection.createStatement();
            String sql = "INSERT INTO users "
                    + "(username, password, usertype)"
                    + " VALUES ('" + username + "','" + encrypt.encryptString(password) + "','" 
                    + usertype + "')";
            st.executeUpdate(sql);
            rs = st.getGeneratedKeys();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserProfileRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
