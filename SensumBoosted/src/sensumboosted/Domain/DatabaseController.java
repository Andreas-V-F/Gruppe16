/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * Mikkel har lavet denne linje som test og skal fjernes igen
 */
package sensumboosted.Domain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Mikkel Hoeyberg
 */
public class DatabaseController {

    // Localhost som kan styres via pgAdmin 4
    private final String url = "jdbc:postgresql://localhost:5432/example";
    private final String userDB = "postgres";
    private final String passDB = "postgres";

    public DatabaseController() {
    }

    private ResultSet rs = null;
    private Connection connection = null;

    public Connection connect() {
        try {
            connection = DriverManager.getConnection(url, userDB, passDB);
            System.out.println("Connected to the PostgreSQL server successfully.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }

    public String CheckLogin(String user, String pass) {
        System.out.println("Checking login in progress, please wait.");
        try {
            Statement st = connection.createStatement();
            String sql = "SELECT username,password FROM sbuserdb WHERE username='" + user + "' AND password='" + pass + "'";
            rs = st.executeQuery(sql);

//          Bliver ikke brugt endnu, mening er at programmet skal vise en boks med hhv. om man er logget ind eller om login info er forkert.
//          Mikkel får muligvis kigget på dette indenfor en overskuelig fremtid.
//            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
//            Alert alert2 = new Alert(Alert.AlertType.ERROR);
            int count = 0;
            while (rs.next()) {
                count++;
            }
//          If there is not user in the database if returns the string "Username or password is wrong!"
            if (count == 0) {
                System.out.println("No user found"); // Delete this when we hand in our code
                return "Username or password is wrong!";
            }
//          Hvis der er en og kun en "bruger"
            if (count == 1) {
                System.out.println("User found"); // Delete this when we hand in our code
                return "Succesful login";
            }
//          Hvis der er flere af samme "brugere"
//            if (count > 1) {
//                System.out.println("FEJL");
//            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return "Login check failed!";
    }
}
