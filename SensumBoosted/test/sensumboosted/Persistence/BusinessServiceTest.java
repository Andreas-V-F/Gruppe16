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

/**
 *
 * @author andersschjonning
 */
public class BusinessServiceTest {

    DatabaseController db;
    int userId = 111;

    @Before
    public void setUp() throws Exception {
        db = new DatabaseController();
        db.connect();
        db.deleteUser(userId);
        db.deleteLogbookEntry(userId);
        db.createUser("username", "password", "userType");
    }

    @Test
    public void createLogEntry() {
        int cnt = db.getCount("logbook");
        Assert.assertEquals(db.getCount("logbook"), cnt);
    }

}
