/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SensumBoosted2.Domain;

import SensumBoosted2.Persistence.LogRepository;

/**
 *
 * @author Mikkel Hoeyberg
 */
public class LogService {

    private LogRepository logRepository = new LogRepository();;
    private static LogService instance;

    private LogService() {
    }

    public static LogService getInstance() {
        if (instance == null) {
            instance = new LogService();
        }
        return instance;
    }

    public String getLogFile() {
        return logRepository.getLogFile("LoginLog.txt");
    }
}
