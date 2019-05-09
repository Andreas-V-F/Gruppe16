/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SensumBoosted2.GUI;

import SensumBoosted2.Domain.LoginService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Mikkel Høyberg
 */
public class FXMLLoginController implements Initializable {

    private FXMLMainMenuController mainMenuController;
    private LoginService loginService;
    private Alert alert;

    @FXML
    private Pane loginPane;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button loginBTN;
    @FXML
    private Label loginInfoLabel;
    @FXML
    private AnchorPane loginAnchorPane;
    @FXML
    private Button exitBTN;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

//    
//    private void setMainMenuTopLabels(String userType, String nameOfUser, String department) {
//        mainMenuController.setTopLabels(userType, nameOfUser, department);
//    }
    private void loadAnotherFXML(Event event) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("FXMLMainMenu.fxml"));
            setMainMenuWindowSize(event);
            loginAnchorPane.getChildren().setAll(pane);
        } catch (IOException ex) {
            System.out.println("Fejl med at indlæse MainMenu FXML");
            Logger.getLogger(FXMLLoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setMainMenuWindowSize(Event event) {
        ((Node) (event.getSource())).getScene().getWindow().setWidth(1180);
        ((Node) (event.getSource())).getScene().getWindow().setHeight(738);
    }

    @FXML
    private void passwordFieldHandler(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            loginChecker(event);
        }
    }

    @FXML
    private void usernameFieldHandler(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            loginChecker(event);
        }
    }

    @FXML
    private void loginBTNHandler(ActionEvent event) {
        loginChecker(event);
    }

    @FXML
    private void loginBTNOnKeyPressedHandler(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            loginChecker(event);
        }
    }

    private void loginChecker(Event event) {
//        loadAnotherFXML(event); // Bruges når der ikke er net eller nødvendigt at tjekke databasen for login

        if (!usernameField.getText().isEmpty() && !passwordField.getText().isEmpty()) {
            createLoginUser();
            boolean b = loginService.validateLogin();
            System.out.println(b);
            if (b) {
                loadAnotherFXML(event);
            } else {
                resetFields();
//                resetLoginUser();
                alertCaller("Din bruger blev ikke godkendt!\n" + "Udfyld felterne korrekt eller kontakt din administrator.", Alert.AlertType.ERROR);
            }
        } else {
            alertCaller("En af felterne er ikke udfyldt.\n" + "Udfyld felterne og prøv igen.", Alert.AlertType.INFORMATION);
        }
    }

    public String getPasswordField() {
        return passwordField.getText();
    }

    public String getUsernameField() {
        return usernameField.getText();
    }

    private void createLoginUser() {
        loginService = new LoginService(getUsernameField(), getPasswordField());
    }

//    private void resetLoginUser() {
//        loginService = null;
//    }
    private void alertCaller(String s, Alert.AlertType type) {
        alert = new Alert(type);
        alert.setHeaderText(null);
        alert.setContentText(s);
        alert.show();
    }

    private void resetFields() {
        usernameField.clear();
        passwordField.clear();
    }

    @FXML
    private void exitBTNHandler(ActionEvent event) {
        exitSystem();
    }

    @FXML
    private void exitBTNOnKeyPressedHandler(KeyEvent event) {
        exitSystem();
    }

    private void exitSystem() {
        System.exit(1);
    }
}
