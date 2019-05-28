/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SensumBoosted2.GUI;

import SensumBoosted2.Domain.CreateEmployeeService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Andreas Ibsen Cor
 */
public class FXMLCreateEmployeeController implements Initializable {

    @FXML
    private Label firstnameLabel;
    @FXML
    private Label lastnameLabel;
    @FXML
    private Label emailLabel;
    @FXML
    private TextField firstnameTextField;
    @FXML
    private TextField middlenameTextField;
    @FXML
    private TextField lastnameTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private Label cprLabel;
    @FXML
    private Label departmentLabel;
    @FXML
    private ChoiceBox<String> departmentChoiceBox;
    @FXML
    private ChoiceBox<String> staffTypeChoiceBox;
    @FXML
    private Label passwordLabel;
    @FXML
    private PasswordField passwordTextField;
    @FXML
    private PasswordField repeatPWTextField;
    @FXML
    private Label repeatPasswordLabel;
    @FXML
    private Label usernameLabel;
    @FXML
    private TextField usernameTextField;
    @FXML
    private Button okBtn;
    @FXML
    private Button cancelBtn;
    @FXML
    private Label checkLabel;

    private CreateEmployeeService createEmployeeService;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        createEmployeeService = new CreateEmployeeService();

        String[] departments = {"Misbrug", "Handicap"};
        departmentChoiceBox.getItems().addAll(departments);

        String[] staffTypes = {"Administrator", "Medicinansvarlig", "Sagsarbejder", "Alment"};
        staffTypeChoiceBox.getItems().addAll(staffTypes);
    }

    @FXML
    private void okBtnHandler(ActionEvent event) {
        TextField[] textFields = {firstnameTextField, middlenameTextField, lastnameTextField, emailTextField, usernameTextField, passwordTextField, repeatPWTextField};
        for (TextField text : textFields) {
            if (text.getText().isEmpty() && text != middlenameTextField) {
                checkLabel.setText("Udfyld venligst felter markerede med *");
                checkLabel.setDisable(false);
                checkLabel.setVisible(true);
                return;
            }
        }

        if (!emailChecker()) {
            checkLabel.setText("Ugyldig email");
            checkLabel.setDisable(false);
            checkLabel.setVisible(true);
            return;
        }

        if (!passwordTextField.getText().equals(repeatPWTextField.getText())) {
            checkLabel.setText("Passwords matcher ikke overens");
            checkLabel.setDisable(false);
            checkLabel.setVisible(true);
            return;
        }

        createEmployeeService.createEA(firstnameTextField.getText(), middlenameTextField.getText(), lastnameTextField.getText(), emailTextField.getText(), staffTypeChoiceBox.getValue(), departmentChoiceBox.getValue(), usernameTextField.getText(), passwordTextField.getText());

        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void cancelBtnHandler(ActionEvent event) {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }

    private boolean emailChecker() {
        if (emailTextField.getText().contains("@") == false || emailTextField.getText().contains(".") == false) {
            return false;
        }
        return true;
    }

}
