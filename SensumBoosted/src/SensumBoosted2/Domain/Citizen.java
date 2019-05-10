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
public class Citizen extends User{
    
    public Citizen(String name, int cpr, String address, String service, int ID) {
        super(name, cpr, address, service, ID);
    }
    
}
