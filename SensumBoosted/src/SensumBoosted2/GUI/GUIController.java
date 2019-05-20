/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SensumBoosted2.GUI;

import java.io.File;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author Mikkel Hoeyberg
 */
public class GUIController extends Application {

    private static Stage pStage;

    @Override
    public void start(Stage stage) throws Exception {
        setPrimaryStage(stage);
        pStage = stage;

        Parent root = FXMLLoader.load(getClass().getResource("FXMLLogin.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Sensom Boosted");
        File file = new File("src/Pictures/EGpicture.jpg");
        stage.getIcons().add(new Image(file.toURI().toString()));
        stage.setScene(scene);

        stage.centerOnScreen();
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static Stage getPrimaryStage() {
        return pStage;
    }

    private void setPrimaryStage(Stage pStage) {
        this.pStage = pStage;
    }

}
