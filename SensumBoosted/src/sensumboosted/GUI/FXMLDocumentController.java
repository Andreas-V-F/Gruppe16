/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sensumboosted.GUI;

import java.io.IOException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import sensumboosted.Domain.DatabaseController;
import sensumboosted.Domain.Encrypt;

/**
 *
 * @author krute
 */
public class FXMLDocumentController implements Initializable {

    DatabaseController dbController = new DatabaseController();
    FXMLLoader loader = new FXMLLoader();
    Encrypt encrypt = new Encrypt();

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

    public String getUsernameField() {
        String userField = usernameField.getText();
        return userField;
    }

    public String getPasswordField() throws NoSuchAlgorithmException {
        String passField = passwordField.getText();
        return passField;
    }
    
    //Metode der krypterer password til SHA-256
//    public String cryptPassword(String password) {
//        StringBuffer sb = new StringBuffer();
//        MessageDigest md;
//        try {
//            md = MessageDigest.getInstance("SHA-256");
//            md.update(password.getBytes());
//
//            byte[] digest = md.digest();
//
//            for (byte b : digest) {
//                sb.append(String.format("%02x", b & 0xff));
//            }
//        } catch (NoSuchAlgorithmException ex) {
//            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        return sb.toString().toUpperCase();
//    }

    @FXML
    private void loginBTNHandler(ActionEvent event) {

        Boolean j = false;
        j = TestTest();
//
//        // Checks if the username field and password field is empty and sets  Boolean j to true or false if the user exist in the database
//        if (!getUsernameField().isEmpty() && !getPasswordField().isEmpty()) {
//            dbController.connect();
//            String s = dbController.CheckLogin(getUsernameField(), getPasswordField());
//            loginInfoLabel.setText(s);
//            // Dont think this is the right way to check!!
//            if ("Succesful login".equals(s)) {
//                j = true;
//            }
//            System.out.println(j.toString());
//        } else {
//            System.out.println("Username or Password is empty!");
//            loginInfoLabel.setText("Username or Password is empty!");
//            System.out.println(j.toString());
//        }
        if (j) {
            // Hide this current window (if this is what you want)
            ((Node) (event.getSource())).getScene().getWindow().hide();
            LoadDiaryWindow();
        }
    }

    // Måske skal der trækkes noget ud af loginBTNHanlder, så metoderne under kan laves? Udfordingen er at det er et ActionEvent og KeyEvent.
    @FXML
    private void cancelBTNHandler(ActionEvent event) {
        System.exit(1);
    }

    @FXML
    private void passwordFieldHandler(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            TestTest();
//            loginBTNHandler(event);
            //TODO - Should load the loginBTNHandler
        }
    }

    @FXML
    private void usernameFieldHandler(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            TestTest();
//            loginBTNHandler(event);
            //TODO - Should load the loginBTNHandler
        }
    }

    private boolean TestTest() {

        Boolean j = false;

        try {
            // Checks if the username field and password field is empty and sets Boolean j to true or false if the user exist in the database
            if (!getUsernameField().isEmpty() && !getPasswordField().isEmpty()) {
                dbController.connect();
                String s;
                try {
                    s = dbController.CheckLogin(getUsernameField(), encrypt.encryptPassword(getPasswordField()));
                    loginInfoLabel.setText(s);
                    if ("Succesful login".equals(s)) {
                        j = true;
                        
                        System.out.println(j.toString());
                    } else {
                        System.out.println("Username or Password is empty!");
                        loginInfoLabel.setText("Username or Password is empty!");
                        System.out.println(j.toString());
                    }
                } catch (NoSuchAlgorithmException ex) {
                    Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                // Dont think this is the right way to check!!
//        if (j) {
//            // Hide this current window (if this is what you want)
//            ((Node) (event.getSource())).getScene().getWindow().hide();
//            LoadDiaryWindow();  
//        }
            
            }
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return j;
    }

//    Loads a new window for the diary.
    private void LoadDiaryWindow() {
        loader.setLocation(getClass().getResource("DiaryDocument.fxml"));
        try {
            loader.load();
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        DiaryDocumentController DiaryDisplay = loader.getController();
        DiaryDisplay.setText(getUsernameField());
        Parent p = loader.getRoot();
        Stage stage = new Stage();
        stage.setTitle("Diary window");
        stage.setScene(new Scene(p));
        stage.showAndWait();
    }

}
