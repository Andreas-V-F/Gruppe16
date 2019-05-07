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

    private User user;
//    private User caseWorker;
//    private Diary diary;
    private String text;
    private String service;
    private Date date;
    private boolean isOpen;
    private Date lastEditDate;

    public Case(User user, /*User caseWorker,*/ String text, String service, Date date, boolean isOpen/*, Diary diary*/) {
        this.user = user;
//        this.caseWorker = caseWorker;
        this.text = text;
        this.service = service;
        this.date = date;
        this.isOpen = isOpen;
//        this.diary = diary;
    }

    public Case(String text, Date addedDate, Date lastEditDate) {
        this.text = text;
        this.date = addedDate;
        this.lastEditDate = lastEditDate;
    }

    public Case(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public String getText() {
        return text;
    }

    public Date getDate() {
        return date;
    }

    public Date getLastEditDate() {
        return lastEditDate;
    }

}
