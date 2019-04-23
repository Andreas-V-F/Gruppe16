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
public class DBcontroller {

    // Localhost som kan styres via pgAdmin 4
//    private final String url = "jdbc:postgresql://localhost:5432/example";
//    private final String userDB = "postgres";
//    private final String passDB = "postgres";
    // Controlled by phpMySql
    private final String url = "jdbc:postgresql://balarama.db.elephantsql.com:5432/rsrrjzno";
    private final String userDB = "rsrrjzno";
    private final String passDB = "afVcwMqs2zGaNtod0axmHcsrAuy5u7uD";
    private Encryption encrypt;
    private int count;
    private int userid;
    private String username;
    private String password;
    private String usertype;

    public DBcontroller() {
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
            Logger.getLogger(DBcontroller.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(DBcontroller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int getUserIDCount() {
        try (Statement st = connection.createStatement()) {
            String sql = "SELECT COUNT(*) AS user_id FROM Users";

            rs = st.executeQuery(sql);
            while (rs.next()) {
                count = rs.getInt("user_id");

            }
        } catch (SQLException ex) {
            Logger.getLogger(DBcontroller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

//    public void getUserInformation() {
//        try (Statement st = connection.createStatement()) {
//            String sql = "SELECT * FROM USER_INFORMATION";
//
//            rs = st.executeQuery(sql);
//            while (rs.next()) {
//                int userid = rs.getInt("user_id");
//                String getFirstname = rs.getString("firstname");
//                String getMiddlename = rs.getString("middlename");
//                String getLastname = rs.getString("lastname");
//                int getCPR = rs.getInt("cpr");
//                String getAddress = rs.getString("address");
//                int getPostalCode = rs.getInt("postal_code");
//                String getCity = rs.getString("city");
//                String getEmail = rs.getString("email");
//
//                hasUserInformation = Integer.toString(userid) + "\t|" + getFirstname + "\t|" + getMiddlename
//                        + "\t|" + getLastname + "\t|" + Integer.toString(getCPR)
//                        + "\t|" + getAddress + "\t|" + Integer.toString(getPostalCode)
//                        + "\t|" + getCity + "\t|" + getEmail;
//
//                System.out.println(hasUserInformation);
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(DatabaseController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//    public void getUsers() {
//        try (Statement st = connection.createStatement()) {
//            String sql = "SELECT * FROM USERS";
//
//            rs = st.executeQuery(sql);
//            while (rs.next()) {
//                int userid = rs.getInt("user_id");
//                String getUsername = rs.getString("username");
//                String getPassword = rs.getString("password");
//                String getUsertype = rs.getString("user_type");
//
//                hasUser = Integer.toString(userid) + "\t|" + getUsername + "\t|"
//                        + getPassword + "\t" + getUsertype;
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(DatabaseController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }

    public int getUserID() {
        try (Statement st = connection.createStatement()) {
            String sql = "SELECT user_id FROM users";

            rs = st.executeQuery(sql);
            while (rs.next()) {
                userid = rs.getInt("user_id");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBcontroller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return userid;
    }

    public String getUsername() {
        try (Statement st = connection.createStatement()) {
            String sql = "SELECT username FROM users";

            rs = st.executeQuery(sql);
            while (rs.next()) {
                username = rs.getString("username");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBcontroller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return username;
    }

    public String getPassword() {
        try (Statement st = connection.createStatement()) {
            String sql = "SELECT password FROM users";

            rs = st.executeQuery(sql);
            while (rs.next()) {
                password = rs.getString("password");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBcontroller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return password;
    }
    
        public String getUserType() {
        try (Statement st = connection.createStatement()) {
            String sql = "SELECT user_type FROM users";

            rs = st.executeQuery(sql);
            while (rs.next()) {
                usertype = rs.getString("user_type");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBcontroller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usertype;
    }
}
