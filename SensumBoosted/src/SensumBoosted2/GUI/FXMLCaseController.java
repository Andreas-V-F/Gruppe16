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
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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

    @FXML
    private TextArea infoBox;

    @FXML
    private AnchorPane rootPane;
   
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private TextArea question1;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        caseService = new CaseService();
        infoBox.setText(caseService.printToInfo());
//        caseText.setText(caseService.printToCase());
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
            caseService.saveCase("test");
            caseService.closeCase();
//            textArea.setText("");
//            caseText.setText("");
        }

    }

    private void handlePreCases(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("FXMLPreviousCases.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    @FXML
    private void handleSaveAction(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Bekræft");
        alert.setHeaderText(null);
        alert.setContentText("Er du sikker på du vil gemme?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            caseService.saveCase("test");
//            textArea.setText("");
//            caseText.setText(caseService.printToCase());
        }
    }

    @FXML
    private void handleBackAction(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("FXMLCaseMenu.fxml"));
        rootPane.getChildren().setAll(pane);

    }

}
