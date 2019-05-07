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
import javafx.scene.control.TextArea;
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
    private TextArea textArea;
    @FXML
    private TextArea infoBox;
    @FXML
    private Button caseBackBTN;
    @FXML
    private ImageView ImgView;
    @FXML
    private AnchorPane rootPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        caseService = new CaseService();
        infoBox.setText(caseService.printToInfo());
        textArea.setText(caseService.printToCase());
        File file = new File("src/Pictures/123.png");
        Image image = new Image(file.toURI().toString());
        ImgView.setImage(image);
    }

    @FXML
    private void logbookButtonHandler(ActionEvent event) {
    }

    @FXML
    private void handleGemActon(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Bekræft");
        alert.setHeaderText("Bekræft");
        alert.setContentText("Er du sikker på du vil gemme?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            caseService.saveCase(textArea.getText());
        }

    }

    @FXML
    private void handleCloseAction(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Bekræft");
        alert.setHeaderText("Bekræft");
        alert.setContentText("Er du sikker på du vil lukke sagen?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            caseService.closeCase();
        }
        
    }

    @FXML
    private void caseBackBTN(MouseEvent event) {
    }

    @FXML
    private void handlePreCases(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("FXMLPreviousCases.fxml"));
        rootPane.getChildren().setAll(pane);
    }

}
