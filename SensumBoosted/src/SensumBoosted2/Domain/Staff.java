/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SensumBoosted2.Domain;

/**
 *
 * @author Andreas Frederiksen
 */
public class Staff {
    
    private String username;
    private String name;
    private String usertype;
    private String userID;
    private String department;

   

    public Staff(String username, String name, String usertype, String userID, String department) {
        this.username = username;
        this.name = name;
        this.usertype = usertype;
        this.userID = userID;
        this.department = department;
    }

    public String getUsertype() {
        return usertype;
    }

    public String getUsername() {
        return username;
    }

    public String getDepartment() {
        return department;
    }

    public String getName() {
        return name;
    }

    public String getUserID() {
        return userID;
    }
    
    
}
