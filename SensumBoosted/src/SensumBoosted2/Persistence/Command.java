/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SensumBoosted2.Persistence;

import SensumBoosted2.Domain.UserInformation2;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author nguye
 */
public class Command extends ConnectRepository {
    
    public ObservableList<UserInformation2> obListUI = FXCollections.observableArrayList();
    public int userid;
    public String firstname;
    public String middlename;
    public String lastname;
    public int cpr;
    public String address;
    public int postalcode;
    public String city;
    public String email;
    
    public void addData(String statement) {
        connect();
        try {
            st = connection.createStatement();
            st.execute(statement);
            System.out.println("add data executed");
        } catch (SQLException ex) {
            Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ObservableList getData(String result) {
//        connect();
        
        try {
            st = connection.createStatement();
            rs = st.executeQuery(result);
            
            while (rs.next()) {
                userid = rs.getInt("user_id");
                firstname = rs.getString("firstname");
                middlename = rs.getString("middlename");
                lastname = rs.getString("lastname");
                cpr = rs.getInt("cpr");
                address = rs.getString("address");
                postalcode = rs.getInt("postal_code");
                city = rs.getString("city");
                email = rs.getString("email");
                obListUI.add(new UserInformation2(userid, firstname, middlename, lastname, cpr, address,
                postalcode, city, email));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Data fetched");
        return obListUI;
    }
    
    
}
