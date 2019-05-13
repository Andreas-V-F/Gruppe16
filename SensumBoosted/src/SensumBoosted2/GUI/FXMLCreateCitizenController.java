/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SensumBoosted2.GUI;

import SensumBoosted2.Domain.CreateCitizenService;
import SensumBoosted2.Domain.UserProfileService;
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
import javafx.scene.paint.Color;
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
    private TextField usernameTextField;
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
    private Label usernameLabel;
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
//        if (!firstnameTextField.getText().isEmpty() && !lastnameTextField.getText().isEmpty()
//                && !addressTextField.getText().isEmpty() && !postalcodeTextField.getText().isEmpty()
//                && !phonenumberTextField.getText().isEmpty() && !cityTextField.getText().isEmpty()
//                && !emailTextField.getText().isEmpty() && !cprTextField.getText().isEmpty()) {
//            
//            if (passwordTextField.getText().equals(repeatPWTextField.getText())) {
//                
//            createCitizenService.createCI(firstnameTextField.getText(), middlenameTextField.getText(),
//                    lastnameTextField.getText(), Integer.parseInt(cprTextField.getText()),
//                    addressTextField.getText(), Integer.parseInt(postalcodeTextField.getText()),
//                    cityTextField.getText(), emailTextField.getText(),
//                    Integer.parseInt(phonenumberTextField.getText()), departmentChoiceBox.getValue());
//            createCitizenService.createCA(usernameTextField.getText(), passwordTextField.getText(), "Borger");
//            
//            }
//        }

        // Alt for langt metode og for mange if statements xD?
        if (firstnameTextField.getText().isEmpty() || lastnameTextField.getText().isEmpty()
                || addressTextField.getText().isEmpty() || postalcodeTextField.getText().isEmpty()
                || cityTextField.getText().isEmpty() || emailTextField.getText().isEmpty()
                || phonenumberTextField.getText().isEmpty() || cprTextField.getText().isEmpty()
                || departmentChoiceBox.getValue().isEmpty() || usernameTextField.getText().isEmpty()
                || passwordTextField.getText().isEmpty() || repeatPWTextField.getText().isEmpty()) {
            checkLabel.setVisible(true);

            if (passwordTextField.getText().equals(repeatPWTextField.getText())
                    && !passwordTextField.getText().isEmpty() && !repeatPWTextField.getText().isEmpty()) {
                createCitizenService.createCI(firstnameTextField.getText(), middlenameTextField.getText(),
                        lastnameTextField.getText(), Integer.parseInt(cprTextField.getText()),
                        addressTextField.getText(), Integer.parseInt(postalcodeTextField.getText()),
                        cityTextField.getText(), emailTextField.getText(),
                        Integer.parseInt(phonenumberTextField.getText()), departmentChoiceBox.getValue());
                createCitizenService.createCA(usernameTextField.getText(), passwordTextField.getText(), "Borger");
                Stage stage = (Stage) okBtn.getScene().getWindow();
                stage.close();
            }

        }
    }

        @FXML
        private void cancelBtnHandler
        (ActionEvent event
        
            ) {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
            stage.close();
        }

    }
