/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SensumBoosted2.GUI;

import SensumBoosted2.Domain.StaffService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.Effect;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author Mikkel Høyberg
 */
public class FXMLMainMenuController implements Initializable {
    private StaffService staffService;

    @FXML
    private Button logoutBTN;
    @FXML
    private AnchorPane mainMenuPane;
    @FXML
    private Pane sideMenuPane;
    @FXML
    private Button citizenBTN;
    @FXML
    private Pane switchingPane;
    @FXML
    private Button adminBTN;
    @FXML
    private Label loggedInAs;
    @FXML
    private Label nameOnUser;
    @FXML
    private Label department;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Pane pane = FXMLLoader.load(getClass().getResource("FXMLUserProfile.fxml"));
            switchingPane.getChildren().setAll(pane);
            staff();
        } catch (IOException ex) {
            Logger.getLogger(FXMLMainMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
        citizenBTN.setEffect(getEffect());
    }

    public FXMLMainMenuController() {
    }
    
    private void setLoginWindowSize(Event event) {
        ((Node) (event.getSource())).getScene().getWindow().setWidth(310);
        ((Node) (event.getSource())).getScene().getWindow().setHeight(265);
    }

    private void loadAnotherFXML(String FXMLDocument) {
        try {
            Pane pane = FXMLLoader.load(getClass().getResource(FXMLDocument));
            switchingPane.getChildren().setAll(pane);
        } catch (IOException ex) {
            System.out.println("Fejl med at indlæse " + FXMLDocument);
            Logger.getLogger(FXMLLoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadLoginFXML(String FXMLDocument, Event event) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource(FXMLDocument));
            setLoginWindowSize(event);
            mainMenuPane.getChildren().setAll(pane);
        } catch (IOException ex) {
            System.out.println("Fejl med at indlæse " + FXMLDocument);
            Logger.getLogger(FXMLLoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void citizenBTNHandler(ActionEvent event) {
        nullButtonEffect();
        citizenBTN.setEffect(getEffect());
        staffService.clearUserInfo();
        loadAnotherFXML("FXMLUserProfile.fxml");
    }

    @FXML
    private void adminBTNHandler(ActionEvent event) {
        nullButtonEffect();
        adminBTN.setEffect(getEffect());
        staffService.clearUserInfo();
        loadAnotherFXML("FXMLAdmin.fxml");
    }

    @FXML
    private void logoutBTNHandler(ActionEvent event) {
        staffService = new StaffService();
        staffService.clearStaff();
        staffService.clearUserInfo();
        loadLoginFXML("FXMLLogin.fxml", event);
    }

    private Effect getEffect() {
        Color color = Color.color(0.2, 0.5, 1);
        Effect ef = new InnerShadow(BlurType.THREE_PASS_BOX, color, 24.5, 0, 0, 0);
        return ef;
    }

    private void nullButtonEffect() {
        citizenBTN.setEffect(null);
        adminBTN.setEffect(null);
    }

    private void staff() {
        staffService = new StaffService();
        loggedInAs.setText(staffService.getStaffType());
        nameOnUser.setText(staffService.getStaffName());
        department.setText(staffService.getStaffDepartment());

        switch (staffService.getStaffType()) {
            case "Administrator":
                citizenBTN.setDisable(false);
                citizenBTN.setVisible(true);
                adminBTN.setDisable(false);
                adminBTN.setVisible(true);
                break;
            case "Medicinansvarlig":
                citizenBTN.setDisable(false);
                citizenBTN.setVisible(true);
                adminBTN.setDisable(true);
                adminBTN.setVisible(false);
                break;
            case "Sagsarbejder":
                citizenBTN.setDisable(false);
                citizenBTN.setVisible(true);
                adminBTN.setDisable(true);
                adminBTN.setVisible(false);
            default:
                citizenBTN.setDisable(true);
                citizenBTN.setVisible(false);
                adminBTN.setDisable(true);
                adminBTN.setVisible(false);
        }
    }

}
