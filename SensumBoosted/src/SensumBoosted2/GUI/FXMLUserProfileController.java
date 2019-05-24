/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SensumBoosted2.GUI;

import SensumBoosted2.Domain.CaseService;
import SensumBoosted2.Domain.CreateCitizenService;
import SensumBoosted2.Domain.StaffService;
import SensumBoosted2.Domain.UserInformation;
import SensumBoosted2.Domain.UserProfileService;
import SensumBoosted2.GUI.FXMLUserProfileController;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Bruger
 */
public class FXMLUserProfileController implements Initializable {

    private UserProfileService userProfileService;
    private UserInformation ui;

    public static boolean okPressed;

    @FXML
    private Button createCitizenBtn;
    @FXML
    private Button editUserBtn;
    @FXML
    public TextField searchUserTextField;
    @FXML
    private Button searchUserBtn;
    @FXML
    private Pane editUserPane;
    @FXML
    private Label saveStatusLabel;
    @FXML
    public TextField editFirstnameField;
    @FXML
    public TextField editMiddlenameField;
    @FXML
    public TextField editLastnameField;
    @FXML
    public TextField editPhoneField;
    @FXML
    public TextField editAddressField;
    @FXML
    public TextField editPostalCodeField;
    @FXML
    public TextField editCityField;
    @FXML
    public TextField editEmailField;
    @FXML
    private Button saveBtn;
    @FXML
    private RadioButton rbCPR;
    @FXML
    private RadioButton rbFirstname;

    public String firstname;
    public String middlename;
    public String lastname;
    public int cpr;
    public String address;
    public int postalcode;
    public String city;
    public String email;

    //Tableview FXML
    @FXML
    private TableView<?> userInformationTableView;
    @FXML
    private TableColumn<?, ?> firstnameColumn;
    @FXML
    private TableColumn<?, ?> middlenameColumn;
    @FXML
    private TableColumn<?, ?> lastnameColumn;
    @FXML
    private TableColumn<?, ?> phoneColumn;
    @FXML
    private TableColumn<?, ?> addressColumn;
    @FXML
    private TableColumn<?, ?> postalcodeColumn;
    @FXML
    private TableColumn<?, ?> cityColumn;
    @FXML
    private TableColumn<?, ?> emailColumn;
    @FXML
    private Pane userSettingPane;
    @FXML
    private ToggleGroup search;
    private StaffService staffService = new StaffService();
    @FXML
    private Button caseBtn;
    @FXML
    private Button diaryBtn;
    @FXML
    private Button medicineBtn;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private Button deleteUserBtn;
    
    CreateCitizenService createCitizenService = new CreateCitizenService();
    @FXML
    private Label errorLabel;
    @FXML
    private Button prevCaseButton;

    public FXMLUserProfileController() {
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initiateTableView();
        permissions(false);
        staffService.setUserInfo(null);
        okPressed = false;
    }

