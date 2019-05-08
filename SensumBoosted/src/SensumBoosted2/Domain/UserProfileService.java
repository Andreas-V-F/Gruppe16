/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SensumBoosted2.Domain;

import SensumBoosted2.Persistence.UserProfileRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author nguye
 */
public class UserProfileService {
    
    
    private UserProfileRepository userProfileRepository;
    
    public UserProfileService() {
        this.userProfileRepository = new UserProfileRepository();
    }
    
    public ObservableList getUI() {
        ObservableList<UserInformation2> ui;
        ui = FXCollections.observableArrayList(userProfileRepository.getCitizenInformation());
        
        return ui;
    }
    
    public ObservableList cprSearchUI(String cpr) {
        ObservableList<UserInformation2> ui;
        ui = FXCollections.observableArrayList(userProfileRepository.cprSearchCitizenInformation(Integer.parseInt(cpr)));
        
        return ui;
    }
    
    public ObservableList firstnameSearchUI(String firstname) {
        ObservableList<UserInformation2> ui;
        ui = FXCollections.observableArrayList(userProfileRepository.firstnameSearchCitizenInformation(firstname));
        
        return ui;
    }
    
    public void saveUI(String firstname, String middlename, String lastname, int cpr, String address,
                int postalcode, String city, String email, int selectedUserID) {
        userProfileRepository.saveUserInformation(firstname, middlename, lastname, cpr, address, postalcode, city, email, selectedUserID);
    }
}
