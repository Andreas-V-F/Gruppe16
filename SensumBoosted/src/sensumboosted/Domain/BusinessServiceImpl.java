/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sensumboosted.Domain;

import java.sql.Connection;

/**
 *
 * @author andersschjonning
 */
public class BusinessServiceImpl implements BusinessService {
    
    DatabaseController db;
    
    public BusinessServiceImpl() {
        db = new DatabaseController();
        db.connect();
    }
    
    public void addLog(final int userId, final String text) {
        Long id = db.getLogBook(userId);
        if(id == null) {
            id = db.createLogbook(userId);
        }
        db.createLogbookEntry(id, text);
    }
}
