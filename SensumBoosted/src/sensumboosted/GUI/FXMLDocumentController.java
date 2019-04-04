/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sensumboosted.GUI;

import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sensumboosted.Domain.DatabaseController;
import sensumboosted.Domain.Encryption;

/**
 *
 * @author krute
 */
public class FXMLDocumentController implements Initializable {

    DatabaseController dbController = new DatabaseController();
    FXMLLoader loader = new FXMLLoader();
    Encryption encrypt = new Encryption();

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
    @FXML
    private Pane loginPane;
    @FXML
    private Pane diaryPane;
    @FXML
    private Button createUserBtn;
    @FXML
    private Pane createUserPane;
    @FXML
    private TextField createUsernameField;
    @FXML
    private TextField createPasswordField;
    @FXML
    private ChoiceBox<String> createUserTypeChoiceBox;
    @FXML
    private Button createBtn;
    @FXML
    private Label insertDbLabel;
    @FXML
    private TextField createFirstnameField;
    @FXML
    private TextField createLastnameField;
    @FXML
    private TextField createMiddlenameField;
    @FXML
    private TextField createCPRField;
    @FXML
    private TextField createAddressField;
    @FXML
    private TextField createPostalCodeField;
    @FXML
    private TextField createCityField;
    @FXML
    private TextField createEmailField;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        dbController.connect();
    }

    public String getUsernameField() {
        String userField = usernameField.getText();
        return userField;
    }

    public String getPasswordField() throws NoSuchAlgorithmException {
        String passField = passwordField.getText();
        return passField;
    }

    @FXML
    private void loginBTNHandler(ActionEvent event) {

        Boolean j = false;
        j = TestTest();
//
//        // Checks if the username field and password field is empty and sets  Boolean j to true or false if the user exist in the database
//        if (!getUsernameField().isEmpty() && !getPasswordField().isEmpty()) {
//            dbController.connect();
//            String s = dbController.checkLogin(getUsernameField(), getPasswordField());
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
            loadDiaryScene();
        }
    }

    @FXML
    private void createUserBtnEventHandler(ActionEvent event) {
        createUserScene();
    }

    @FXML
    private void createBtnEventHandler(ActionEvent event) {
        if (!createUsernameField.getText().isEmpty() && !createPasswordField.getText().isEmpty() && !createUserTypeChoiceBox.getItems().isEmpty()) {
            dbController.createUser((dbController.getUserIDCount() + 1), createUsernameField.getText(), encrypt.encryptString(createPasswordField.getText()), createUserTypeChoiceBox.getValue());
            insertDbLabel.setText("User created in database!");
        }
        
        if (!createFirstnameField.getText().isEmpty() && !createMiddlenameField.getText().isEmpty() && !createLastnameField.getText().isEmpty()
                && !createCPRField.getText().isEmpty() && !createAddressField.getText().isEmpty() && !createPostalCodeField.getText().isEmpty()
                && !createCityField.getText().isEmpty() && !createEmailField.getText().isEmpty()) {
            dbController.createUserInformation((dbController.getUserIDCount()), createFirstnameField.getText(), createMiddlenameField.getText(),
                    createLastnameField.getText(), Integer.parseInt(createCPRField.getText()),createAddressField.getText(), Integer.parseInt(createPostalCodeField.getText()), createCityField.getText(),
                    createEmailField.getText());
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
            if (TestTest()) {

                loadDiaryScene();
            }
//            loginBTNHandler(event);
            //TODO - Should load the loginBTNHandler
        }
    }

    @FXML
    private void usernameFieldHandler(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            if (TestTest()) {

                loadDiaryScene();
            }

//            loginBTNHandler(event);
            //TODO - Should load the loginBTNHandler
        }
    }

    private boolean TestTest() {

        Boolean j = false;

        try {
            // Checks if the username field and password field is empty and sets Boolean j to true or false if the user exist in the database
            if (!getUsernameField().isEmpty() && !getPasswordField().isEmpty()) {
                String s;
                try {
                    s = dbController.checkLogin(getUsernameField(), encrypt.encryptString(getPasswordField()));
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
//            loadDiaryWindow();  
//        }
            }
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return j;
    }

//    Loads a new window for the diary.
    private void loadDiaryWindow() {
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

    private void loadDiaryScene() {
        loginPane.setVisible(false);
        loginPane.setDisable(true);
        diaryPane.setVisible(true);
        diaryPane.setDisable(false);
    }

    private void createUserScene() {
        String[] userType = {"Adminstrator", "Sagsbehandler", "Medicinansvarlig", "Vikar", "Pædagog", "Pårørerende"};

        createUserTypeChoiceBox.getItems().addAll(userType);

        diaryPane.setVisible(false);
        diaryPane.setDisable(true);
        createUserPane.setVisible(true);
        createUserPane.setDisable(false);
    }

}
