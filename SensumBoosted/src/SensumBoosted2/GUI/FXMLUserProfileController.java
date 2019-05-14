/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SensumBoosted2.GUI;

import SensumBoosted2.Domain.StaffService;
import SensumBoosted2.Domain.UserInformation2;
import SensumBoosted2.Domain.UserProfileService;
import SensumBoosted2.GUI.FXMLUserProfileController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
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
    private UserInformation2 ui;

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
    public TextField editCPRField;
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
    private TableColumn<?, ?> userIDColumn;
    @FXML
    private TableColumn<?, ?> firstnameColumn;
    @FXML
    private TableColumn<?, ?> middlenameColumn;
    @FXML
    private TableColumn<?, ?> lastnameColumn;
    @FXML
    private TableColumn<?, ?> cprColumn;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initiateTableView();
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
        ui = (UserInformation2) userInformationTableView.getSelectionModel().getSelectedItem();
        if (!editFirstnameField.getText().equals(ui.getFirstname()) || !editMiddlenameField.getText().equals(ui.getMiddlename())
                || !editLastnameField.getText().equals(ui.getLastname()) || !editCPRField.getText().equals(Integer.toString(ui.getCpr()))
                || !editAddressField.getText().equals(Integer.toString(ui.getPostalcode())) || !editCityField.getText().equals(ui.getCity())
                || !editEmailField.getText().equals(ui.getEmail())) {

            userProfileService.saveCI(editFirstnameField.getText(), editMiddlenameField.getText(), editLastnameField.getText(),
                    Integer.parseInt(editCPRField.getText()), editAddressField.getText(), Integer.parseInt(editPostalCodeField.getText()),
                    editCityField.getText(), editEmailField.getText(), ui.getUserid());
            saveStatusLabel.setText("Ændringer gemt");
        }
        initiateTableView();

    }

    //Tableview - COLUMN
    private void initiateCols() {
        userIDColumn.setCellValueFactory(new PropertyValueFactory<>("userid"));
        firstnameColumn.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        middlenameColumn.setCellValueFactory(new PropertyValueFactory<>("middlename"));
        lastnameColumn.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        cprColumn.setCellValueFactory(new PropertyValueFactory<>("cpr"));
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
            userInformationTableView.setItems(userProfileService.cprSearchCI(searchUserTextField.getText()));
        } else if (rbFirstname.isSelected()) {
            userInformationTableView.setItems(userProfileService.firstnameSearchCI(searchUserTextField.getText()));
        }
    }

    @FXML
    private void editUserBtnHandler(ActionEvent event) {
        ui = (UserInformation2) userInformationTableView.getSelectionModel().getSelectedItem();

        editFirstnameField.setText(ui.getFirstname());
        editMiddlenameField.setText(ui.getMiddlename());
        editLastnameField.setText(ui.getLastname());
        editCPRField.setText(Integer.toString(ui.getCpr()));
        editAddressField.setText(ui.getAddress());
        editPostalCodeField.setText(Integer.toString(ui.getPostalcode()));
        editCityField.setText(ui.getCity());
        editEmailField.setText(ui.getEmail());
    }

    @FXML
    private void mouseClick(MouseEvent event) {
        staffService.setUserInfo(userInformationTableView.getSelectionModel().getSelectedItem());
    }
}
