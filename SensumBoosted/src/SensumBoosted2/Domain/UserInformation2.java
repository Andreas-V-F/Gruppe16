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
    String firstName;
    String middleName;
    String lastName;
    int cpr;
    String address;
    int postalCode;
    String city;
    String email;
    
    public UserInformation2(int userid, String firstName, String middleName,
            String lastName, int cpr, String address, int postalCode, String city,
            String email) {
        this.userid = userid;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.cpr = cpr;
        this.address = address;
        this.postalCode = postalCode;
        this.city = city;
        this.email = email;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
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
}

