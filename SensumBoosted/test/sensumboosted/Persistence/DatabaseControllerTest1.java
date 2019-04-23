/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sensumboosted.Persistence;

import java.sql.Connection;
import java.sql.Statement;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import sensumboosted.Domain.DBcontroller;

/**
 *
 * @author andersschjonning
 */
public class DatabaseControllerTest1 {
    
    DatabaseController db;
    int userId = 111;
    
    @Before
    public void setUp() throws Exception {
        db = new DatabaseController();
        db.connect();
        db.createUser(userId, "username", "password", "userType");
    }
  
    
    @Test
    public void deleteUser() {
       int i = db.getUserIDCount();
       db.deleteUser(userId);
       Assert.assertTrue(db.getUserIDCount() == i-1);
   }
    
   
        
}
