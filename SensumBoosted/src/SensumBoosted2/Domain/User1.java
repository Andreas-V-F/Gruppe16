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
public class User1 {
    private String name;
    private int cpr;
    private String address;
    private String service;
    private int ID;

    public User1(String name, int cpr, String address, String service, int ID) {
        this.name = name;
        this.cpr = cpr;
        this.address = address;
        this.service = service;
        this.ID = ID;
    }

   

    public String getService() {
        return service;
    }

    public int getID() {
        return ID;
    }
    
    @Override
    public String toString() {
        return "Navn: " + name + "\nCPR: " + cpr + "\nAdresse: " + address;
    }
}
