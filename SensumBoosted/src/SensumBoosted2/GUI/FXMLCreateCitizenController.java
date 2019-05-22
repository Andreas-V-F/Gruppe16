/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SensumBoosted2.GUI;

import SensumBoosted2.Domain.CreateCitizenService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Bruger
 */
public class FXMLCreateCitizenController implements Initializable {

    private CreateCitizenService createCitizenService = new CreateCitizenService();

    @FXML
    private AnchorPane createCitizenPane;
    @FXML
    private TextField firstnameTextField;
    @FXML
    private TextField middlenameTextField;
    @FXML
    private TextField lastnameTextField;
    @FXML
    private TextField addressTextField;
    @FXML
    private TextField phonenumberTextField;
    @FXML
    private TextField cityTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private Button okBtn;
    @FXML
    private Button cancelBtn;
    @FXML
    private TextField cprTextField;
    @FXML
    private TextField postalcodeTextField;
    @FXML
    private ChoiceBox<String> departmentChoiceBox;
    @FXML
    private TextField passwordTextField;
    @FXML
    private TextField repeatPWTextField;
    @FXML
    private Label checkLabel;
    @FXML
    private Label firstnameLabel;
    @FXML
    private Label lastnameLabel;
    @FXML
    private Label addressLabel;
    @FXML
    private Label postalcodeLabel;
    @FXML
    private Label cityLabel;
    @FXML
    private Label emailLabel;
    @FXML
    private Label phonenumberLabel;
    @FXML
    private Label cprLabel;
    @FXML
    private Label departmentLabel;
    @FXML
    private Label passwordLabel;
    @FXML
    private Label repeatPasswordLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        String[] departments = {"Misbrug", "Handicap"};
        departmentChoiceBox.getItems().addAll(departments);

    }

    @FXML
    private void okBtnHandler(ActionEvent event) {
        TextField[] textFields = {firstnameTextField, middlenameTextField, lastnameTextField, addressTextField,
            phonenumberTextField, cityTextField, emailTextField, cprTextField, postalcodeTextField,
            passwordTextField, repeatPWTextField};

        for (TextField text : textFields) {
            System.out.println(text.getText());
            if (text.getText().isEmpty() && text != middlenameTextField) {
                checkLabel.setText("Udfyld venligst felter markerede med *");
                checkLabel.setDisable(false);
                checkLabel.setVisible(true);
                return;
            }
        }

        if (!postalcodeChecker()) {
            checkLabel.setText("Ugyldigt postnummer");
            checkLabel.setDisable(false);
            checkLabel.setVisible(true);
            return;
        }

        if (!emailChecker()) {
            checkLabel.setText("Ugyldig email");
            checkLabel.setDisable(false);
            checkLabel.setVisible(true);
            return;
        }

        if (!numberChecker()) {
            checkLabel.setText("Ugyldigt telefonnummer");
            checkLabel.setDisable(false);
            checkLabel.setVisible(true);
            return;
        }

        if (!cprChecker()) {
            checkLabel.setText("Ugyldigt CPR nummer");
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

        FXMLUserProfileController.okPressed = true;

        createCitizenService.createCA(firstnameTextField.getText(), middlenameTextField.getText(), lastnameTextField.getText(), Integer.parseInt(cprTextField.getText()), addressTextField.getText(),
                Integer.parseInt(postalcodeTextField.getText()), cityTextField.getText(), emailTextField.getText(), Integer.parseInt(phonenumberTextField.getText()), departmentChoiceBox.getValue(), passwordTextField.getText(), "Borger");

        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void cancelBtnHandler(ActionEvent event) {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }

    private boolean cprChecker() {
        if (cprTextField.getText().length() != 8) {
            return false;
        }

        try {
            int cpr = Integer.parseInt(cprTextField.getText());
        } catch (NumberFormatException e) {
            return false;
        }

        if (createCitizenService.cprCheck(Integer.parseInt(cprTextField.getText()))) {
            return false;
        }
        return true;
    }

    private boolean numberChecker() {
        if (phonenumberTextField.getText().length() != 8) {
            return false;
        }

        try {
            int number = Integer.parseInt(phonenumberTextField.getText());
        } catch (NumberFormatException e) {
            return false;
        }

        if (createCitizenService.numberCheck(Integer.parseInt(phonenumberTextField.getText()))) {
            return false;
        }
        return true;
    }

    private boolean postalcodeChecker() {
        if (postalcodeTextField.getText().length() != 4) {
            return false;
        }

        try {
            int postalcode = Integer.parseInt(postalcodeTextField.getText());
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    private boolean emailChecker() {
        if (emailTextField.getText().contains("@") == false || emailTextField.getText().contains(".") == false) {
            return false;
        }
        return true;
    }
}
