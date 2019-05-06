/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sensumboosted.GUI;

import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sensumboosted.Domain.DatabaseController;
import sensumboosted.Domain.Encryption;
import sensumboosted.Domain.Log;
import sensumboosted.Domain.UserAccount;
import sensumboosted.Domain.UserInformation;

/**
 *
 * @author krute
 */
public class FXMLDocumentController implements Initializable {

    private DatabaseController dbController = new DatabaseController();
    private FXMLLoader loader = new FXMLLoader();
    private Encryption encrypt = new Encryption();

//    private final static Log logger = new Log("LogTest.txt");
//    private Log myLog = new Log(fileName);
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
    private Button createToUserSettingBtn;
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
    @FXML
    private Pane userSettingPane;
    @FXML
    private Button userAccountBtn;
    @FXML
    private Button userInformationBtn;
    @FXML
    private TableView<UserAccount> usersTableView;
    @FXML
    private TableColumn<UserAccount, Integer> userIDColumn;
    @FXML
    private TableColumn<UserAccount, String> usernameColumn;
    @FXML
    private TableColumn<UserAccount, String> passwordColumn;
    @FXML
    private TableColumn<UserAccount, String> userTypeColumn;
    @FXML
    private TableView<UserInformation> userInformationTableView;
    @FXML
    private TableColumn<UserInformation, Integer> userIDInfoColumn;
    @FXML
    private TableColumn<UserInformation, String> firstNameColumn;
    @FXML
    private TableColumn<UserInformation, String> middleNameColumn;
    @FXML
    private TableColumn<UserInformation, String> lastNameColumn;
    @FXML
    private TableColumn<UserInformation, Integer> cprColumn;
    @FXML
    private TableColumn<UserInformation, String> addressColumn;
    @FXML
    private TableColumn<UserInformation, Integer> postalCodeColumn;
    @FXML
    private TableColumn<UserInformation, String> cityColumn;
    @FXML
    private TableColumn<UserInformation, String> emailColumn;
    @FXML
    private Button updateBtn;
    @FXML
    private TextField searchUserTextField;
    @FXML
    private Button searchUserBtn;
    @FXML
    private Pane editUserPane;
    @FXML
    private TextField editFirstnameField;
    @FXML
    private TextField editMiddlenameField;
    @FXML
    private TextField editLastnameField;
    @FXML
    private TextField editCPRField;
    @FXML
    private TextField editAddressField;
    @FXML
    private TextField editPostalCodeField;
    @FXML
    private TextField editCityField;
    @FXML
    private TextField editEmailField;
    @FXML
    private Button editUserBtn;

    ObservableList<UserAccount> obListUA = FXCollections.observableArrayList();
    ObservableList<UserInformation> obListUI = FXCollections.observableArrayList();

    private Connection con = dbController.connect();

