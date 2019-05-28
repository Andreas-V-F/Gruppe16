/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SensumBoosted2.GUI;

import SensumBoosted2.Domain.DiaryService;
import SensumBoosted2.Domain.StaffService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Andreas Frederiksen
 */
public class FXMLMedicineController implements Initializable {

    @FXML
    private TextField medicineTextField;
    @FXML
    private TextField amountTextField;
    @FXML
    private TextArea userTextArea;
    @FXML
    private Label errorLabel;
    @FXML
    private AnchorPane rootPane;

    private StaffService staffService;
    
    private DiaryService diaryService;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        staffService = new StaffService();
        diaryService = new DiaryService();
        userTextArea.setText(staffService.getUserInfo().toString());
    }

    @FXML
    private void dispenseMedicineHandler(ActionEvent event) {
        if (medicineTextField.getText().equals("") || amountTextField.getText().equals("")) {
            errorLabel.setVisible(true);
            return;
        }
        
        diaryService.medicineDiaryEntry(diaryService.getDiaryId(diaryService.getOriginalCaseID(staffService.getUserID())), medicineTextField.getText(), amountTextField.getText(), staffService.getUserInfo().getCpr());
        medicineTextField.clear();
        amountTextField.clear();
    }

    @FXML
    private void backHandler(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("FXMLUserProfile.fxml"));
        rootPane.getChildren().setAll(pane);
    }

}
