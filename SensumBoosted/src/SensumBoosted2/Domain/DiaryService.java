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

    public void createDiaryEntry(long diaryID, String text) {
        dr.createDiaryEntry(diaryID, text);

    }

    public void editDiaryEntry(long diaryEntryID, String text) {
        dr.editDiaryEntry(diaryEntryID, text);

    }

    public Long getCaseId(int userID) {
        long id = dr.getDiaryId(userID);
        return id;
    }
    
    public Long getDiaryId(long sagsId) {
        long id = dr.getDiaryId(sagsId);
        return id;
    }

    public List<String> getDiaryEntries(long diaryID) {
        List<String> entries = dr.getDiaryEntries(diaryID);
        return entries;

    }

    public void saveDiary(int userid, String text) {
//        long caseid = getCaseId(userid); -------------------
//        long diaryID = getDiaryId(caseid);

//        createDiaryEntry(diaryID, text);

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
