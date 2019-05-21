package SensumBoosted2.Domain;

import SensumBoosted2.Persistence.DiaryRepository;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DiaryService {

    DiaryRepository dr = new DiaryRepository();

    public void createDiary(int sagsId) {
        dr.createDiary(sagsId);
    }

    public void createDiaryEntry(int diaryID, String text) {
        dr.createDiaryEntry(diaryID, text);

    }

    public void editDiary(int diaryEntryID, String text) {
        dr.editDiaryEntry(diaryEntryID, text);

    }

    public int getCaseId(int userID) {
        System.out.println("test");
        int id = dr.getCaseId(userID);
        System.out.println("test");
        return id;
    }
    
    public int getDiaryId(int sagsId) {
        int id = dr.getDiaryId(sagsId);
        return id;
    }
    
    public int getDiaryIdByEntryId(int entryId) {
        int id = dr.getDiaryIdByEntryId(entryId);
        return id;
    }

    public List<String> getDiaryEntries(int diaryID) {
        List<String> entries = dr.getDiaryEntries(diaryID);
        return entries;

    }

    public void saveDiary(int diaryId, String text) {
        int diaryID = dr.getDiaryIdByEntryId(diaryId);

        createDiaryEntry(diaryID, text);

    }

    public void deleteDiaryEntry(int diaryEntryId) {
        dr.deleteDiaryEntry(diaryEntryId);

    }
    
    public ObservableList createDiaryEntryTableView(int logbookID) {
        ObservableList<DiaryEntry> diaries;
        diaries = FXCollections.observableArrayList(dr.createDiaryEntryTableView(logbookID));
        return diaries;
    }
    
    
}