    //User information
    private String firstname;
    private String middlename;
    private String lastname;
    private int cpr;
    private String address;
    private int postal_code;
    private String city;
    private String email;
    private String username;
    private String password;
    private String usertype;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
// Måske ikke nødvendigt at connect. Kun bruges, hvis der skal kommunikeres med databasen
// og det gør de brugte metoder i dbController allerede.
//        dbController.connect();
        loginInfoLabel.setAlignment(Pos.CENTER);
//        userAccountTableView();
//        userInformationTableView();
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
    }

    @FXML
    private void userSettingBtnHandler(ActionEvent event) {
        userSettingScene();

        int userid = dbController.getUserID();
        String kage = Integer.toString(userid);

    }

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
        }
    }

    @FXML
    private void usernameFieldHandler(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            if (TestTest()) {
                loadDiaryScene();
            }
        }
    }

    @FXML
    private void searchUserBtnHandler(ActionEvent event) {
        if (!searchUserTextField.getText().isEmpty()) {
            editUserPane.setVisible(true);
            editUserPane.setDisable(false);

            editFirstnameField.setText(getFirstname());
            editMiddlenameField.setText(getMiddlename());
            editLastnameField.setText(getLastname());
            editCPRField.setText(Integer.toString(getCPR()));
            editAddressField.setText(getAddress());
            editPostalCodeField.setText(Integer.toString(getPostalCode()));
            editCityField.setText(getCity());
            editEmailField.setText(getEmail());

        }
    }

    public String getFirstname() {
        try {
            ResultSet rs = con.createStatement().executeQuery("SELECT firstname FROM user_information WHERE cpr = " + searchUserTextField.getText());

            while (rs.next()) {
                firstname = rs.getString("firstname");
            }

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return firstname;
    }

    public String getMiddlename() {
        try {
            ResultSet rs = con.createStatement().executeQuery("SELECT middlename FROM user_information WHERE cpr = " + Integer.parseInt(searchUserTextField.getText()));

            while (rs.next()) {
                middlename = rs.getString("middlename");
            }

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return middlename;
    }

    public String getLastname() {
        try {
            ResultSet rs = con.createStatement().executeQuery("SELECT lastname FROM user_information WHERE cpr = " + Integer.parseInt(searchUserTextField.getText()));

            while (rs.next()) {
                lastname = rs.getString("lastname");
            }

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lastname;
    }

    public int getCPR() {
        try {
            ResultSet rs = con.createStatement().executeQuery("SELECT cpr FROM user_information WHERE cpr = " + Integer.parseInt(searchUserTextField.getText()));

            while (rs.next()) {
                cpr = rs.getInt("cpr");
            }

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cpr;
    }

    public String getAddress() {
        try {
            ResultSet rs = con.createStatement().executeQuery("SELECT address FROM user_information WHERE cpr = " + Integer.parseInt(searchUserTextField.getText()));

            while (rs.next()) {
                address = rs.getString("address");
            }

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return address;
    }

    public int getPostalCode() {
        try {
            ResultSet rs = con.createStatement().executeQuery("SELECT postal_code FROM user_information WHERE cpr = " + Integer.parseInt(searchUserTextField.getText()));

            while (rs.next()) {
                postal_code = rs.getInt("postal_code");
            }

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return postal_code;
    }

    public String getCity() {
        try {
            ResultSet rs = con.createStatement().executeQuery("SELECT city FROM user_information WHERE cpr = " + Integer.parseInt(searchUserTextField.getText()));

            while (rs.next()) {
                city = rs.getString("city");
            }

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return city;

    }

    public String getEmail() {
        try {
            ResultSet rs = con.createStatement().executeQuery("SELECT email FROM user_information WHERE cpr = " + Integer.parseInt(searchUserTextField.getText()));

            while (rs.next()) {
                email = rs.getString("email");
            }

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return email;
    }

    @FXML
    private void editUserBtnHandler(ActionEvent event) {
        if (editFirstnameField.getText() != getFirstname() || editMiddlenameField.getText() != getMiddlename()
                || editLastnameField.getText() != getLastname() || editCPRField.getText() != Integer.toString(getCPR())
                || editAddressField.getText() != Integer.toString(getPostalCode()) || editCityField.getText() != getCity()
                || editEmailField.getText() != getEmail()) {
            updateUserInformation();
        }
    }

    private void updateUserInformation() {
        try {
            ResultSet rs = con.createStatement().executeQuery("UPDATE user_information "
                    + "SET firstname = '" + editFirstnameField.getText() + "', middlename = '" + editMiddlenameField.getText() + "',"
                    + "lastname = '" + editLastnameField.getText() + "', cpr = " + Integer.parseInt(editCPRField.getText()) + ","
                    + "address = '" + editAddressField.getText() + "', postal_code = " + Integer.parseInt(editPostalCodeField.getText()) + ","
                    + "city = '" + editCityField.getText() + "', email = '" + editEmailField.getText() + "'"
                    + " WHERE cpr = " + Integer.parseInt(searchUserTextField.getText()));
            rs.close();

        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

// Should be renamed!
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

    public void userSettingScene() {

        diaryPane.setVisible(false);
        diaryPane.setDisable(true);
        userSettingPane.setVisible(true);
        userSettingPane.setDisable(false);
    }

    private void createUserScene() {
        String[] userType = {"Adminstrator", "Sagsbehandler", "Medicinansvarlig", "Vikar", "Pædagog", "Pårørerende"};

        createUserTypeChoiceBox.getItems().addAll(userType);

        userSettingPane.setVisible(false);
        userSettingPane.setDisable(true);
        createUserPane.setVisible(true);
        createUserPane.setDisable(false);
    }

    @FXML
    private void logoutBTNHandler(ActionEvent event) {
        usernameField.clear();
        passwordField.clear();
        loginInfoLabel.setText("");
        loginPane.setVisible(true);
        loginPane.setDisable(false);
        diaryPane.setVisible(false);
        diaryPane.setDisable(true);
    }

    @FXML
    private void createToUserSettingHandler(ActionEvent event) {
        userSettingPane.setVisible(true);
        userSettingPane.setDisable(false);
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

//    private void userAccountTableView() {
//
//        try {
//            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM users");
//
//            while (rs.next()) {
//                obListUA.add(new UserAccount(rs.getInt("user_id"), rs.getString("username"),
//                        rs.getString("password"), rs.getString("user_type")));
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        userIDColumn.setCellValueFactory(new PropertyValueFactory<>("userid"));
//        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
//        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
//        userTypeColumn.setCellValueFactory(new PropertyValueFactory<>("usertype"));
//
//        usersTableView.setItems(obListUA);
//        usersTableView.setEditable(true);
//    }
//    private void userInformationTableView() {
//        try {
//            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM user_information");
//            while (rs.next()) {
//                obListUI.add(new UserInformation(rs.getInt("user_id"), rs.getString("firstname"),
//                        rs.getString("middlename"), rs.getString("lastname"), rs.getInt("cpr"),
//                        rs.getString("address"), rs.getInt("postal_code"), rs.getString("city"),
//                        rs.getString("email")));
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        userIDInfoColumn.setCellValueFactory(new PropertyValueFactory<>("userid"));
//        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
//        middleNameColumn.setCellValueFactory(new PropertyValueFactory<>("middleName"));
//        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
//        cprColumn.setCellValueFactory(new PropertyValueFactory<>("cpr"));
//        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
//        postalCodeColumn.setCellValueFactory(new PropertyValueFactory<>("postalcode"));
//        cityColumn.setCellValueFactory(new PropertyValueFactory<>("city"));
//        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
//
//        userInformationTableView.setItems(obListUI);
//        userInformationTableView.setEditable(true);
//    }
    public void userAccountBtnHandler(ActionEvent event) {
        usersTableView.setVisible(true);
        userInformationTableView.setVisible(false);
    }

    public void userInformationBtnHandler(ActionEvent event) {
        userInformationTableView.setVisible(true);
        usersTableView.setVisible(false);
    }

    public void updateBtnEventHandler(ActionEvent event) {
        usersTableView.getItems().clear();
        userInformationTableView.getItems().clear();
//        userInformationTableView();
//        userAccountTableView();

    }
}
