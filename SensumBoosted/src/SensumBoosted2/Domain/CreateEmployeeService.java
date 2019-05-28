/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SensumBoosted2.Domain;


import SensumBoosted2.Persistence.CreateEmployeeRepository;

/**
 *
 * @author Andreas Ibsen Cor
 */
public class CreateEmployeeService {
    
    CreateEmployeeRepository createEmployeeRepository = new CreateEmployeeRepository();

    public void createEA(String firstname, String middlename, String lastname, String email, String usertype, String department, String username, String password) {
        createEmployeeRepository.createEmployeeAccount(firstname, middlename, lastname, email, usertype, department, username, password);
    }

}
