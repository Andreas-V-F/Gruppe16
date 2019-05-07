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
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import sensumboosted.Domain.Admin;
import sensumboosted.Domain.CaseWorker;
import sensumboosted.Persistence.DatabaseController;
import sensumboosted.Domain.Encryption;
import sensumboosted.Domain.LogEntry;
import sensumboosted.Domain.Permission;
import sensumboosted.Domain.User;
import sensumboosted.Persistence.Log;
import sensumboosted.Domain.UserAccount;
import sensumboosted.Domain.UserInformation;

/**
 *
 * @author krute
 */
public class FXMLDocumentController implements Initializable {

    private User currentUser;
//    private CaseWorker cw;
//    private Admin admin;
    private Alert alert;

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
    private TextArea infoBox;
    @FXML
    private TextArea textArea;
    @FXML
    private Pane casePane;
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
    private Button userSettingToDiaryPaneBtn;

    ObservableList<UserAccount> obListUA = FXCollections.observableArrayList();
    ObservableList<UserInformation> obListUI = FXCollections.observableArrayList();
    ObservableList<UserAccount> obListCT = FXCollections.observableArrayList();
    ObservableList<LogEntry> obListLE = FXCollections.observableArrayList();

    private Connection con = dbController.connect();
    @FXML
    private TableView<UserAccount> citizenTableView;
    @FXML
    private TableColumn<UserAccount, String> citizenName;
    @FXML
    private TableColumn<UserAccount, Integer> citizenId;
    @FXML
    private Pane logbookPane;
    @FXML
    private TextArea logbookTextField;
    @FXML
    private Button saveLogbookButton;
    @FXML
    private TableView<LogEntry> logEntryTableView;
    @FXML
    private TableColumn<LogEntry, String> text;
    @FXML
    private Button DeleteLogbookBTN;
    @FXML
    private Button caseBackBTN;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loginInfoLabel.setAlignment(Pos.CENTER);
        String[] userType = {"Adminstrator", "Sagsbehandler", "Medicinansvarlig", "Vikar", "Pædagog", "Pårørerende"};

        createUserTypeChoiceBox.getItems().addAll(userType);
        userAccountTableView();
        userInformationTableView();
        citizenTableView();
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
//        createUser();
        loginInfoLabel.setText("");
        Boolean j = false;
        j = TestTest();
        if (j) {

            setWindowSizeDiary(event);
            loadDiaryScene();
        }
    }

