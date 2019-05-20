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

    private CaseRepository caseRepository;
    private StaffService staffService;
    private static Case preCase;

    public CaseService() {
        staffService = new StaffService();
        this.caseRepository = new CaseRepository();
    }

    public void saveCase(String inquiryText, String inquirer, String assessmentText, String taskPurpose, String taskGoal) {

        if (caseRepository.hasOpenCase(staffService.getUserID())) {
            caseRepository.saveCase(staffService.getUserID(), inquiryText, inquirer, assessmentText, taskPurpose, taskGoal);
        } else {
            caseRepository.createCase(staffService.getUserID(), inquiryText, inquirer, assessmentText, taskPurpose, taskGoal);
        }
    }

    public void closeCase() {
        caseRepository.closeAllCases(staffService.getUserID());
    }

    public String printToInfo() {
        return staffService.getUserInfo().toString();
    }

    public String[] printToCase() {
        if (caseRepository.hasOpenCase(staffService.getUserID())) {
            String[] caseText = caseRepository.CaseText(staffService.getUserID());
            if (!caseText[1].equals("")) {
                String[] cases = {caseText[0], caseText[1].split("/")[0], caseText[1].split("/")[1], caseText[1].split("/")[2], caseText[2], caseText[3], caseText[4]};
                return cases;
            }
            else{
                String[] cases = {caseText[0], "Borger", "", "Ja", caseText[2], caseText[3], caseText[4]};
                return cases;
            }
        }
        return null;
    }

    public ObservableList sendPreviousCases() {

        ObservableList<Case> cases;
        cases = FXCollections.observableArrayList(caseRepository.getPreviousCases(staffService.getUserID()));

        return cases;
    }

    public String sendPreviousInquiryText(Object o) {
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
