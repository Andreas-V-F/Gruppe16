/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SensumBoosted2.GUI;

import SensumBoosted2.Domain.CaseService;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Bruger
 */
public class FXMLCaseController implements Initializable {

    private CaseService caseService;
    public static boolean boo;
    private boolean saved;
    @FXML
    private TextArea infoBox;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private ToggleGroup kinToggleGroup;
    @FXML
    private TextField kinTextField;
    @FXML
    private ToggleGroup yerOrNoToggleGroup;
    @FXML
    private TextArea answer1;
    @FXML
    private TextArea assessmentText;
    @FXML
    private TextArea taskPurpose;
    @FXML
    private TextArea taskGoal;
    @FXML
    private ImageView imgView;
    @FXML
    private RadioButton radioButtonCitizen;
    @FXML
    private RadioButton radioButtonInquirer;
    @FXML
    private RadioButton radioButtonDoctor;
    @FXML
    private RadioButton radioButtonHospital;
    @FXML
    private RadioButton radioButtonPublic;
    @FXML
    private RadioButton radioButtonTreatment;
    @FXML
    private RadioButton radioButtonMunicipality;
    @FXML
    private RadioButton radioButtonOther;
    @FXML
    private RadioButton radioButtonYes;
    @FXML
    private RadioButton radioButtonNo;
    @FXML
    private Button saveButton;
    @FXML
    private Button closeButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        caseService = new CaseService();
        clear();
        update(boo);
        File file = new File("src/Pictures/123.png");
        Image image = new Image(file.toURI().toString());
        imgView.setImage(image);
    }

    @FXML
    private void handleCloseAction(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Bekræft");
        alert.setHeaderText(null);
        alert.setContentText("Er du sikker på du vil lukke sagen?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            if (!saved) {
                handleSaveAction(event);
            }

            caseService.closeCase();
            clear();
            caseService.createCase("", "", "", "", "");
            caseService.setSelectedCaseID(caseService.getOpenCaseID());

        }

    }

    private void handlePreCases(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("FXMLPreviousCases.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    @FXML
    private void handleSaveAction(ActionEvent event) {
        saved = true;
        RadioButton selectedKinRadioButton = (RadioButton) kinToggleGroup.getSelectedToggle();
        String toogleGroupKinValue = selectedKinRadioButton.getText();
        RadioButton selectedYesOrNoRadioButton = (RadioButton) yerOrNoToggleGroup.getSelectedToggle();
        String toogleGroupYesOrNoValue = selectedYesOrNoRadioButton.getText();
        String inquirer = toogleGroupKinValue + "/" + kinTextField.getText() + "/" + toogleGroupYesOrNoValue;
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Bekræft");
        alert.setHeaderText(null);
        alert.setContentText("Er du sikker på du vil gemme?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            caseService.saveCase(answer1.getText(), inquirer, assessmentText.getText(), taskPurpose.getText(), taskGoal.getText());

        }
    }

    @FXML
    private void handleBackAction(ActionEvent event) throws IOException {
        if (boo && !saved) {
            handleSaveAction(event);
        }

        AnchorPane pane = FXMLLoader.load(getClass().getResource("FXMLCaseMenu.fxml"));
        rootPane.getChildren().setAll(pane);

    }

    public void update(boolean b) {

        infoBox.setText(caseService.printToInfo());

        if (b == true) {
            String[] cases = caseService.printToCase();
            casePrinter(cases);
            saveButton.setDisable(false);
            closeButton.setDisable(false);
        } else {
            String[] cases = caseService.casePrintStrings();
            casePrinter(cases);
            saveButton.setDisable(true);
            closeButton.setDisable(true);
            answer1.setEditable(false);
            kinTextField.setEditable(false);
            assessmentText.setEditable(false);
            taskPurpose.setEditable(false);
            taskGoal.setEditable(false);
            radioButtonCitizen.setDisable(true);
            radioButtonDoctor.setDisable(true);
            radioButtonHospital.setDisable(true);
            radioButtonInquirer.setDisable(true);
            radioButtonMunicipality.setDisable(true);
            radioButtonNo.setDisable(true);
            radioButtonOther.setDisable(true);
            radioButtonPublic.setDisable(true);
            radioButtonTreatment.setDisable(true);
            radioButtonYes.setDisable(true);
        }
    }

    public void casePrinter(String[] cases) {
        if (cases != null) {

            ArrayList<RadioButton> radioInquirer = new ArrayList<>();
            radioInquirer.add(radioButtonCitizen);
            radioInquirer.add(radioButtonDoctor);
            radioInquirer.add(radioButtonHospital);
            radioInquirer.add(radioButtonInquirer);
            radioInquirer.add(radioButtonMunicipality);
            radioInquirer.add(radioButtonOther);
            radioInquirer.add(radioButtonPublic);
            radioInquirer.add(radioButtonTreatment);

            answer1.setText(cases[0]);
            for (RadioButton r : radioInquirer) {
                if (r.getText().equals(cases[1])) {
                    r.setSelected(true);
                    break;
                }
            }
            kinTextField.setText(cases[2]);
            if (radioButtonYes.getText().equals(cases[3])) {
                radioButtonYes.setSelected(true);
            } else if (radioButtonNo.getText().equals(cases[3])) {
                radioButtonNo.setSelected(true);
            }
            assessmentText.setText(cases[4]);
            taskPurpose.setText(cases[5]);
            taskGoal.setText(cases[6]);

        }
    }

    public void clear() {
        answer1.setText("");
        kinTextField.setText("");
        assessmentText.setText("");
        taskPurpose.setText("");
        taskGoal.setText("");
        radioButtonCitizen.setSelected(true);
        radioButtonYes.setSelected(true);
    }

    @FXML
    private void handleDiaryButton(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("FXMLDiary.fxml"));
        rootPane.getChildren().setAll(pane);
    }
}
