/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SensumBoosted2.Domain;

import java.util.Date;

/**
 *
 * @author Andreas Frederiksen
 */
public class Case {
    
    private long caseID;
    private UserInformation userInfo;
    private String inquiryText;
    private Date addedDate;
    private Date editDate;
    private String inquirer;
    private String assessment;
    private String taskPurpose;
    private String taskGoal;

    public Case(long caseID, UserInformation userInfo, String inquiryText, Date addedDate, Date editDate, String inquirer, String assessment, String taskPurpose, String taskGoal) {
        this.caseID = caseID;
        this.userInfo = userInfo;
        this.inquiryText = inquiryText;
        this.addedDate = addedDate;
        this.editDate = editDate;
        this.inquirer = inquirer;
        this.assessment = assessment;
        this.taskPurpose = taskPurpose;
        this.taskGoal = taskGoal;
    }

    public Case(long caseID, String inquiryText, Date addedDate, Date editDate, String assessment) {
        this.caseID = caseID;
        this.inquiryText = inquiryText;
        this.addedDate = addedDate;
        this.editDate = editDate;
        this.assessment = assessment;
    }

    public String getInquiryText() {
        return inquiryText;
    }

    public Date getAddedDate() {
        return addedDate;
    }

    public Date getEditDate() {
        return editDate;
    }

    public String getAssessment() {
        return assessment;
    }

    public UserInformation getUserInfo() {
        return userInfo;
    }

    public String getInquirer() {
        return inquirer;
    }

    public String getTaskPurpose() {
        return taskPurpose;
    }

    public String getTaskGoal() {
        return taskGoal;
    }
    
    public long getCaseID(){
        System.out.println("returned" + caseID);
        return caseID;
    }
    
    

}
