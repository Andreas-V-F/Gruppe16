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
import sensumboosted.Domain.DatabaseController;

/**
 *
 * @author krute
 */
public class FXMLDocumentController implements Initializable {

//    private final String url = "jdbc:postgresql://localhost:5432/example";
//    private final String userDB = "postgres";
//    private final String passDB = "postgres";
    DatabaseController dbController = new DatabaseController();

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

        String userField = usernameField.getText();
        String passField = passwordField.getText();

        if (!userField.isEmpty() && !passField.isEmpty()) {
            dbController.connect();
            dbController.CheckLogin(userField, passField);
        } else {
            System.out.println("Username or Password is empty!");
            loginInfoLabel.setText("Username or Password is empty!");
        }
    }

    @FXML
    private void cancelBTNHandler(ActionEvent event) {
        System.exit(1);
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
