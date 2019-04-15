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
    
    @Before
    public void setUp() throws Exception {
        service = new BusinessServiceImpl();
        db = new DatabaseController();
        db.connect();
    }
  
    
    @Test
    public void createLogEntry() {
       int cnt = db.getCount("logbook");
       service.addLog(111, "text");
       Assert.assertEquals(db.getCount("logbook"), cnt );
   }
        
}
