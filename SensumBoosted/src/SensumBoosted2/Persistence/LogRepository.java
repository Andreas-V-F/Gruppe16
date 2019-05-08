/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SensumBoosted2.Persistence;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import sensumboosted.GUI.FXMLDocumentController;
import sensumboosted.Persistence.Log;

/**
 *
 * @author Mikkel Hoeyberg
 */
public class LogRepository {

    private Logger logger;
    private FileHandler fH;

    public LogRepository(String fileName) throws SecurityException, IOException {

        File f = new File(fileName);
        if (!f.exists()) {
            f.createNewFile();
        }
        fH = new FileHandler(fileName, true);
        logger = Logger.getLogger("firstLogger");
        logger.addHandler(fH);
        fH.setFormatter(new SimpleFormatter());
    }

    public void logLoginAttempt(String username, String attempt) {
        try {
            Log myLog = new Log("LoginLog.txt");
            myLog.getLogger().setLevel(Level.ALL);
            myLog.getLogger().info("Bruger: \"" + username + "\" " + attempt);
        } catch (SecurityException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Logger getLogger() {
        return logger;
    }

}
