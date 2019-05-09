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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

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
}
