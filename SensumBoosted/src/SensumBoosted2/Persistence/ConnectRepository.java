/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SensumBoosted2.Persistence;

import SensumBoosted2.Domain.UserInformation2;
import SensumBoosted2.GUI.FXMLUserProfileController;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andreas Frederiksen
 */
public class ConnectRepository {

    private Connection connection = null;
    private final String url = "jdbc:postgresql://balarama.db.elephantsql.com:5432/rsrrjzno";
    private final String userDB = "rsrrjzno";
    private final String passDB = "afVcwMqs2zGaNtod0axmHcsrAuy5u7uD";
    private ResultSet rs;
    private FXMLUserProfileController upController;

    public ConnectRepository() {
        connect();
    }

    public Connection connect() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (java.lang.ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            connection = DriverManager.getConnection(url, userDB, passDB);
            System.out.println("Connected to the PostgreSQL server successfully.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }

    public Connection getConnection() {
        return connection;
    }

    public String getFirstnameWithCPR() {
        try {
            rs = connect().createStatement().executeQuery("SELECT firstname FROM user_information WHERE cpr = " + upController.searchUserTextField.getText());

            while (rs.next()) {
                upController.firstname = rs.getString("firstname");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return upController.firstname;
    }

    public String getMiddlenameWithCPR() {
        try {
            rs = connect().createStatement().executeQuery("SELECT middlename FROM citizen_information WHERE cpr = " + upController.searchUserTextField.getText());

            while (rs.next()) {
                upController.middlename = rs.getString("middlename");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return upController.middlename;
    }

    public String getLastnameWithCPR() {
        try {
            rs = connect().createStatement().executeQuery("SELECT lastname FROM citizen_information WHERE cpr = " + upController.searchUserTextField.getText());

            while (rs.next()) {
                upController.lastname = rs.getString("lastname");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return upController.lastname;
    }

    public int getCPRWithCPR() {
        try {
            rs = connect().createStatement().executeQuery("SELECT lastname FROM citizen_information WHERE cpr = " + upController.searchUserTextField.getText());

            while (rs.next()) {
                upController.cpr = rs.getInt("cpr");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return upController.cpr;
    }

    public String getAddressWithCPR() {
        try {
            rs = connect().createStatement().executeQuery("SELECT address FROM citizen_information WHERE cpr = " + upController.searchUserTextField.getText());

            while (rs.next()) {
                upController.address = rs.getString("address");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return upController.address;
    }

    public int getPostalcodeWithCPR() {
        try {
            rs = connect().createStatement().executeQuery("SELECT postal_code FROM citizen_information WHERE cpr = " + upController.searchUserTextField.getText());

            while (rs.next()) {
                upController.postalcode = rs.getInt("postal_code");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return upController.postalcode;
    }

    public String getCityWithCPR() {
        try {
            rs = connect().createStatement().executeQuery("SELECT city FROM citizen_information WHERE cpr = " + upController.searchUserTextField.getText());

            while (rs.next()) {
                upController.city = rs.getString("city");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return upController.city;
    }

    public String getEmailWithCPR() {
        try {
            rs = connect().createStatement().executeQuery("SELECT email FROM citizen_information WHERE cpr = " + upController.searchUserTextField.getText());

            while (rs.next()) {
                upController.email = rs.getString("email");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return upController.email;
    }

    //HVAD ER FEJLEN HER?!
    public void getUITableView() {
        try {
            rs = connect().createStatement().executeQuery("SELECT * FROM citizen_information");

            while (rs.next()) {
                upController.obListUI.add(new UserInformation2(rs.getInt("user_id"), rs.getString("firstname"),
                        rs.getString("middlename"), rs.getString("lastname"), rs.getInt("cpr"),
                        rs.getString("address"), rs.getInt("postal_code"), rs.getString("city"),
                        rs.getString("email")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void saveUserInformation() {
        try {
            rs = connect().createStatement().executeQuery("UPDATE user_information "
                    + "SET firstname = '" + upController.editFirstnameField.getText() + "', middlename = '" + upController.editMiddlenameField.getText() + "',"
                    + "lastname = '" + upController.editLastnameField.getText() + "', cpr = " + Integer.parseInt(upController.editCPRField.getText()) + ","
                    + "address = '" + upController.editAddressField.getText() + "', postal_code = " + Integer.parseInt(upController.editPostalCodeField.getText()) + ","
                    + "city = '" + upController.editCityField.getText() + "', email = '" + upController.editEmailField.getText() + "'"
                    + " WHERE cpr = " + Integer.parseInt(upController.searchUserTextField.getText()));
            rs.close();

        } catch (SQLException ex) {
            Logger.getLogger(FXMLUserProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