//    private void createUser() {
//        if (true) {
//
//        };
//
//    }
    @FXML
    private void createUserBtnEventHandler(ActionEvent event) {
        // Maybe make a switch with all the "roles" we got??
        if ("admin".equals(currentUser.getRole())) { //
            System.out.println("admin rolle");
            if (currentUser.hasPermission(Permission.adminPermits)) {
                createUserScene();
            } else {
                System.out.println("admin don't have the permit");
            }
            if ("medicin".equals(currentUser.getRole())) {
                System.out.println("medicin rolle");
                if (currentUser.hasPermission(Permission.adminPermits)) {
                    createUserScene();
                } else {
                    System.out.println("medicin don't have permit");
                }
            }
        }
    }

    /*
    if ("admin".equals(comboBox.getSelectionModel().getSelectedItem())) {
            if (admin.hasPermission(abc.adminBTN)) {
                nextWindowLabel.setAlignment(Pos.CENTER);
                nextWindowLabel.setText("you have clicked the admin button");
                alertCaller("You are the admin", Alert.AlertType.INFORMATION);
                
            } else {
                nextWindowLabel.setAlignment(Pos.CENTER);
                nextWindowLabel.setText("you dont have permission for this");
            }
        }
        if ("case worker".equals(comboBox.getSelectionModel().getSelectedItem())) {
            if (cw.hasPermission(abc.adminBTN)) {
                nextWindowLabel.setAlignment(Pos.CENTER);
                nextWindowLabel.setText("you have clicked the admin button");
            } else {
                nextWindowLabel.setAlignment(Pos.CENTER);
                nextWindowLabel.setText("you dont have permission for this");
                alertCaller("You dont have permission for this, contact your admin", Alert.AlertType.WARNING);
            }
        }
     */
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
    private void userSettingBtnHandler(ActionEvent event) {
        userSettingScene();

        int userid = dbController.getUserIDCount();
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

    @FXML
    private void userSettingToDiaryPaneBtnHandler(ActionEvent event) {
        userSettingToDiaryPane();
    }

// Should be renamed! and dont think this method should do so much logic. should be moved
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

                        try {
                            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM users WHERE username='" + getUsernameField() + "'");
                            while (rs.next()) {
                                String sqlString = rs.getString("user_type");
                                if ("Administrator".equals(sqlString)) {
                                    currentUser = new Admin(getUsernameField(), "admin");
                                    System.out.println(currentUser.toString());
                                    System.out.println("ADMIN");
                                }
                                if ("Medicinansvarlig".equals(sqlString)) {
                                    currentUser = new CaseWorker(getUsernameField(), "medicin");
                                    System.out.println(currentUser.toString());
                                    System.out.println("MEDICIN");
                                }
                            }
                        } catch (SQLException ex) {
                            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                            System.out.println("DEN BLIVER FANGET HER");
                        }

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

    public void userSettingToDiaryPane() {
        userSettingPane.setVisible(false);
        userSettingPane.setDisable(true);
        diaryPane.setVisible(true);
        diaryPane.setDisable(false);
    }

    private void createUserScene() {
        userSettingPane.setVisible(false);
        userSettingPane.setDisable(true);
        createUserPane.setVisible(true);
        createUserPane.setDisable(false);
    }

    private void resetCurrentUser() {

        currentUser = null;
//        cw = null;
//        admin = null;

    }

    @FXML
    private void logoutBTNHandler(ActionEvent event) {

        resetCurrentUser();

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
    private void backToUserSettingFromCreateHandler(ActionEvent event) {
        clearCreateTextField();
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

    @FXML
    void handleUpdateAction(ActionEvent event) {
        String[] info = dbController.getUserInformation();
        String userID = info[0];
        String Firstname = info[1];
        String Middlename = info[2];
        String Lastname = info[3];
        String CPR = info[4];
        String Address = info[5];
        String PostalCode = info[6];
        String City = info[7];
        String Email = info[8];

        infoBox.setText("Fulde navn: " + Firstname + " " + Middlename + " " + Lastname + "\n"
                + "CPR nr: " + CPR + "\n"
                + "Adresse: " + Address + ", " + City + ", " + PostalCode);
    }

    @FXML
    void handleGemActon(ActionEvent event) {
        String[] info = dbController.getUserInformation();
        int userID = Integer.parseInt(info[0]);
        String input = textArea.getText();
        if (dbController.hasOpenCase(userID)) {
            System.out.println(input);
            dbController.saveCase(dbController.findCaseID(userID), userID, input);
        } else {
            dbController.createCase(userID, input);
        }
    }

    @FXML
    void handleCloseAction(ActionEvent event) {
        String[] info = dbController.getUserInformation();
        int userID = Integer.parseInt(info[0]);
        dbController.closeAllCases(userID);
    }

    // Dont change the window to the right size.
    private void setWindowSizeLogin(Event event) {
        ((Node) (event.getSource())).getScene().getWindow().setWidth(310);
        ((Node) (event.getSource())).getScene().getWindow().setHeight(250);
    }

    private void setWindowSizeDiary(Event event) {
        ((Node) (event.getSource())).getScene().getWindow().setWidth(1100);
        ((Node) (event.getSource())).getScene().getWindow().setHeight(800);
    }

    @FXML
    private void openCaseHandler(ActionEvent event) {
        diaryPane.setDisable(true);
        diaryPane.setVisible(false);
        casePane.setVisible(true);
        casePane.setDisable(false);
    }

    private void userAccountTableView() {

        try {
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM users");

            while (rs.next()) {
                obListUA.add(new UserAccount(rs.getInt("user_id"), rs.getString("username"),
                        rs.getString("password"), rs.getString("user_type")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }

        userIDColumn.setCellValueFactory(new PropertyValueFactory<>("userid"));
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
        userTypeColumn.setCellValueFactory(new PropertyValueFactory<>("usertype"));

        usersTableView.setItems(obListUA);
        usersTableView.setEditable(true);
    }

    private void userInformationTableView() {
        try {
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM user_information");
            while (rs.next()) {
                obListUI.add(new UserInformation(rs.getInt("user_id"), rs.getString("firstname"),
                        rs.getString("middlename"), rs.getString("lastname"), rs.getInt("cpr"),
                        rs.getString("address"), rs.getInt("postal_code"), rs.getString("city"),
                        rs.getString("email")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }

        userIDInfoColumn.setCellValueFactory(new PropertyValueFactory<>("userid"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        middleNameColumn.setCellValueFactory(new PropertyValueFactory<>("middleName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        cprColumn.setCellValueFactory(new PropertyValueFactory<>("cpr"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        postalCodeColumn.setCellValueFactory(new PropertyValueFactory<>("postalcode"));
        cityColumn.setCellValueFactory(new PropertyValueFactory<>("city"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        userInformationTableView.setItems(obListUI);
        userInformationTableView.setEditable(true);
    }

    @FXML
    public void userAccountBtnHandler(ActionEvent event) {
        usersTableView.setVisible(true);
        userInformationTableView.setVisible(false);
    }

    @FXML
    public void userInformationBtnHandler(ActionEvent event) {
        userInformationTableView.setVisible(true);
        usersTableView.setVisible(false);
    }

    @FXML
    public void updateBtnEventHandler(ActionEvent event) {
        usersTableView.getItems().clear();
        userInformationTableView.getItems().clear();
        userInformationTableView();
        userAccountTableView();
    }

    private void citizenTableView() {

        try {
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM users");

            while (rs.next()) {
                obListCT.add(new UserAccount(rs.getInt("user_id"), rs.getString("username")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }

        citizenName.setCellValueFactory(new PropertyValueFactory<>("username"));
        citizenId.setCellValueFactory(new PropertyValueFactory<>("userid"));

        citizenTableView.setItems(obListCT);
        usersTableView.setEditable(true);

    }

    @FXML
    private void logbookButtonHandler(ActionEvent event) {
        casePane.setVisible(false);
        casePane.setDisable(true);

        logbookPane.setVisible(true);
        logbookPane.setDisable(false);
    }

    @FXML
    private void backToCasePaneHandler(ActionEvent event) {
        logbookPane.setVisible(false);
        logbookPane.setDisable(true);

        casePane.setVisible(true);
        casePane.setDisable(false);
    }

    @FXML
    private void saveLogbookButtonHandler(ActionEvent event) {
        UserAccount x = citizenTableView.getSelectionModel().getSelectedItem();
        dbController.editLogBook(x.getUserid(), logbookTextField.getText());
    }

    @FXML
    private void dbClickRowHandler(MouseEvent event) {
        if (event.getClickCount() > 1) {
            System.out.println("dbClickRowHandler");
            logbookTextField.clear();
            UserAccount x = citizenTableView.getSelectionModel().getSelectedItem();
            long id = dbController.getLogBookId(dbController.getCaseId(x.getUserid()));
            System.out.println("id : " + id);
            logEntryTableView(id);
        }
    }

    private void logEntryTableView(long logbookID) {
        System.out.println("logEntryTableView id : " + logbookID);
        obListLE.clear();
        try {
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM logbook_entry WHERE logbook_id = " + logbookID + "order by create_timestamp DESC");

            while (rs.next()) {
                obListLE.add(new LogEntry(rs.getString("entry_text"), rs.getLong("logbook_entry_id")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }

        text.setCellValueFactory(new PropertyValueFactory<>("text"));

        logEntryTableView.setItems(obListLE);
        logEntryTableView.setEditable(true);

    }

    @FXML
    private void DeleteLogbookBTN(MouseEvent event) {
        System.out.println("DeleteLogbookBTN b");
        LogEntry le = logEntryTableView.getSelectionModel().getSelectedItem();
        dbController.deleteLogbookEntry(le.getLogbookId());
        System.out.println("DeleteLogbookBTN a");
    }

    @FXML
    private void caseBackBTN(MouseEvent event) {
        diaryPane.setDisable(false);
        diaryPane.setVisible(true);
        casePane.setVisible(false);
        casePane.setDisable(true);
    }

}
