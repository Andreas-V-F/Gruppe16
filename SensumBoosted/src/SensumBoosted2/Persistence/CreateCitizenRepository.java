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

    public CreateCitizenRepository() {
        connectRepository = new ConnectRepository();
        connection = connectRepository.getConnection();
        encrypt = new Encryption();
    }

    // VIRKER IKKE... FIND UD AF PROBLEMET
    public void createCitizenInformation(int userId, String firstname, String middlename, String lastname,
            int cpr, String address, int postalcode, String city, String email, int phonenumber,
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
        } catch (SQLException ex) {
            Logger.getLogger(UserProfileRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int createCitizenAccount(String firstname, String middlename, String lastname,
            int cpr, String address, int postalcode, String city, String email, int phonenumber,
            String department, String username, String password, String usertype) {
        int userID = 0;
        try {
            Statement st = connection.createStatement();
            String sql = "INSERT INTO users "
                    + "(username, password, user_type)"
                    + " VALUES ('" + username + "','" + encrypt.encryptString(password) + "','"
                    + usertype + "')";
            st.executeUpdate(sql);
            sql = "SELECT user_id FROM users WHERE username='" + username + "';";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                createCitizenInformation(rs.getInt("user_id"), firstname, middlename, lastname, cpr, address, postalcode, city, email, phonenumber, department);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserProfileRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return userID;
    }
}
