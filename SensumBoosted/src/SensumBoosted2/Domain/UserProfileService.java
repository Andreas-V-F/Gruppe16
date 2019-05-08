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
    
    
    private UserProfileRepository upRep;
    private UserInformation2 ui;
    
    public UserProfileService() {
        this.upRep = new UserProfileRepository();
    }
    
    public ObservableList getUI() {
        ObservableList<UserInformation2> ui2;
        ui2 = FXCollections.observableArrayList(upRep.getCitizenInformation());
        
        return ui2;
    }
}
