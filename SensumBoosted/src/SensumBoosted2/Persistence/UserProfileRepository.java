/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SensumBoosted2.Persistence;

import SensumBoosted2.Domain.UserInformation2;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nguye
 */
public class UserProfileRepository {

    ConnectRepository connectRepository;
    Connection connection;
    ResultSet rs;

    public UserProfileRepository() {
        connectRepository = new ConnectRepository();
        connection = connectRepository.getConnection();
    }

    public List getCitizenInformation() {
        List<UserInformation2> uiList = new ArrayList<>();
        try {
            rs = connection.createStatement().executeQuery("SELECT * FROM citizen_information ORDER BY user_id ASC");
            while (rs.next()) {
                uiList.add(new UserInformation2(rs.getInt("user_id"), rs.getString("firstname"), rs.getString("middlename"),
                        rs.getString("lastname"), rs.getInt("user_cpr"), rs.getString("address"), rs.getInt("postal_code"),
                        rs.getString("city"), rs.getString("email")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserProfileRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return uiList;
    }

    public List cprSearchCitizenInformation(int cpr) {
        List<UserInformation2> uiList = new ArrayList<>();
        try {
            rs = connection.createStatement().executeQuery("SELECT * FROM citizen_information WHERE cpr = " + cpr);
            while (rs.next()) {
                uiList.add(new UserInformation2(rs.getInt("user_id"), rs.getString("firstname"), rs.getString("middlename"),
                        rs.getString("lastname"), rs.getInt("user_cpr"), rs.getString("address"), rs.getInt("postal_code"),
                        rs.getString("city"), rs.getString("email")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserProfileRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return uiList;
    }

    public List firstnameSearchCitizenInformation(String firstname) {
        List<UserInformation2> uiList = new ArrayList<>();
        try {
            rs = connection.createStatement().executeQuery("SELECT * FROM citizen_information WHERE firstname = '" + firstname + "'");
            while (rs.next()) {
                uiList.add(new UserInformation2(rs.getInt("user_id"), rs.getString("firstname"), rs.getString("middlename"),
                        rs.getString("lastname"), rs.getInt("user_cpr"), rs.getString("address"), rs.getInt("postal_code"),
                        rs.getString("city"), rs.getString("email")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserProfileRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return uiList;
    }

    public void saveCitizenInformation(String firstname, String middlename, String lastname, int cpr, String address,
            int postalcode, String city, String email, int selectedUserID) {
        try {
            Statement st = connection.createStatement();
            String sql = "UPDATE citizen_information "
                    + "SET firstname = '" + firstname + "', middlename = '" + middlename + "',"
                    + "lastname = '" + lastname + "', user_cpr = " + cpr + ","
                    + "address = '" + address + "', postal_code = " + postalcode + ","
                    + "city = '" + city + "', email = '" + city + "'"
                    + " WHERE user_id = " + selectedUserID;
            int update = st.executeUpdate(sql);
            rs = st.getGeneratedKeys();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserProfileRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteCitizenInformation(int selectedUserID) {
        try {
            Statement st = connection.createStatement();
            String sql = "DELETE FROM citizen_information WHERE user_id= '" + selectedUserID + "'";
            st.executeUpdate(sql);
            deleteUser(selectedUserID);
        } catch (SQLException ex) {
            Logger.getLogger(UserProfileRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void deleteUser(int userID){
        try {
            Statement st = connection.createStatement();
            String sql = "DELETE FROM users WHERE user_id= '" + userID + "'";
            st.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(UserProfileRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
