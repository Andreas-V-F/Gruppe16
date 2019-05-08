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
    private TableColumn<?, ?> textColumn;
    @FXML
    private TableColumn<?, ?> dateColumn;
    @FXML
    private TableColumn<?, ?> lastColumn;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private TextArea textArea;
    @FXML
    private Button dairyButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        caseService = new CaseService();

        textColumn.setCellValueFactory(new PropertyValueFactory<>("text"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        lastColumn.setCellValueFactory(new PropertyValueFactory<>("lastEditDate"));

        tableView.setItems(caseService.sendPreviousCases());

    }

    @FXML
    private void handleBackButton(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("FXMLCase.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    @FXML
    private void editStuiff(MouseEvent event) {
        dairyButton.setDisable(false);
        textArea.setText(caseService.sendPreviousCaseText(tableView.getSelectionModel().getSelectedItem()));
    }

    @FXML
    private void handleDaoryButton(ActionEvent event) {
    }

  

}
