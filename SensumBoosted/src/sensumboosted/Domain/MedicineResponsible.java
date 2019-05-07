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
public class MedicineResponsible extends User {

    public MedicineResponsible(String name, String role) {
        super(name, role);
    }
    
    @Override
    public void addPermissions() {
        addPermission(Permission.adminPermits);
    }
}
