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
public class CreateEmployeeRepository {
    
    ConnectRepository connectRepository;
    private Connection connection;
    private ResultSet rs;
    private Encryption encrypt;

    public CreateEmployeeRepository() {
        connectRepository = new ConnectRepository();
        connection = connectRepository.getConnection();
        encrypt = new Encryption();
    }
    
    
    private void createEmployeeInformation(int userId, String firstname, String middlename, String lastname, String email, String usertype) {
        try {
            Statement st = connection.createStatement();
            String sql = "INSERT INTO employee_information "
                    + "(user_id, name, "
                    + "email, user_type)"
                    + " VALUES ('" + userId + "','" + firstname + " " + middlename + " " + lastname
                    + "','" + email + "','" + usertype + "')";
            System.out.println("sdas?asdads?");
            st.executeUpdate(sql);
            System.out.println("sdas??");
            st.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } 
    }

    public void createEmployeeAccount(String firstname, String middlename, String lastname, String email, String usertype, String department, String username, String password) {
        try {
            Statement st = connection.createStatement();
            String sql = "INSERT INTO users "
                    + "(username, password, user_type, department)"
                    + " VALUES ('" + username + "','" + encrypt.encryptString(password) + "','"
                    + usertype + "','" + department + "')";
            st.executeUpdate(sql);
            sql = "SELECT user_id FROM users WHERE username='" + username + "';";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                System.out.println("HALLO??");
                createEmployeeInformation(rs.getInt("user_id"), firstname, middlename, lastname, email, usertype);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
