/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SensumBoosted2.GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Bruger
 */
public class FXMLUserProfileController implements Initializable {

    @FXML
    private Pane userSettingPane;
    @FXML
    private Button createUserBtn;
    @FXML
    private Button userAccountBtn;
    @FXML
    private Button userInformationBtn;
    @FXML
    private Button updateBtn;
    @FXML
    private TextField searchUserTextField;
    @FXML
    private Button searchUserBtn;
    @FXML
    private Pane editUserPane;
    @FXML
    private Label insertDbLabel;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void createUserBtnEventHandler(ActionEvent event) {
    }

    @FXML
    private void userAccountBtnHandler(ActionEvent event) {
    }

    @FXML
    private void userInformationBtnHandler(ActionEvent event) {
    }

    @FXML
    private void updateBtnEventHandler(ActionEvent event) {
    }

    @FXML
    private void searchUserBtnHandler(ActionEvent event) {
    }

    @FXML
    private void editUserBtnHandler(ActionEvent event) {
    }
    
}
