/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sensumboosted.Domain;

import java.sql.Connection;
import java.sql.Statement;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author andersschjonning
 */
public class BusinessServiceTest {
    
    BusinessServiceImpl service; 
    DatabaseController db;
    int userId = 111;
    
    @Before
    public void setUp() throws Exception {
        service = new BusinessServiceImpl();
        db = new DatabaseController();
        db.connect();
        db.deleteUser(userId);
        db.createUser(userId, "username", "password", "userType");
        db.deleteLogbook(userId);
    }
  
    
    @Test
    public void createLogEntry() {
       int cnt = db.getCount("logbook");
       service.addLog(userId, "text");
       Assert.assertEquals(db.getCount("logbook"), cnt );
   }
        
}
