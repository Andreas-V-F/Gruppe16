/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * Mikkel har lavet denne linje som test og skal fjernes igen
 */
package sensumboosted.Domain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mikkel Hoeyberg
 */
public class DatabaseController {

    // Localhost som kan styres via pgAdmin 4
//    private final String url = "jdbc:postgresql://localhost:5432/example";
//    private final String userDB = "postgres";
//    private final String passDB = "postgres";
    // Controlled by phpMySql
    private final String url = "jdbc:postgresql://balarama.db.elephantsql.com:5432/rsrrjzno";
    private final String userDB = "rsrrjzno";
    private final String passDB = "afVcwMqs2zGaNtod0axmHcsrAuy5u7uD";
    private Encryption encrypt;

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
            String sql = "SELECT username,password FROM userTest WHERE username='" + user + "' AND password='" + pass + "'";
            rs = st.executeQuery(sql);

//          Bliver ikke brugt endnu, mening er at programmet skal vise en boks med hhv. om man er logget ind eller om login info er forkert.
//          Mikkel får muligvis kigget på dette indenfor en overskuelig fremtid.
//            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
//            Alert alert2 = new Alert(Alert.AlertType.ERROR);
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

            /* Should be deleted, there should never be the same user/data twice in a database */
//          If there is more of the same user in the database
//            if (count > 1) {
//                System.out.println("FEJL"); 
//            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return "Login check failed!";
    }
    
    public void createUser(int userID, String username, String password, String role) {
        try {
            Statement st = connection.createStatement();
            String sql = "INSERT INTO usertest " 
                    + "(userid,username,password,role)" 
                    + " VALUES " 
                    + "(" + userID + ',' + "'" + username + "'" + ',' + "'" + password + "'" + ',' + "'" + role + "')";
            st.execute(sql);
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            System.out.println("User created");
        }
    }
}
