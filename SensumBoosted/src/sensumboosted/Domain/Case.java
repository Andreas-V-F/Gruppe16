/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sensumboosted.Domain;

import java.sql.Connection;
import java.util.Date;
import sensumboosted.Persistence.DatabaseController;

/**
 *
 * @author Andreas Frederiksen
 */
public class Case {

    private DatabaseController dbController;
    private Connection con;
    private Date date;
    private String service;
    private String caseWorker;
    private boolean isOpen;
    private Diary diary;

    public Case() {
        this.dbController = new DatabaseController();
        this.con = dbController.connect();
    }

    public void saveCase(String textArea) {

        String[] info = dbController.getUserInformation();
        int userID = 4;
        String input = textArea;

        if (dbController.hasOpenCase(userID)) {

            dbController.saveCase(dbController.findCaseID(userID), userID, input);
        } else {

            dbController.createCase(userID, input);
        }
    }
    
    public void closeCase() {
        int userID = 4;
        dbController.closeAllCases(userID);
    }
}
