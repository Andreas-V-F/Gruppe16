/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SensumBoosted2.Domain;

/**
 *
 * @author Alex
 */
public class UserInformation2 {
    
    int userid;
    String firstname;
    String middlename;
    String lastname;
    int cpr;
    String address;
    int postalcode;
    String city;
    String email;
    
    public UserInformation2(int userid, String firstName, String middleName,
            String lastName, int cpr, String address, int postalCode, String city,
            String email) {
        this.userid = userid;
        this.firstname = firstName;
        this.middlename = middleName;
        this.lastname = lastName;
        this.cpr = cpr;
        this.address = address;
        this.postalcode = postalCode;
        this.city = city;
        this.email = email;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getCpr() {
        return cpr;
    }

    public void setCpr(int cpr) {
        this.cpr = cpr;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPostalcode() {
        return postalcode;
    }

    public void setPostalcode(int postalcode) {
        this.postalcode = postalcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    @Override
    public String toString() {
        return "Navn: " + firstname + " " + middlename + " " + lastname + "\nCPR: " + cpr + "\nAdresse: " + address;
    }
}

