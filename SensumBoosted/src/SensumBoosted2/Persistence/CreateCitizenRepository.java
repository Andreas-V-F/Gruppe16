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

    // VIRKER IKKE... FIND UD AF PROBLEM
    // Problemet er i databasen. Har noget med user_id SERIAL PIMARY KEY at gøre
    // Eksempel: Når den skal oprette ny bruger, så vil den starte fra user_id = 1
    // og fortælle at der allerede findes det user_id, og det gør sådan at programmet
    // giver exception, da databasen melder fejl.
    // Det blev testet i databasen og man skal køre den enkelte statement flere gange
    // for at det når til det user_id der ikke findes og derfra vil den oprette ny bruger.
    public void createCitizenInformation(String firstname, String middlename, String lastname,
            int cpr, String address, int postalcode, String city, String email, int phonenumber,
            String department) {
        try {
            Statement st = connection.createStatement();
            String sql = "INSERT INTO citizen_information "
                    + "(firstname, middlename, lastname, cpr, address, "
                    + "postal_code, city, email, phonenumber, department)"
                    + " VALUES (" + firstname + "','" + middlename
                    + "','" + lastname + "'," + cpr + ",'" + address + "'," + postalcode
                    + ",'" + city + "','" + email + "'," + phonenumber + ",'" + department + "')";

            st.executeUpdate(sql);
            rs = st.getGeneratedKeys();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserProfileRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
