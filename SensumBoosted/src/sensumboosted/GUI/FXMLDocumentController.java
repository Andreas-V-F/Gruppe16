/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sensumboosted.GUI;

import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import sensumboosted.Persistence.DatabaseController;
import sensumboosted.Domain.Encryption;
import sensumboosted.Persistence.Log;

/**
 *
 * @author krute
 */
public class FXMLDocumentController implements Initializable {

    private DatabaseController dbController = new DatabaseController();
    private FXMLLoader loader = new FXMLLoader();
    private Encryption encrypt = new Encryption();
    private Log myLog;

    private Label label; // Is this even used??

    @FXML
    private ChoiceBox<String> createUserTypeChoiceBox;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Pane createUserPane;
    @FXML
    private Pane loginPane;
    @FXML
    private Pane diaryPane;
    @FXML
    private Label insertDbLabel;
    @FXML
    private Label loginInfoLabel;
    @FXML
    private Label createUsernameLabel;
    @FXML
    private Label createPasswordLabel;
    @FXML
    private Label createUserTypeLabel;
    @FXML
    private Button loginBTN;
    @FXML
    private Button cancelBTN;
    @FXML
    private Button logoutBTN;
    @FXML
    private Button createUserBtn;
    @FXML
    private Button createBtn;
    @FXML
    private Button backToDiaryPaneFromCreate;
    @FXML
    private TextField createUsernameField;
    @FXML
    private TextField createPasswordField;
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
    @FXML
    private TextField usernameField;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dbController.connect();
        loginInfoLabel.setAlignment(Pos.CENTER);
        String[] userType = {"Adminstrator", "Sagsbehandler", "Medicinansvarlig", "Vikar", "Pædagog", "Pårørerende"};

        createUserTypeChoiceBox.getItems().addAll(userType);
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

        loginInfoLabel.setText("");
        Boolean j = false;
        j = TestTest();
        if (j) {
            setWindowSizeDiary(event);
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
                    createLastnameField.getText(), Integer.parseInt(createCPRField.getText()), createAddressField.getText(), Integer.parseInt(createPostalCodeField.getText()), createCityField.getText(),
                    createEmailField.getText());
        }
        clearCreateTextField();
    }

    @FXML
    private void cancelBTNHandler(ActionEvent event) {
        System.exit(1);
    }

    @FXML
    private void passwordFieldHandler(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            if (TestTest()) {
                setWindowSizeDiary(event);
                loadDiaryScene();
            }
        }
    }

    @FXML
    private void usernameFieldHandler(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            if (TestTest()) {
                setWindowSizeDiary(event);
                loadDiaryScene();
            }
        }
    }

// Should be renamed! and dont think this method should do so much logic.
    private boolean TestTest() {
        Boolean j = false;
        String s;
        String logName;
        String attempt;
        try {
            // Checks if the username field and password field is empty and sets Boolean j to true or false if the user exist in the database
            if (!getUsernameField().isEmpty() && !getPasswordField().isEmpty()) {
                try {
                    s = dbController.checkLogin(getUsernameField(), encrypt.encryptString(getPasswordField()));
                    loginInfoLabel.setText(s);
                    if ("Succesful login".equals(s)) {
                        logName = getUsernameField();
                        attempt = "har logget ind i systemet.";
                        logLoginAttempt(logName, attempt);
                        j = true;
                        System.out.println(j.toString()); // Only to see what happens in the console
                    } else {
                        logName = getUsernameField();
                        attempt = "har forsøgt at logge ind";
                        logLoginAttempt(logName, attempt);
                        System.out.println("Oplysninger er ikke udfyldt korrekt!"); // Only to see what happens in the console
                        loginInfoLabel.setText("Oplysninger er ikke udfyldt korrekt!");
                        System.out.println(j.toString());  // Only to see what happens in the console
                    }
                } catch (NoSuchAlgorithmException ex) {
                    Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (getUsernameField().isEmpty() && getPasswordField().isEmpty()) {
                logName = "INTET BRUGERNAVN INDTASTET";
                attempt = "har forsøgt at logge ind";
                logLoginAttempt(logName, attempt);
                System.out.println("Felterne er ikke udfyldt!"); // Only to see what happens in the console
                loginInfoLabel.setText("Felterne er ikke udfyldt!");
            } else {
                if (getUsernameField().isEmpty()) {
                    logName = "INTET BRUGERNAVN INDTASTET";
                } else {
                    logName = getUsernameField();
                }
                attempt = "har forsøgt at logge ind";
                logLoginAttempt(logName, attempt);
                System.out.println("Et af felterne er ikke udfyldt!"); // Only to see what happens in the console
                loginInfoLabel.setText("Et af felterne er ikke udfyldt!");
            }
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return j;
    }

//    This should be deleted if we use the same stage and not creating a new stage
//    Loads a new window for the diary.
//    private void loadDiaryWindow() {
//        loader.setLocation(getClass().getResource("DiaryDocument.fxml"));
//        try {
//            loader.load();
//        } catch (IOException ex) {
//            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        DiaryDocumentController DiaryDisplay = loader.getController();
//        DiaryDisplay.setText(getUsernameField());
//        Parent p = loader.getRoot();
//        Stage stage = new Stage();
//        stage.setTitle("Diary window");
//        stage.setScene(new Scene(p));
//        stage.showAndWait();
//    }
    private void loadDiaryScene() {
        loginPane.setVisible(false);
        loginPane.setDisable(true);
        diaryPane.setVisible(true);
        diaryPane.setDisable(false);
    }

    private void createUserScene() {
        diaryPane.setVisible(false);
        diaryPane.setDisable(true);
        createUserPane.setVisible(true);
        createUserPane.setDisable(false);
    }

    @FXML
    private void logoutBTNHandler(ActionEvent event) {
        usernameField.clear();
        passwordField.clear();
        loginInfoLabel.setText("");
        setWindowSizeLogin(event);
        loginPane.setVisible(true);
        loginPane.setDisable(false);
        diaryPane.setVisible(false);
        diaryPane.setDisable(true);
    }

    @FXML
    private void backToDiaryPaneFromCreateHandler(ActionEvent event) {
        clearCreateTextField();
        diaryPane.setVisible(true);
        diaryPane.setDisable(false);
        createUserPane.setVisible(false);
        createUserPane.setDisable(true);
    }

    private void logLoginAttempt(String username, String attempt) {
        try {
            myLog = new Log("LoginLog.txt");
            myLog.getLogger().setLevel(Level.ALL);
            myLog.getLogger().info("Bruger: \"" + username + "\" " + attempt);
        } catch (SecurityException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void clearCreateTextField() {
        createUsernameField.clear();
        createPasswordField.clear();
        createFirstnameField.clear();
        createLastnameField.clear();
        createMiddlenameField.clear();
        createCPRField.clear();
        createAddressField.clear();
        createPostalCodeField.clear();
        createCityField.clear();
        createEmailField.clear();
    }

    // Dont change the window to the right size.
    private void setWindowSizeLogin(Event event) {
        ((Node) (event.getSource())).getScene().getWindow().setWidth(299);
        ((Node) (event.getSource())).getScene().getWindow().setHeight(250);
    }

    private void setWindowSizeDiary(Event event) {
        ((Node) (event.getSource())).getScene().getWindow().setWidth(1000);
        ((Node) (event.getSource())).getScene().getWindow().setHeight(700);
    }
}
