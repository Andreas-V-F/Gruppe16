/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sensumboosted.Domain;

import java.util.ArrayList;
import sensumboosted.Interface.IRole;

/**
 *
 * @author krute
 */
public abstract class User implements IRole {

    private String name;
    private String role;
    private ArrayList<String> permitList = new ArrayList<>();

    public User(String name, String role) {
        this.name = name;
        this.role = role;
        addPermissions();
    }

    public String getRole() {
        return role;
    }

    @Override
    public boolean hasPermission(String s) {
        boolean hasPermit = false;
        for (String p : permitList) {
            if (s.equals(p)) {
                return true;
            }
        }
        return hasPermit;
    }

    @Override
    public void addPermission(String s) {
        permitList.add(s);
    }
}
