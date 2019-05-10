package SensumBoosted2.Persistence;

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
                    + "(sags_id, diary_id)"
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

    public Long getDiaryId(long sagsId) {
        Long id = null;
        try (Statement st = connection.getConnection().createStatement()) {
            String sql = "SELECT diary_id FROM diary where sags_id = " + sagsId;

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
        List<String> entries = new ArrayList<String>();
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
}
