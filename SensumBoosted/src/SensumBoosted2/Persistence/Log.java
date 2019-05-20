/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SensumBoosted2.Persistence;
import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 *
 * @author krute
 */
public class Log {

    private Logger logger;
    private FileHandler fH;

    public Log(String fileName) throws SecurityException, IOException {

        File f = new File(fileName);
        if (!f.exists()) {
            f.createNewFile();
        }
        fH = new FileHandler(fileName, true);
        logger = Logger.getLogger("firstLogger");
        logger.addHandler(fH);
        fH.setFormatter(new SimpleFormatter());
    }

    public Logger getLogger() {
        return logger;
    }
}
