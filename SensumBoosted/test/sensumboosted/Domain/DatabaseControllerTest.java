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
public class DatabaseControllerTest {
    
    DatabaseController controller;
    
    @Before
    public void setUp() throws Exception {
        controller = new DatabaseController();
        Connection connection = controller.connect();
            Statement st = connection.createStatement();
            String sql = "DELETE FROM users WHERE user_id = 111";
            st.execute(sql);
    }
  
    
    @Test
    public void createUser() {
       int i = controller.getUserIDCount();
       controller.createUser(111, "username", "password", "userType");
       Assert.assertTrue(controller.getUserIDCount() == i+1);
   }
        
}
