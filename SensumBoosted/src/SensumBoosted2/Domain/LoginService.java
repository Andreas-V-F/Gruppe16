/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SensumBoosted2.Domain;

import SensumBoosted2.Persistence.LoginRepository;
import SensumBoosted2.Persistence.EmployeeRepository;
import SensumBoosted2.Persistence.UserProfileRepository;

/**
 *
 * @author Mikkel Hoeyberg
 */
public class LoginService {

    private String loginUsername;
    private String loginPassword;
    private Staff staff;

    private LoginRepository loginRepository;

    public LoginService(String loginUsername, String loginPassword) {
        this.loginUsername = loginUsername;
        this.loginPassword = loginPassword;
    }

    public boolean validateLogin() {

        boolean validatedLogin = false;
        loginRepository = new LoginRepository();
        if (loginRepository.validateInDatabase(loginUsername, loginPassword)) {
            String[] staffinfo = loginRepository.returnStaffInformation(loginUsername);
            if (staffinfo[0].equals("Borger")) {
                staff = new Staff(loginUsername, new UserProfileRepository().getName(Integer.parseInt(staffinfo[1])) ,staffinfo[0], staffinfo[1], staffinfo[2]);
            } else{
                staff = new Staff(loginUsername, new EmployeeRepository().getName(Integer.parseInt(staffinfo[1])) ,staffinfo[0], staffinfo[1], staffinfo[2]);
            }
            StaffService staffService = new StaffService();
            staffService.setStaff(staff);
            validatedLogin = true;
            return validatedLogin;
        }
        return validatedLogin;
    }

}
