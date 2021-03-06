/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SensumBoosted2.Persistence;

import SensumBoosted2.Domain.LogService;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.stream.Stream;

/**
 *
 * @author Mikkel Hoeyberg
 */
public class LogRepository {

    private Logger logger;
    private static LogRepository instance;
    private LogRepository myLog;
    private FileHandler fH;

    private LogRepository() {
    }
    
        public static LogRepository getInstance() {
        if (instance == null) {
            instance = new LogRepository();
        }
        return instance;
    }

    public void createLog(String fileName) throws SecurityException, IOException {

        File f = new File(fileName);
        if (!f.exists()) {
            f.createNewFile();
        }
        fH = new FileHandler(fileName, true);
        logger = Logger.getLogger(fileName);
        logger.addHandler(fH);
        fH.setFormatter(new SimpleFormatter());
    }

    public void logLoginAttempt(String username, String attempt) {
        try {
            myLog.createLog("LoginLog.txt");
            myLog.getLogger().setLevel(Level.ALL);
            myLog.getLogger().info("Bruger: \"" + username + "\" " + attempt);
        } catch (SecurityException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public Logger getLogger() {
        return logger;
    }

    public String getLogFile(String filePath) {
        StringBuilder sb = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(filePath), StandardCharsets.UTF_8)) {
            stream.forEach((s) -> sb.append(s).append("\n"));
        } catch (IOException ex) {
            Logger.getLogger(LogRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sb.toString();
    }

}
