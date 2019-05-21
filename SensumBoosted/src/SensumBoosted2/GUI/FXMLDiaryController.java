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
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    private TableView<UserAccount> citizenTableView;
    @FXML
    private TableColumn<DiaryEntry, String> text;
    @FXML
    private Button DeleteDiaryBTN;
    @FXML
    private Button editBTN;

    private boolean editMode = false;

    DiaryService ds = new DiaryService();

    StaffService StaffService = new StaffService();
    @FXML
    private TableView<DiaryEntry> DiaryEntryTableView;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("diary id: " + ds.getDiaryId(ds.getCaseId(StaffService.getUserID())));
//        System.out.println(StaffService.getUserID());
//        System.out.println(ds.getCaseId(StaffService.getUserID()));
        text.setCellValueFactory(new PropertyValueFactory<>("text"));
        DiaryEntryTableView(ds.getDiaryId(ds.getCaseId(StaffService.getUserID())));

    }

    private void DiaryEntryTableView(int logbookID) {

        System.out.println(ds.createDiaryEntryTableView(logbookID));
        
        text.setCellValueFactory(new PropertyValueFactory<>("text"));

        DiaryEntryTableView.setItems(ds.createDiaryEntryTableView(logbookID));
        DiaryEntryTableView.setEditable(true);

    }

    @FXML
    private void saveDiaryButtonHandler(ActionEvent event) {
        DiaryEntry le = DiaryEntryTableView.getSelectionModel().getSelectedItem();
        if (editMode == false) {
            ds.saveDiary(ds.getDiaryId(le.getDiaryId()), diaryTextField.getText());
        } else if (editMode == true) {
            ds.editDiary(ds.getDiaryId(le.getDiaryId()), diaryTextField.getText());
            editMode = false;
        }
        
        int id = ds.getDiaryIdByEntryId(le.getDiaryId());
        DiaryEntryTableView(id);
        diaryTextField.clear();
    }

    @FXML
    private void backToCasePaneHandler(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("FXMLUserProfile.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    private void dbClickRowHandler(MouseEvent event) {
        if (event.getClickCount() > 1) {
            System.out.println("dbClickRowHandler");
            diaryTextField.clear();
            DiaryEntry le = DiaryEntryTableView.getSelectionModel().getSelectedItem();
            int id = ds.getDiaryIdByEntryId(le.getDiaryId());
            DiaryEntryTableView(id);
        }
    }

    @FXML
    private void DeleteDiaryBTN(MouseEvent event) {
        DiaryEntry le = DiaryEntryTableView.getSelectionModel().getSelectedItem();
        ds.deleteDiaryEntry(le.getDiaryId());
        System.out.println("DeleteDiaryBTN a");
        int id = ds.getDiaryIdByEntryId(le.getDiaryId());
        DiaryEntryTableView(id);
    }

    @FXML
    private void editDiaryBTNHandler(ActionEvent event) {
        editMode = true;
        DiaryEntry le = DiaryEntryTableView.getSelectionModel().getSelectedItem();
        diaryTextField.setText(le.getText());
    }

}
