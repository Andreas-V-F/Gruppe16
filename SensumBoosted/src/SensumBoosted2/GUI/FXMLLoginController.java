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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
 * @author Bruger
 */
public class FXMLLoginController implements Initializable {

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

    // Skal nok tjekke om man er admin/superbruger også gå videre til mainmenu eller skal den loade "UserProfile"
    private void loadAnotherFXML() {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("FXMLMainMenu.fxml"));
            loginAnchorPane.getChildren().setAll(pane);
        } catch (IOException ex) {
            System.out.println("Fejl med at indlæse MainMenu FXML");
            Logger.getLogger(FXMLLoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void passwordFieldHandler(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            System.out.println("du har trykket enter på kodeord feltet");
        }
    }

    @FXML
    private void usernameFieldHandler(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            System.out.println("du har trykket enter på brugernavn feltet");
        }
    }

    @FXML
    private void loginBTNHandler(ActionEvent event) {
        createLoginUser();
        if (!usernameField.getText().isEmpty() && !passwordField.getText().isEmpty()) {
            boolean b = loginService.validateLogin();
            System.out.println(b);
            if (b) {
                System.out.println("valideret!");
                loadAnotherFXML();
            } else {
                System.out.println("Ikke valideret!");
                resetFields();
                resetLoginUser();
                alertCaller("Din bruger blev ikke godkendt!\n" + "Udfyld felterne korrekt eller kontakt din administrator.", Alert.AlertType.ERROR);
            }
        } else {
            alertCaller("En af felterne er ikke udfyldt.\n" + "Udfyld felterne og prøv igen.", Alert.AlertType.INFORMATION);
        }
    }

    @FXML
    private void loginBTNOnKeyPressedHandler(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            System.out.println("du har trykket enter på log ind knappen");
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

    private void resetLoginUser() {
        loginService = null;
    }

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
