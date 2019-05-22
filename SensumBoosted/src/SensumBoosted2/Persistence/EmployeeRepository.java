/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SensumBoosted2.Persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Andreas Ibsen Cor
 */
public class EmployeeRepository {
    
    ConnectRepository connectRepository;
    private Connection connection;
    private ResultSet rs;
    
    public EmployeeRepository() {
        connectRepository = new ConnectRepository();
        connection = connectRepository.getConnection();
    }
    
    public String getName(int userID) {
        try {
            Statement st = connection.createStatement();
            String sql = "SELECT name FROM employee_information WHERE user_id = '" + userID + "'";
            rs = st.executeQuery(sql);
            String name = null;
            while(rs.next()){
                name = rs.getString("name");
            }
            st.close();
            return name;
        } catch (SQLException ex) {
        }
        return null;

    }
    
}
