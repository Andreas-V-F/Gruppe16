/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SensumBoosted2.GUI;

import SensumBoosted2.Domain.CaseService;
import SensumBoosted2.Domain.DiaryEntry;
import SensumBoosted2.Domain.DiaryService;
import SensumBoosted2.Domain.StaffService;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Bruger
 */
public class FXMLDiaryController implements Initializable {

    @FXML
    private AnchorPane rootPane;
    @FXML
    private Pane diaryPane;
    @FXML
    private TextArea diaryTextField;
    @FXML
    private Button saveDiaryButton;
    @FXML
    private TableColumn<DiaryEntry, String> text;
    @FXML
    private Button deleteDiaryBTN;

    public static boolean fromMenu;

    DiaryService ds = new DiaryService();
    CaseService caseService = new CaseService();

    StaffService staffService = new StaffService();
    @FXML
    private TableView<DiaryEntry> DiaryEntryTableView;
    @FXML
    private Button newBTN;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        text.setCellValueFactory(new PropertyValueFactory<>("text"));
        refreshTable();
        if (!fromMenu && !caseService.isOpenCase()) {
            newBTN.setDisable(true);
            newBTN.setVisible(false);
            saveDiaryButton.setDisable(true);
            saveDiaryButton.setVisible(false);
            deleteDiaryBTN.setDisable(true);
            deleteDiaryBTN.setVisible(false);
        }

    }

    private void DiaryEntryTableView(long logbookID) {

        text.setCellValueFactory(new PropertyValueFactory<>("text"));

        DiaryEntryTableView.setItems(ds.createDiaryEntryTableView(logbookID));

    }

    @FXML
    private void saveDiaryButtonHandler(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Bekræft");
        alert.setHeaderText(null);
        alert.setContentText("Er du sikker på du vil gemme?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            ds.editDiary(DiaryEntryTableView.getSelectionModel().getSelectedItem().getDiaryEntryId(), diaryTextField.getText());
            refreshTable();
        }
        deleteDiaryBTN.setDisable(false);
        saveDiaryButton.setDisable(false);
    }

    @FXML
    private void backToCasePaneHandler(ActionEvent event) throws IOException {

        AnchorPane pane = FXMLLoader.load(getClass().getResource("FXMLUserProfile.fxml"));
        rootPane.getChildren().setAll(pane);

    }

    @FXML
    private void DeleteDiaryBTN(MouseEvent event) {
        ds.deleteDiaryEntry(DiaryEntryTableView.getSelectionModel().getSelectedItem().getDiaryEntryId());
        deleteDiaryBTN.setDisable(false);
        saveDiaryButton.setDisable(false);
        refreshTable();
    }

    @FXML
    private void onMouseClicked(MouseEvent event) {
        if (DiaryEntryTableView.getSelectionModel().getSelectedItem() == null) {
            return;
        }
        if (fromMenu || caseService.isOpenCase()) {
            deleteDiaryBTN.setDisable(false);
            saveDiaryButton.setDisable(false);
        }
        if (!DiaryEntryTableView.getSelectionModel().getSelectedItem().getText().contains("Medicinudlevering for:")) {
            diaryTextField.setEditable(true);
            deleteDiaryBTN.setDisable(false);
            saveDiaryButton.setDisable(false);
        } else {
            diaryTextField.setEditable(false);
            deleteDiaryBTN.setDisable(true);
            saveDiaryButton.setDisable(true);
        }
        if (ds.getCreatorPerm(DiaryEntryTableView.getSelectionModel().getSelectedItem().getDiaryEntryId()).equals(staffService.getStaffType()) || staffService.getStaffType().equals("Administrator")) {
            diaryTextField.setEditable(true);
            deleteDiaryBTN.setDisable(false);
            saveDiaryButton.setDisable(false);
        } else {
            diaryTextField.setEditable(false);
            deleteDiaryBTN.setDisable(true);
            saveDiaryButton.setDisable(true);
        }
        diaryTextField.setText(DiaryEntryTableView.getSelectionModel().getSelectedItem().getText());
    }

    @FXML
    private void newDiaryBTNHandler(ActionEvent event) {
        if (fromMenu) {
            ds.createDiaryEntry(ds.getDiaryId(ds.getOriginalCaseID(staffService.getUserID())), "Ny note");
        } else {
            ds.createDiaryEntry(ds.getDiaryId(caseService.getSelectedCaseID()), "Ny note");
        }
        deleteDiaryBTN.setDisable(false);
        saveDiaryButton.setDisable(false);
        refreshTable();

    }

    private void refreshTable() {
        if (fromMenu) {
            DiaryEntryTableView(ds.getDiaryId(ds.getOriginalCaseID(staffService.getUserID())));
        } else {
            DiaryEntryTableView(ds.getDiaryId(caseService.getSelectedCaseID()));
        }
    }

}
