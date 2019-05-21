package SensumBoosted2.Domain;

import SensumBoosted2.Persistence.DiaryRepository;
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

    public void editDiaryEntry(long diaryEntryID, String text) {
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

    public List<String> getDiaryEntries(int diaryID) {
        List<String> entries = dr.getDiaryEntries(diaryID);
        return entries;

    }

    public void saveDiary(long entryId, String text) {
        long diaryID = getDiaryIdByEntryId(entryId);

        createDiaryEntry(diaryID, text);

    }

    public void editDiary(long diaryID, String text) {
        editDiaryEntry(diaryID, text);


    }

    public void deleteDiaryEntry(long diaryEntryId) {
        dr.deleteDiaryEntry(diaryEntryId);

    }
    
    public ObservableList createDiaryEntryTableView(long logbookID) {
        ObservableList<DiaryEntry> diaries;
        diaries = FXCollections.observableArrayList(dr.createDiaryEntryTableView(logbookID));
        return diaries;
    }
    
    
}
