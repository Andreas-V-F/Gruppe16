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
    private static UserInformation2 userInfo;
    private int userId;

    public StaffService() {
    }

    public static void setStaff(Staff staff) {
        StaffService.staff = staff;
        System.out.println(staff.getUsertype());
    }

    public static void setUserInfo(Object o) {
        try {
            StaffService.userInfo = (UserInformation2) o;
            System.out.println(userInfo.getUserid());
        } catch (Exception e) {
        }
    }

    public void setUserID() {
        userId = userInfo.getUserid();
    }
    
    public int getUserID() {
        return userId;
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

    public void clearStaff() {
        StaffService.staff = null;
    }
    
    public void clearUserInfo() {
        StaffService.userInfo = null;
    }
    

}