    @FXML
    private void createCitizenBtnEventHandler(ActionEvent event) {
        Stage stage;
        Parent root;
        try {
            if (event.getSource() == createCitizenBtn) {
                stage = new Stage();
                root = FXMLLoader.load(getClass().getResource("FXMLCreateCitizen.fxml"));
                stage.setScene(new Scene(root));
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.show();
            }
        } catch (IOException ex) {
            Logger.getLogger(FXMLUserProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void searchUserBtnHandler(ActionEvent event) {
        if (searchUserTextField.getText().isEmpty()) {
            initiateTableView();
        } else {
            initiateSearchTableView();
        }

    }

    @FXML
    private void saveBtnHandler(ActionEvent event) {
        ui = (UserInformation) userInformationTableView.getSelectionModel().getSelectedItem();
        saveStatusLabel.setVisible(false);
        if(!numberChecker(editPhoneField.getText()) || !postalcodeChecker(editPostalCodeField.getText()) || !emailChecker(editEmailField.getText())){
            errorLabel.setVisible(true);
            return;
        }
        
        if (!editFirstnameField.getText().equals(ui.getFirstname()) || !editMiddlenameField.getText().equals(ui.getMiddlename())
                || !editLastnameField.getText().equals(ui.getLastname()) || !editPhoneField.getText().equals(Integer.toString(ui.getPhonenumber()))
                || !editAddressField.getText().equals(Integer.toString(ui.getPostalcode())) || !editCityField.getText().equals(ui.getCity())
                || !editEmailField.getText().equals(ui.getEmail())) {

            userProfileService.saveCI(editFirstnameField.getText(), editMiddlenameField.getText(), editLastnameField.getText(),
                    Integer.parseInt(editPhoneField.getText()), editAddressField.getText(), Integer.parseInt(editPostalCodeField.getText()),
                    editCityField.getText(), editEmailField.getText(), ui.getUserid());
            saveStatusLabel.setVisible(true);
            errorLabel.setVisible(false);
            editFirstnameField.clear();
            editMiddlenameField.clear();
            editLastnameField.clear();
            editPhoneField.clear();
            editAddressField.clear();
            editPostalCodeField.clear();
            editCityField.clear();
            editEmailField.clear();
        }
        initiateTableView();

    }

    //Tableview - COLUMN
    private void initiateCols() {
        firstnameColumn.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        middlenameColumn.setCellValueFactory(new PropertyValueFactory<>("middlename"));
        lastnameColumn.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phonenumber"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        postalcodeColumn.setCellValueFactory(new PropertyValueFactory<>("postalcode"));
        cityColumn.setCellValueFactory(new PropertyValueFactory<>("city"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
    }

    //Tableview
    public void initiateTableView() {

        userProfileService = new UserProfileService();
        initiateCols();
        userInformationTableView.setItems(userProfileService.getCI());
    }

    public void initiateSearchTableView() {
        userProfileService = new UserProfileService();
        initiateCols();

        if (rbCPR.isSelected()) {
            if(!cprChecker(searchUserTextField.getText())){
                errorLabel.setVisible(true);
                return;
            }
            userInformationTableView.setItems(userProfileService.cprSearchCI(searchUserTextField.getText()));
        } else if (rbFirstname.isSelected()) {
            userInformationTableView.setItems(userProfileService.firstnameSearchCI(searchUserTextField.getText()));
        }
    }

    @FXML
    private void editUserBtnHandler(ActionEvent event) {
        ui = (UserInformation) userInformationTableView.getSelectionModel().getSelectedItem();
        
        editFirstnameField.setText(ui.getFirstname());
        editMiddlenameField.setText(ui.getMiddlename());
        editLastnameField.setText(ui.getLastname());
        editPhoneField.setText(Integer.toString(ui.getCpr()));
        editAddressField.setText(ui.getAddress());
        editPostalCodeField.setText(Integer.toString(ui.getPostalcode()));
        editCityField.setText(ui.getCity());
        editEmailField.setText(ui.getEmail());
    }

    @FXML
    private void mouseClick(MouseEvent event) {
        if (userInformationTableView.getSelectionModel().getSelectedItem() == null) {
            return;
        }
        staffService.setUserInfo(userInformationTableView.getSelectionModel().getSelectedItem());
        permissions(true);

    }

    @FXML
    private void openCasePane(ActionEvent event) throws IOException {
        FXMLDiaryController.fromMenu = false;
        FXMLCaseController.boo = true;
        CaseService caseService = new CaseService();
        caseService.createCase("", "", "", "", "");
        caseService.setSelectedCaseID(caseService.getOpenCaseID());
        AnchorPane pane = FXMLLoader.load(getClass().getResource("FXMLCase.fxml"));
        rootPane.getChildren().setAll(pane);
        
    }

    @FXML
    private void openDiaryPane(ActionEvent event) throws IOException {
        FXMLDiaryController.fromMenu = true;
        AnchorPane pane = FXMLLoader.load(getClass().getResource("FXMLDiary.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    @FXML
    private void openMedicinePane(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("FXMLMedicine.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    private void permissions(boolean b) {
        Button[] buttons = {caseBtn, prevCaseButton, diaryBtn, medicineBtn, editUserBtn, createCitizenBtn, deleteUserBtn};
        if (!b) {
            for (Button button : buttons) {
                button.setDisable(true);
            }
            switch (staffService.getStaffType()) {
                case "Administrator":
                    caseBtn.setVisible(true);
                    prevCaseButton.setVisible(true);
                    diaryBtn.setVisible(true);
                    medicineBtn.setVisible(true);
                    editUserBtn.setVisible(true);
                    deleteUserBtn.setVisible(true);
                    createCitizenBtn.setVisible(true);
                    createCitizenBtn.setDisable(false);
                    break;
                case "Medicinansvarlig":
                    caseBtn.setVisible(false);
                    prevCaseButton.setVisible(false);
                    diaryBtn.setVisible(true);
                    medicineBtn.setVisible(true);
                    editUserBtn.setVisible(false);
                    deleteUserBtn.setVisible(false);
                    createCitizenBtn.setVisible(false);
                    break;
                case "Sagsarbejder":
                    caseBtn.setVisible(true);
                    prevCaseButton.setVisible(true);
                    diaryBtn.setVisible(true);
                    medicineBtn.setVisible(false);
                    editUserBtn.setVisible(true);
                    deleteUserBtn.setVisible(true);
                    createCitizenBtn.setVisible(true);
                    createCitizenBtn.setDisable(false);
                    break;
                default:
                    caseBtn.setVisible(false);
                    prevCaseButton.setVisible(false);
                    diaryBtn.setVisible(false);
                    medicineBtn.setVisible(false);
                    editUserBtn.setVisible(false);
                    createCitizenBtn.setVisible(false);
                    deleteUserBtn.setVisible(false);
            }
        } else {
            for (Button button : buttons) {
                if (button.isVisible()) {
                    button.setDisable(false);
                }
            }
        }
    }

    ;

    @FXML
    private void onMouseEntered(MouseEvent event) {
        if (okPressed) {
            initiateTableView();
            okPressed = false;
        }
    }

    @FXML
    private void deleteUserBtnHandler(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Bekræft");
        alert.setHeaderText(null);
        alert.setContentText("Er du sikker på du vil slette denne borger og alt tilhørende?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            ui = (UserInformation) userInformationTableView.getSelectionModel().getSelectedItem();
            userProfileService.deleteUser(ui.getUserid());
            initiateTableView();
            staffService.setUserInfo(null);
            permissions(false);
        }
    }
    
    private boolean cprChecker(String cprText) {
        if (cprText.length() != 8) {
            return false;
        }

        try {
            int cpr = Integer.parseInt(cprText);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
    
    private boolean postalcodeChecker(String postalNumber) {
        if (postalNumber.length() != 4) {
            return false;
        }

        try {
            int postalcode = Integer.parseInt(postalNumber);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
    
     private boolean numberChecker(String phone) {
        if (phone.length() != 8) {
            return false;
        }

        try {
            int number = Integer.parseInt(phone);
        } catch (NumberFormatException e) {
            return false;
        }

        if (createCitizenService.numberCheck(Integer.parseInt(phone))) {
            return false;
        }
        return true;
    }

    private boolean emailChecker(String email) {
        if (email.contains("@") == false || email.contains(".") == false) {
            return false;
        }
        return true;
    }

    @FXML
    private void prevCaseButtonHandler(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("FXMLPreviousCases.fxml"));
        rootPane.getChildren().setAll(pane);
    }
}
