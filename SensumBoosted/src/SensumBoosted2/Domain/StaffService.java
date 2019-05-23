/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SensumBoosted2.Domain;

import SensumBoosted2.Persistence.UserProfileRepository;

/**
 *
 * @author Andreas Frederiksen
 */
public class StaffService {

    private static Staff staff;
    private static UserInformation userInfo;

    public StaffService() {
    }

    public static void setStaff(Staff staff) {
        StaffService.staff = staff;
        System.out.println(staff.getUsertype());
    }

    public static void setUserInfo(Object o) {
        try {
            StaffService.userInfo = (UserInformation) o;
            System.out.println(userInfo.getUserid());
        } catch (Exception e) {
        }
    }

    public int getUserID() {
        return userInfo.getUserid();
    }
    

    public String getStaffType() {
        return staff.getUsertype();
    }

    public String getStaffUsername() {
        return staff.getUsername();
    }
    
    public String getStaffName(){
        return staff.getName();
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
    
    public UserInformation getUserInfo(){
        return userInfo;
    }
    
    public String getStaffUserID(){
        return staff.getUserID();
    }

    public Object getUserInfoFromUserID(String staffUserID) {
        UserProfileRepository userProfileRepository = new UserProfileRepository();
        return userProfileRepository.getUserinfo(Integer.parseInt(staffUserID));
    }

}
