/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * Mikkel har lavet denne linje som test og skal fjernes igen
 */
package sensumboosted.Persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.postgresql.core.QueryExecutor;
import sensumboosted.Domain.Encryption;

/**
 *
 * @author Mikkel Hoeyberg
 */
public class DatabaseController {

    private final String url = "jdbc:postgresql://balarama.db.elephantsql.com:5432/rsrrjzno";
    private final String userDB = "rsrrjzno";
    private final String passDB = "afVcwMqs2zGaNtod0axmHcsrAuy5u7uD";
    private Encryption encrypt;
    private int count;

    public DatabaseController() {
    }

    private ResultSet rs = null;
    private Connection connection = null;

    public Connection connect() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (java.lang.ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            connection = DriverManager.getConnection(url, userDB, passDB);
            System.out.println("Connected to the PostgreSQL server successfully.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }

    public String checkLogin(String user, String pass) {
        System.out.println("Checking login in progress, please wait.");
        try {
            Statement st = connection.createStatement();
            String sql = "SELECT username,password FROM users WHERE username='" + user + "' AND password='" + pass + "'";
            rs = st.executeQuery(sql);
            int count = 0;
            while (rs.next()) {
                count++;
            }
//          If there is no user found in the database
            if (count == 0) {
                System.out.println("No user found");  // Delete this when we hand in our code
                return "Username or Password is wrong!";
            }
//          If there is only 1 of the user in the database
            if (count == 1) {
                System.out.println("User found");  // Delete this when we hand in our code
                return "Succesful login";
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return "Login check failed!";
    }

    public void createUser(int userID, String username, String password, String userType) {
        try {
            Statement st = connection.createStatement();
            String sql = "INSERT INTO users "
                    + "(user_id,username,password,user_type)"
                    + " VALUES "
                    + "(" + userID + ',' + "'" + username + "'" + ',' + "'" + password + "'" + ',' + "'" + userType + "')";
            st.execute(sql);
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void createUserInformation(int userID, String firstname, String middlename, String lastname,
            int cpr, String address, int postalcode, String city, String email) {
        try {
            Statement st = connection.createStatement();
            String sql = "INSERT INTO user_information "
                    + "(user_id,firstname,middlename,lastname,cpr,address,"
                    + "postal_code,city,email)"
                    + " VALUES (" + userID + ",'" + firstname + "','" + middlename
                    + "','" + lastname + "','" + cpr + "','" + address + "'," + postalcode
                    + ",'" + city + "','" + email + "')";
            st.execute(sql);
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//Database should increment on its own by adding SERIAL to user ID
    public int getUserIDCount() {
        try (Statement st = connection.createStatement()) {
            String sql = "SELECT COUNT(*) AS user_id FROM Users";
            rs = st.executeQuery(sql);
            while (rs.next()) {
                count = rs.getInt("user_id");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

}
