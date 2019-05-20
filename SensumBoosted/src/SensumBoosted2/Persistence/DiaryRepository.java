package SensumBoosted2.Persistence;

import SensumBoosted2.Domain.DiaryEntry;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DiaryRepository {

    ConnectRepository connection = new ConnectRepository();
    private ResultSet rs = null;

    public Long createDiary(int sagsId) {
        try {
            Statement st = connection.getConnection().createStatement();
            Long id = System.currentTimeMillis();
            String sql = "INSERT INTO diary "
                    + "(case_id, diary_id)"
                    + " VALUES "
                    + "(" + sagsId + ',' + id + ")";
            st.execute(sql);
            st.close();
            return id;
        } catch (SQLException ex) {
            Logger.getLogger(sensumboosted.Persistence.DatabaseController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void createDiaryEntry(long diaryID, String text) {
        try {
            Statement st = connection.getConnection().createStatement();
            Long entryID = System.currentTimeMillis();
            Date date = new Date();
            Timestamp timestamp = new Timestamp(date.getTime());
            String sql = "INSERT INTO diary_entry "
                    + "(diary_entry_id, diary_id, entry_text, create_timestamp)"
                    + " VALUES "
                    + "(" + entryID + ',' + diaryID + ",'" + text + "', '" + timestamp + "')";
            System.out.println("slq :" + sql);
            st.execute(sql);
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(sensumboosted.Persistence.DatabaseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void editDiaryEntry(long diaryEntryID, String text) {
        try {
            Statement st = connection.getConnection().createStatement();
            Long entryID = System.currentTimeMillis();
            Date date = new Date();
            Timestamp timestamp = new Timestamp(date.getTime());
            String sql = "UPDATE public.diary_entry"
                    + "    SET entry_text= '" + text + "', create_timestamp= '" + timestamp + "'"
                    + "    WHERE diary_entry_id = " + diaryEntryID + "; ";
            System.out.println("slq :" + sql);
            st.execute(sql);
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(sensumboosted.Persistence.DatabaseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Long getCaseId(long userCpr) {
        Long id = null;
        try (Statement st = connection.getConnection().createStatement()) {
            String sql = "SELECT case_id "
                    + "	FROM public.sager; where user_cpr" + userCpr;

            rs = st.executeQuery(sql);
            while (rs.next()) {
                id = rs.getLong("case_id");

            }
        } catch (SQLException ex) {
            Logger.getLogger(sensumboosted.Persistence.DatabaseController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

    public Long getDiaryId(long caseId) {
        Long id = null;
        try (Statement st = connection.getConnection().createStatement()) {
            String sql = "SELECT diary_id FROM diary where case_id = " + caseId;

            rs = st.executeQuery(sql);
            while (rs.next()) {
                id = rs.getLong("diary_id");

            }
        } catch (SQLException ex) {
            Logger.getLogger(sensumboosted.Persistence.DatabaseController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

    public List<String> getDiaryEntries(long diaryID) {
        List<String> entries = new ArrayList<>();
        long timestamp = 0;

        try (Statement st = connection.getConnection().createStatement()) {
            String sql = "SELECT entry_text, create_timestamp FROM diary_entry WHERE diary_id = " + diaryID + "order by create_timestamp DESC";

            rs = st.executeQuery(sql);
            while (rs.next()) {
                System.out.println("");
                entries.add(rs.getString("entry_text"));
                //timestamp = rs.getLong("create_timestamp");

            }
        } catch (SQLException ex) {
            Logger.getLogger(sensumboosted.Persistence.DatabaseController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return entries;
    }

    public int getCount(String tableName) {
        int cnt = 0;
        try (Statement st = connection.getConnection().createStatement()) {
            String sql = "SELECT COUNT(*) AS cnt FROM " + tableName;

            rs = st.executeQuery(sql);
            while (rs.next()) {
                cnt = rs.getInt("cnt");

            }
        } catch (SQLException ex) {
            Logger.getLogger(sensumboosted.Persistence.DatabaseController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cnt;
    }

    public void deleteDiaryEntry(long diaryEntryId) {
        //long id = getDiaryId(getCaseId(diaryEntryId));
        //System.out.print(id);

        try (Statement st = connection.getConnection().createStatement()) {
            String sql = "DELETE FROM public.diary_entry WHERE diary_entry_id = " + diaryEntryId;
            st.execute(sql);

        } catch (SQLException ex) {
            Logger.getLogger(sensumboosted.Persistence.DatabaseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List createDiaryEntryTableView(long logbookID) {
        List<DiaryEntry> diaries = new ArrayList<>();
        try {
            ResultSet rs = connection.getConnection().createStatement().executeQuery("SELECT * FROM diary_entry WHERE diary_id = " + logbookID + "order by create_timestamp DESC");

            while (rs.next()) {
                String text = rs.getString("entry_text");
                Long diaryId = rs.getLong("diary_entry_id");

                diaries.add(new DiaryEntry(text, diaryId));

                return diaries;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

}
