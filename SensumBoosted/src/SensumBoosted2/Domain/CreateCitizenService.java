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

//    //METODE VIRKER IKKE... FIX!
//    public void createCI(String firstname, String middlename, String lastname,
//            int cpr, String address, int postalcode, String city, String email, int phonenumber,
//            String department) {
//        createCitizenRepository.createCitizenInformation(firstname, middlename, lastname, cpr, address, postalcode, city, email, phonenumber, department);
//    }

    public void createCA(String firstname, String middlename, String lastname,
            int cpr, String address, int postalcode, String city, String email, int phonenumber,
            String department, String username, String password, String usertype) {
        createCitizenRepository.createCitizenAccount(firstname, middlename, lastname, cpr, address, postalcode, city, email, phonenumber, department, username, password, usertype);
    }
}
