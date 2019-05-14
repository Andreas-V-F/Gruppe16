/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SensumBoosted2.GUI;

import SensumBoosted2.Domain.DiaryEntry;
import SensumBoosted2.Domain.DiaryService;
import SensumBoosted2.Domain.StaffService;
import SensumBoosted2.Domain.UserAccount;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Bruger
 */
public class FXMLDiaryController implements Initializable {

    @FXML
    private Pane diaryPane;
    @FXML
    private TextArea diaryTextField;
    @FXML
    private Button saveDiaryButton;
    @FXML
    private TableView<UserAccount> citizenTableView;
    @FXML
    private TableColumn<UserAccount, Integer> citizenId;
    @FXML
    private TableColumn<UserAccount, String> citizenName;
    @FXML
    private TableView<DiaryEntry> diaryEntryTableView;
    @FXML
    private TableColumn<DiaryEntry, String> text;
    @FXML
    private Button DeleteDiaryBTN;
    @FXML
    private Button editBTN;
    @FXML
    private Label nameDiaryLBL;
    @FXML
    private Label cprDiaryLBL;
    @FXML
    private Label emailDiaryLBL;
    @FXML
    private Label adresseDiaryLBL;

    private boolean editMode = false;

    DiaryService ds = new DiaryService();
    
    StaffService StaffService = new StaffService();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        diaryEntryTableView(ds.getDiaryId(ds.getCaseId(StaffService.getUserID())));
    }

    private void diaryEntryTableView(long logbookID) {

        text.setCellValueFactory(new PropertyValueFactory<>("text"));

        diaryEntryTableView.setItems(ds.createDiaryEntryTableView(logbookID));
        diaryEntryTableView.setEditable(true);

    }

    @FXML
    private void saveDiaryButtonHandler(ActionEvent event) {
        UserAccount x = citizenTableView.getSelectionModel().getSelectedItem();
        if (editMode == false) {
            ds.saveDiary(x.getUserid(), diaryTextField.getText());
        } else if (editMode == true) {
            DiaryEntry le = diaryEntryTableView.getSelectionModel().getSelectedItem();
            ds.editDiary(le.getDiaryId(), diaryTextField.getText());
            editMode = false;
        }
        long id = ds.getDiaryId(ds.getCaseId(x.getUserid()));
        diaryEntryTableView(id);
        diaryTextField.clear();
    }

    @FXML
    private void backToCasePaneHandler(ActionEvent event) {

    }

    @FXML
    private void dbClickRowHandler(MouseEvent event) {
        if (event.getClickCount() > 1) {
            System.out.println("dbClickRowHandler");
            diaryTextField.clear();
            UserAccount x = citizenTableView.getSelectionModel().getSelectedItem();
            long id = ds.getDiaryId(ds.getCaseId(x.getUserid()));
            diaryEntryTableView(id);
        }
    }

    @FXML
    private void DeleteDiaryBTN(MouseEvent event) {
        UserAccount x = citizenTableView.getSelectionModel().getSelectedItem();
        System.out.println("DeleteDiaryBTN b");
        DiaryEntry le = diaryEntryTableView.getSelectionModel().getSelectedItem();
        ds.deleteDiaryEntry(le.getDiaryId());
        System.out.println("DeleteDiaryBTN a");
        long id = ds.getDiaryId(ds.getCaseId(x.getUserid()));
    }

    @FXML
    private void editDiaryBTNHandler(ActionEvent event) {
        DiaryEntry le = diaryEntryTableView.getSelectionModel().getSelectedItem();
        diaryTextField.setText(le.getText());
        editMode = true;
    }

}
