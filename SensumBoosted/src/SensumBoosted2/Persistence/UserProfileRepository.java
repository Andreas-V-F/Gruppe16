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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
            rs = connection.createStatement().executeQuery("SELECT * FROM citizen_information");
            while (rs.next()) {
                uiList.add(new UserInformation2(rs.getInt("user_id"), rs.getString("firstname"), rs.getString("middlename"),
                        rs.getString("lastname"), rs.getInt("cpr"), rs.getString("address"), rs.getInt("postal_code"),
                        rs.getString("city"), rs.getString("email")));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserProfileRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return uiList;
    }

//    public void addData(String statement) {
//        connect();
//        try {
//            st = connection.createStatement();
//            st.execute(statement);
//            System.out.println("add data executed");
//        } catch (SQLException ex) {
//            Logger.getLogger(UserProfileRepository.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//    
//    public ObservableList getData(String result) {
////        connect();
//        
//        try {
//            st = connection.createStatement();
//            rs = st.executeQuery(result);
//            
//            while (rs.next()) {
//                userid = rs.getInt("user_id");
//                firstname = rs.getString("firstname");
//                middlename = rs.getString("middlename");
//                lastname = rs.getString("lastname");
//                cpr = rs.getInt("cpr");
//                address = rs.getString("address");
//                postalcode = rs.getInt("postal_code");
//                city = rs.getString("city");
//                email = rs.getString("email");
//                obListUI.add(new UserInformation2(userid, firstname, middlename, lastname, cpr, address,
//                postalcode, city, email));
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(UserProfileRepository.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        System.out.println("Data fetched");
//        return obListUI;
//    }
}
