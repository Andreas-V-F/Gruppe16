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
public abstract class Staff {
    private String name;
    private String roleName;
    private String username;

    public Staff(String name, String roleName, String username) {
        this.name = name;
        this.roleName = roleName;
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public String getRoleName() {
        return roleName;
    }

    public String getUsername() {
        return username;
    }
    
    
}
