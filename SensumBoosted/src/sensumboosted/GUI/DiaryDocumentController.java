/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sensumboosted.GUI;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 *
 * @author krute
 */
public class DiaryDocumentController {

    private FXMLLoader loader = new FXMLLoader();

    @FXML
    private Hyperlink logoutLink;
    @FXML
    private MenuButton menuButton;
    @FXML
    private Label whoIsLoggedIn;
    @FXML
    private Button logoutBTN;

    public void setText(String user) {
        this.whoIsLoggedIn.setText(user.substring(0, 1).toUpperCase() + user.substring(1));
    }

    @FXML
    private void menuButtonHandler(ActionEvent event) {

    }

    @FXML
    private void whoIsLoggedInHandler(MouseEvent event) {

    }

    @FXML
    private void logoutLinkHandler(ActionEvent event) {
        // This must be reworked with the method used "LoadLoginWindow".
        ((Node) (event.getSource())).getScene().getWindow().hide();
        LoadLoginWindow();
        
    }

    @FXML
    private void logoutBTNHandler(ActionEvent event) {

    }

    // Im pretty sure this is not the right way!
    // You should be able to load the login stage the is hidden or unhide it, and close this!
    private void LoadLoginWindow() {

        loader.setLocation(getClass().getResource("FXMLDocument.fxml"));
        try {
            loader.load();
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        FXMLDocumentController loginDisplay = loader.getController();

        Parent p = loader.getRoot();
        Stage stage = new Stage();
        stage.setTitle("Login Window");
        stage.setScene(new Scene(p));
        stage.showAndWait();
    }

}
