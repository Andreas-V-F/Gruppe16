/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SensumBoosted2.Domain;

import SensumBoosted2.Persistence.CaseRepository;
import java.util.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Andreas Frederiksen
 */
public class CaseService {

    private User1 user = new User1("Andreas Ibsen Cor", 1212125577, "Univej 1, 5230 Odense M", "Pik", 1);
    private CaseRepository caseRepository;
    private Case case1;

    public CaseService() {

        this.caseRepository = new CaseRepository();
    }

    public void saveCase(String text) {
        Case case2 = new Case(user);
        String input = "";
        if ("".equals(caseRepository.CaseText(case2))) {
             input = "dato: " + new Date() + "\t" + text;
        }
        else {
            input = caseRepository.CaseText(case2) +  "\n" + "dato: " + new Date() + "\t" + text;
        }
       
        case1 = new Case(user, input, "test", new Date(), true);
        if (caseRepository.hasOpenCase(case1)) {
            caseRepository.saveCase(case1);
        } else {
            caseRepository.createCase(case1);
        }
    }

    public void closeCase() {
        case1 = new Case(user);
        caseRepository.closeAllCases(case1);
    }

    public String printToInfo() {
        return user.toString();
    }

    public String printToCase() {
        case1 = new Case(user);
        return caseRepository.CaseText(case1);
    }

    public ObservableList sendPreviousCases() {
        case1 = new Case(user);
        ObservableList<Case> cases;
        cases = FXCollections.observableArrayList(caseRepository.getPreviousCases(case1));

        return cases;
    }
    
    public String sendPreviousCaseText(Object o){
        Case case2 = (Case) o;
        return case2.getText();
    }

}
