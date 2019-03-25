/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sensumboosted.GUI;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 *
 * @author krute
 */
public class FXMLDocumentController implements Initializable {

    private final String url = "jdbc:postgresql://localhost:5432/postgres";
    private final String userDB = "postgres";
    private final String passDB = "postgres";

    private Label label;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField usernameField;
    @FXML
    private Button loginBTN;
    @FXML
    private Button cancelBTN;
    @FXML
    private Label loginInfoLabel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void loginBTNHandler(ActionEvent event) {
//         Check i database om username og password er i database og hvilken rolle "brugeren" har.
//         Sæt text i loginInfoLabel, til at sige "Succesful login" eller "Username or Password is wrong!"
        /*
        Kan bruges når metoden er lavet
        While true = loginInfoLabel.setText("Succesful login");
        While !true = loginInfoLabel.setText("Username or password is wrong!");
         */

        String userField = usernameField.getText();
        String passField = passwordField.getText();

        ResultSet rs = null;
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(url, userDB, passDB);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        try {
//            Statement statement = connection.createStatement();
//            ResultSet resultSet = statement.executeQuery("SELECT username,password FROM SensumBoostedUserDB WHERE username='" + userField + "' AND password='" + passField + "'");
            Statement st = connection.createStatement();
            String sql = "SELECT username,password FROM sbuserdb WHERE username='" + userField + "' AND password='" + passField + "'";
            rs = st.executeQuery(sql);

            Alert alert1 = new Alert(AlertType.INFORMATION);
            Alert alert2 = new Alert(AlertType.ERROR);
            int count = 0;
            while (rs.next()) {
                count++;
            }

            if (count == 0) {
                System.out.println("No user found");
                loginInfoLabel.setText("Username or password is wrong!");
            }
            if (count == 1) {
                System.out.println("User found");
                loginInfoLabel.setText("Succesful login");
            }
            
            if (count > 1) {
                System.out.println("FEJL");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void cancelBTNHandler(ActionEvent event) {
        System.exit(1);
    }

    private void resetBTNHandler(ActionEvent event) {
        usernameField.clear();
        passwordField.clear();
    }

//    public Connection connect() {
//        Connection connection = null;
//        try {
//            connection = DriverManager.getConnection(url, user, pass);
//            System.out.println("Connected to the PostgreSQL server successfully.");
//
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//        return connection;
//    }
}
