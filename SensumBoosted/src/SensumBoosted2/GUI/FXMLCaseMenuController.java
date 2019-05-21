/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SensumBoosted2.GUI;

import SensumBoosted2.Domain.CaseService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Andreas Frederiksen
 */
public class FXMLCaseMenuController implements Initializable {

    @FXML
    private AnchorPane rootPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleNewCaseButton(ActionEvent event) throws IOException {
        FXMLCaseController.boo = true;
        CaseService caseService = new CaseService();
        caseService.createCase("", "", "", "", "");
        caseService.setSelectedCaseID(caseService.getOpenCaseID());
        AnchorPane pane = FXMLLoader.load(getClass().getResource("FXMLCase.fxml"));
        rootPane.getChildren().setAll(pane);
        
    }

    @FXML
    private void handlePrevCasesButton(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("FXMLPreviousCases.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    @FXML
    private void handleBackButton(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("FXMLUserProfile.fxml"));
        rootPane.getChildren().setAll(pane);
    }
    
}
