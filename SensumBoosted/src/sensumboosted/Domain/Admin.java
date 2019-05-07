/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sensumboosted.Domain;

/**
 *
 * @author krute
 */
public class Admin extends User {

    public Admin(String name, String role) {
        super(name, role);
    }

    @Override
    public void addPermissions() {
        addPermission(Permission.adminPermits);
        addPermission(Permission.caseworkerPermits);
    }

}
