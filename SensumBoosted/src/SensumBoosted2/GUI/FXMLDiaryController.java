/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SensumBoosted2.GUI;

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
    private TableView<?> citizenTableView;
    @FXML
    private TableColumn<?, ?> citizenId;
    @FXML
    private TableColumn<?, ?> citizenName;
    @FXML
    private TableView<?> DiaryEntryTableView;
    @FXML
    private TableColumn<?, ?> text;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void saveDiaryButtonHandler(ActionEvent event) {
    }

    @FXML
    private void backToCasePaneHandler(ActionEvent event) {
    }

    @FXML
    private void dbClickRowHandler(MouseEvent event) {
    }

    @FXML
    private void DeleteDiaryBTN(MouseEvent event) {
    }

    @FXML
    private void editDiaryBTNHandler(ActionEvent event) {
    }

}
