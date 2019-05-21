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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Andreas Frederiksen
 */
public class FXMLPreviousCasesController implements Initializable {

    private CaseService caseService;
    @FXML
    private TableView<?> tableView;
    @FXML
    private TableColumn<?, ?> dateColumn;
    @FXML
    private TableColumn<?, ?> lastColumn;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private Button diaryButton;
    @FXML
    private TableColumn<?, ?> inqColumn;
    @FXML
    private TableColumn<?, ?> assColumn;
    @FXML
    private TextArea inqText;
    @FXML
    private TextArea assText;
    @FXML
    private Button lookAtCaseButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        caseService = new CaseService();
        caseService.preCase(null);
        inqColumn.setCellValueFactory(new PropertyValueFactory<>("inquiryText"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("addedDate"));
        lastColumn.setCellValueFactory(new PropertyValueFactory<>("editDate"));
        assColumn.setCellValueFactory(new PropertyValueFactory<>("assessment"));

        tableView.setItems(caseService.sendPreviousCases());

    }

    @FXML
    private void handleBackButton(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("FXMLCaseMenu.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    @FXML
    private void onMouseClicked(MouseEvent event) {
        if (tableView.getSelectionModel().getSelectedItem() == null) {
            return;
        }
        diaryButton.setDisable(false);
        lookAtCaseButton.setDisable(false);
        
        CaseService.setSelectedCaseID(tableView.getSelectionModel().getSelectedItem());

        inqText.setText(caseService.sendPreviousInquiryText(tableView.getSelectionModel().getSelectedItem()));
        assText.setText(caseService.sendPreviousAssessmentText(tableView.getSelectionModel().getSelectedItem()));

    }

    @FXML
    private void handleDiaryButton(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("FXMLDiary.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    @FXML
    private void handleLookAtCaseButton(ActionEvent event) throws IOException {
        FXMLCaseController.boo = false;
        caseService.preCase(tableView.getSelectionModel().getSelectedItem());
        AnchorPane pane = FXMLLoader.load(getClass().getResource("FXMLCase.fxml"));
        rootPane.getChildren().setAll(pane);

    }

}
