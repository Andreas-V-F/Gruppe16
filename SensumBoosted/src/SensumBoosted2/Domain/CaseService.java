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

    private Citizen user = new Citizen("Andreas Ibsen Cor", 1212125577, "Univej 1, 5230 Odense M", "Pik", 2);
    private CaseRepository caseRepository;
    private Case case1;
    private static Case preCase;

    public CaseService() {
        case1 = new Case(user);
        this.caseRepository = new CaseRepository();
    }

    public void saveCase(String inquiryText, String inquirer, String assessmentText, String taskPurpose, String taskGoal) {
        
        
       
        Case case2 = new Case(user, inquiryText, null,  new Date(), inquirer, assessmentText, taskPurpose, taskGoal);
        if (caseRepository.hasOpenCase(case2)) {
            caseRepository.saveCase(case2);
        } else {
            caseRepository.createCase(case2);
        }
    }

    public void closeCase() {
        
        caseRepository.closeAllCases(case1);
    }

    public String printToInfo() {
        return user.toString();
    }

    public String[] printToCase() {
        if (caseRepository.hasOpenCase(case1)) {
             Case case2 = caseRepository.CaseText(case1);
        String[] cases = new String[7];
        cases[0] = case2.getInquiryText();
        cases[1] = case2.getInquirer().split("/")[0];
        cases[2] = case2.getInquirer().split("/")[1];
        cases[3] = case2.getInquirer().split("/")[2];
        cases[4] = case2.getAssessment();
        cases[5] = case2.getTaskPurpose();
        cases[6] = case2.getTaskPurpose();
        return cases;
        }
       return null;
    }

    public ObservableList sendPreviousCases() {
        
        ObservableList<Case> cases;
        cases = FXCollections.observableArrayList(caseRepository.getPreviousCases(case1));

        return cases;
    }
    
    public String sendPreviousInquiryText(Object o){
        Case case2 = (Case) o;
        return case2.getInquiryText();
    }
    
    public String sendPreviousAssessmentText(Object o) {
        Case case2 = (Case) o;
        return case2.getAssessment();
    }
    
    public String[] casePrintStrings() {
        Case case2;
        case2 = caseRepository.selectPreviousCase(preCase);
        String[] cases = new String[7];
        cases[0] = case2.getInquiryText();
        cases[1] = case2.getInquirer().split("/")[0];
        cases[2] = case2.getInquirer().split("/")[1];
        cases[3] = case2.getInquirer().split("/")[2];
        cases[4] = case2.getAssessment();
        cases[5] = case2.getTaskPurpose();
        cases[6] = case2.getTaskPurpose();
        return cases;
    }
    public void preCase(Object o) {
        preCase = (Case) o;
    }

}
