package SensumBoosted2.Domain;

import SensumBoosted2.Persistence.DiaryRepository;
import java.util.Date;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DiaryService {

    DiaryRepository dr = new DiaryRepository();
    StaffService staffService = new StaffService();

    public void createDiary(long sagsId, int userID) {
        dr.createDiary(sagsId, userID);
    }

    public void createDiaryEntry(long diaryID, String text) {
        dr.createDiaryEntry(diaryID, text, staffService.getStaffType());

    }

    public void editDiary(long diaryEntryID, String text) {
        dr.editDiaryEntry(diaryEntryID, text);

    }

    public long getOriginalCaseID(int userID) {
        long id = dr.getOriginalCaseID(userID);
        return id;
    }
    
    public long getDiaryId(long sagsId) {
        long id = dr.getDiaryId(sagsId);
        return id;
    }
    
    public long getDiaryIdByEntryId(long entryId) {
        long id = dr.getDiaryIdByEntryId(entryId);
        return id;
    }

    public void deleteDiaryEntry(long diaryEntryId) {
        dr.deleteDiaryEntry(diaryEntryId);

    }
    
    public ObservableList createDiaryEntryTableView(long logbookID) {
        ObservableList<DiaryEntry> diaries;
        diaries = FXCollections.observableArrayList(dr.createDiaryEntryTableView(logbookID, staffService.getStaffType()));
        return diaries;
    }
    
    public void medicineDiaryEntry(long diaryID, String medicine, String amount, long cpr){
        String output = new Date() + "\t" + medicine + "\t" + amount + "\n";
        if(dr.getMedicinEntryID(cpr, diaryID) == -1){
            dr.createDiaryEntry(diaryID, "Medicinudlevering for: " + cpr + "\n" + output, "Medicinansvarlig");
        } else{
            dr.editDiaryEntry(dr.getMedicinEntryID(cpr, diaryID), dr.getEntryText(dr.getMedicinEntryID(cpr, diaryID)) + output);
        };
    }
    
    public String getCreatorPerm(long diaryEntryID){
        return dr.getCreatorPerm(diaryEntryID);
    }
    
    
}
