/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SensumBoosted2.Domain;

/**
 *
 * @author krute
 */
public class DiaryEntry {

    String text;
    int diaryEntryId;
    
    public DiaryEntry(String text, int diaryEntryId) {
        this.text = text;
        this.diaryEntryId = diaryEntryId;
    }
    
    public DiaryEntry(String text) {
        this.text = text;
    }
    
    public int getDiaryId() {
        return diaryEntryId;
    }

    public void setDiaryId(int diaryEntryId) {
        this.diaryEntryId = diaryEntryId;
    }
    
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    

}

