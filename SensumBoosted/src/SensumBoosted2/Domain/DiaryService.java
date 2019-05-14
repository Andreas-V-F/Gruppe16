package SensumBoosted2.Domain;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import SensumBoosted2.Persistence.DiaryRepository;

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

    public Long getCaseId(long userCpr) {
        long id = dr.getDiaryId(userCpr);
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
    
    public Object[] createDiaryEntryTableView(long logbookID) {
        Object[] info = createDiaryEntryTableView(logbookID);
        return info;
    }
    
    
}
