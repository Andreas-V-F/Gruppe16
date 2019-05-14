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
public class StaffService {

    private static Staff staff;
   

    

    public StaffService() {
    }

    public static void setStaff(Staff staff) {
        StaffService.staff = staff;
        System.out.println(staff.getUsertype());
    }
    

    public String getStaffType() {
        return staff.getUsertype();
    }
    public String getStaffName() {
        return staff.getUsername();
    }
    public String getStaffDepartment() {
        return staff.getDepartment();
    }
    public void clear() {
        StaffService.staff = null;
    }
    

}
