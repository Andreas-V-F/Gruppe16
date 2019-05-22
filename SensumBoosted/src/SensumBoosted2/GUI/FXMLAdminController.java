/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SensumBoosted2.GUI;

import SensumBoosted2.Domain.LogService;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Mikkel Høyberg
 */
public class FXMLAdminController implements Initializable {

//    private LogService logService;
    @FXML
    private Button seeLogLoginBTN;
    @FXML
    private TextArea logTextArea;
    @FXML
    private AnchorPane rootPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        logTextArea.editableProperty().set(false);
    }

    @FXML
    private void seeLogLoginBTNHandler(ActionEvent event) {
        logTextArea.setVisible(true);
        logTextArea.clear();
        String txt = LogService.getInstance().getLogFile();
        logTextArea.setText(txt);
    }

    @FXML
    private void createEmployeeBTN(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        try {
                stage = new Stage();
                root = FXMLLoader.load(getClass().getResource("FXMLCreateEmployee.fxml"));
                stage.setScene(new Scene(root));
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
