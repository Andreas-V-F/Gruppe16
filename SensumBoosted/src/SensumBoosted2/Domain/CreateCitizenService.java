/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SensumBoosted2.Domain;

import SensumBoosted2.Persistence.CreateCitizenRepository;

/**
 *
 * @author Alex
 */
public class CreateCitizenService {

    CreateCitizenRepository createCitizenRepository = new CreateCitizenRepository();

    public void createCA(String firstname, String middlename, String lastname,
            int cpr, String address, int postalcode, String city, String email, int phonenumber,
            String department, String password, String usertype) {
        createCitizenRepository.createCitizenAccount(firstname, middlename, lastname, cpr, address, postalcode, city, email, phonenumber, department, password, usertype);
    }

    public boolean cprCheck(int cpr) {
        return createCitizenRepository.checkCprRepo(cpr);
    }
    
    public boolean numberCheck(int number) {
        return createCitizenRepository.checkPhoneRepo(number);
    }
}
