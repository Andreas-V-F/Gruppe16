/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SensumBoosted2.GUI;

import SensumBoosted2.Domain.UserInformation2;
import SensumBoosted2.Persistence.ConnectRepository;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Bruger
 */
public class FXMLUserProfileController implements Initializable {
    
    private ConnectRepository cr;
    private UserInformation2 ui;

    @FXML
    private Pane userProfilePane;
    @FXML
    private Button createCitizenBtn;
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
    
    
    public String firstname;
    public String middlename;
    public String lastname;
    public int cpr;
    public String address;
    public int postalcode;
    public String city;
    public String email;
    
    //Tableview FXML
    public ObservableList<UserInformation2> obListUI = FXCollections.observableArrayList();
    @FXML
    private TableView<UserInformation2> userInformationTableView;
    @FXML
    private TableColumn<UserInformation2, String> userIDColumn;
    @FXML
    private TableColumn<UserInformation2, String> firstnameColumn;
    @FXML
    private TableColumn<UserInformation2, String> middlenameColumn;
    @FXML
    private TableColumn<UserInformation2, String> lastnameColumn;
    @FXML
    private TableColumn<UserInformation2, Integer> cprColumn;
    @FXML
    private TableColumn<UserInformation2, String> addressColumn;
    @FXML
    private TableColumn<UserInformation2, Integer> postalcodeColumn;
    @FXML
    private TableColumn<UserInformation2, String> cityColumn;
    @FXML
    private TableColumn<UserInformation2, String> emailColumn;
    @FXML
    private Button editUserBtn;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initiateTableView();
    }    

    @FXML
    private void createCitizenBtnEventHandler(ActionEvent event) {
    }


    @FXML
    private void searchUserBtnHandler(ActionEvent event) {
    }

    @FXML
    private void saveBtnHandler(ActionEvent event) {
                if (!editFirstnameField.getText().equals(cr.getFirstnameWithCPR()) || !editMiddlenameField.getText().equals(cr.getMiddlenameWithCPR())
                || !editLastnameField.getText().equals(cr.getLastnameWithCPR()) || !editCPRField.getText().equals(Integer.toString(cr.getCPRWithCPR()))
                || !editAddressField.getText().equals(Integer.toString(cr.getPostalcodeWithCPR())) || !editCityField.getText().equals(cr.getCityWithCPR())
                || !editEmailField.getText().equals(cr.getEmailWithCPR())) {
                    cr.saveUserInformation();
                }
    }
    
    //Tableview - COLUMN
    private void initiateCols() {
        userIDColumn.setCellValueFactory(new PropertyValueFactory<>("userid"));
        firstnameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        middlenameColumn.setCellValueFactory(new PropertyValueFactory<>("middleName"));
        lastnameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        cprColumn.setCellValueFactory(new PropertyValueFactory<>("cpr"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        postalcodeColumn.setCellValueFactory(new PropertyValueFactory<>("postalcode"));
        cityColumn.setCellValueFactory(new PropertyValueFactory<>("city"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
    }
    
    //Tableview
    public void initiateTableView() {
        cr.getUITableView();
        initiateCols();
        userInformationTableView.setItems(obListUI);
        userInformationTableView.setEditable(true);
    }

    @FXML
    private void editUserBtnHandler(ActionEvent event) {
        ui = userInformationTableView.getSelectionModel().getSelectedItem();
        
        editFirstnameField.setText(ui.getFirstName());
        editMiddlenameField.setText(ui.getMiddleName());
        editLastnameField.setText(ui.getLastName());
        editCPRField.setText(Integer.toString(ui.getCpr()));
        editAddressField.setText(ui.getAddress());
        editPostalCodeField.setText(Integer.toString(ui.getPostalCode()));
        editCityField.setText(ui.getCity());
        editEmailField.setText(ui.getEmail());
    }
    
    
}
