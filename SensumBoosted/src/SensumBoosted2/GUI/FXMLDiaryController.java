/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SensumBoosted2.GUI;

import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import sensumboosted.Domain.DiaryEntry;
import sensumboosted.Domain.UserAccount;

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
    private TableView<DiaryEntry> DiaryEntryTableView;
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
    ObservableList<UserAccount> obListCT = FXCollections.observableArrayList();
    ObservableList<DiaryEntry> obListLE = FXCollections.observableArrayList();
//    private Connection con = dbController.connect();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

    @FXML
    private void saveDiaryButtonHandler(ActionEvent event) {
        UserAccount x = citizenTableView.getSelectionModel().getSelectedItem();
        if (editMode == false) {
            dbController.saveDiary(x.getUserid(), diaryTextField.getText());
        } else if (editMode == true) {
            DiaryEntry le = diaryEntryTableView.getSelectionModel().getSelectedItem();
            dbController.editDiary(le.getDiaryId(), diaryTextField.getText());
            editMode = false;
        }
        long id = dbController.getDiaryId(dbController.getCaseId(x.getUserid()));
        diaryEntryTableView(id);
        diaryTextField.clear();
    }

    @FXML
    private void backToCasePaneHandler(ActionEvent event) {
        diaryPane.setVisible(false);
        diaryPane.setDisable(true);

        casePane.setVisible(true);
        casePane.setDisable(false);
    }

    @FXML
    private void dbClickRowHandler(MouseEvent event) {
        if (event.getClickCount() > 1) {
            System.out.println("dbClickRowHandler");
            diaryTextField.clear();
            UserAccount x = citizenTableView.getSelectionModel().getSelectedItem();
            long id = dbController.getDiaryId(dbController.getCaseId(x.getUserid()));
            diaryEntryTableView(id);
            setDiaryLBL(x.getUserid());
        }
    }

    @FXML
    private void DeleteDiaryBTN(MouseEvent event) {
        UserAccount x = citizenTableView.getSelectionModel().getSelectedItem();
        System.out.println("DeleteDiaryBTN b");
        DiaryEntry le = diaryEntryTableView.getSelectionModel().getSelectedItem();
        dbController.deleteDiaryEntry(le.getDiaryId());
        System.out.println("DeleteDiaryBTN a");
        long id = dbController.getDiaryId(dbController.getCaseId(x.getUserid()));
        diaryEntryTableView(id);
    }

    @FXML
    private void editDiaryBTNHandler(ActionEvent event) {
        DiaryEntry le = diaryEntryTableView.getSelectionModel().getSelectedItem();
        diaryTextField.setText(le.getText());
        editMode = true;
    }

}
