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

    public void createDiary(long sagsId, int userID) {
        try {
            Statement st = connection.getConnection().createStatement();
            long id = System.currentTimeMillis();
            String sql = "INSERT INTO diary "
                    + "(case_id, diary_id, user_id)"
                    + " VALUES ('" + sagsId + "','" + id + "','" + userID + "');";
            st.execute(sql);
            st.close();
            createDiaryEntry(id, "Ny note", "Sagsarbejder");
        } catch (SQLException ex) {
            Logger.getLogger(SensumBoosted2.Persistence.DiaryRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void createDiaryEntry(long diaryID, String text, String permission) {
        try {
            Statement st = connection.getConnection().createStatement();
            long entryID = System.currentTimeMillis();
            Date date = new Date();
            Timestamp timestamp = new Timestamp(date.getTime());
            String sql = "INSERT INTO diary_entry "
                    + "(diary_entry_id, diary_id, entry_text, create_timestamp, permission)"
                    + " VALUES "
                    + "(" + entryID + ',' + diaryID + ",'" + text + "', '" + timestamp + "', '" + permission + "__Administrator__Borger" + "')";
            st.execute(sql);
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(SensumBoosted2.Persistence.DiaryRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void editDiaryEntry(long diaryEntryID, String text) {
        try {
            Statement st = connection.getConnection().createStatement();
            Date date = new Date();
            Timestamp timestamp = new Timestamp(date.getTime());
            String sql = "UPDATE public.diary_entry"
                    + "    SET entry_text= '" + text + "', create_timestamp= '" + timestamp + "'"
                    + "    WHERE diary_entry_id = " + diaryEntryID + "; ";
            st.execute(sql);
            st.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public long getOriginalCaseID(int userID) {
        long id = 0;

        try (Statement st = connection.getConnection().createStatement()) {
            String sql = "SELECT case_id FROM sager WHERE user_id = " + userID + " ORDER BY case_id ASC";
            rs = st.executeQuery(sql);
            while (rs.next()) {
                id = rs.getLong("case_id");
                break;
            }
            return id;
        } catch (SQLException ex) {
            Logger.getLogger(SensumBoosted2.Persistence.DiaryRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

    public long getDiaryId(long sagsId) {
        long id = 0;
        try (Statement st = connection.getConnection().createStatement()) {
            String sql = "SELECT diary_id FROM diary where case_id = " + sagsId;

            rs = st.executeQuery(sql);
            while (rs.next()) {
                id = rs.getLong("diary_id");

            }
        } catch (SQLException ex) {
            Logger.getLogger(SensumBoosted2.Persistence.DiaryRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

    public long getDiaryIdByEntryId(long entryId) {
        long id = 0;
        try (Statement st = connection.getConnection().createStatement()) {
            String sql = "SELECT diary_id FROM diary_entry where diary_entry_id = " + entryId;

            rs = st.executeQuery(sql);
            while (rs.next()) {
                id = rs.getLong("diary_id");

            }
        } catch (SQLException ex) {
            Logger.getLogger(SensumBoosted2.Persistence.DiaryRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

    public List<String> getDiaryEntries(int diaryID, String perm) {
        List<String> entries = new ArrayList<>();

        try (Statement st = connection.getConnection().createStatement()) {
            String sql = "SELECT entry_text, permission FROM diary_entry WHERE diary_id = " + diaryID;

            rs = st.executeQuery(sql);
            while (rs.next()) {
                if (rs.getString("permission").contains(perm)) {
                    entries.add(rs.getString("entry_text"));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(SensumBoosted2.Persistence.DiaryRepository.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(SensumBoosted2.Persistence.DiaryRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cnt;
    }

    public void deleteDiaryEntry(long diaryEntryId) {
        //int id = getDiaryId(getCaseId(diaryEntryId));
        //System.out.print(id);

        try (Statement st = connection.getConnection().createStatement()) {
            String sql = "DELETE FROM public.diary_entry WHERE diary_entry_id = " + diaryEntryId;
            st.execute(sql);

        } catch (SQLException ex) {
            Logger.getLogger(SensumBoosted2.Persistence.DiaryRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List createDiaryEntryTableView(long logbookID, String permission) {
        List<DiaryEntry> diaries = new ArrayList<>();
        try {
            ResultSet rs = connection.getConnection().createStatement().executeQuery("SELECT diary_entry_id, entry_text, permission FROM diary_entry WHERE diary_id = " + logbookID + "ORDER BY create_timestamp DESC");

            while (rs.next()) {
                if (rs.getString("permission").contains(permission)) {
                    String text = rs.getString("entry_text");
                    long diaryId = rs.getLong("diary_entry_id");

                    diaries.add(new DiaryEntry(text, diaryId));
                } 

            }
            return diaries;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public long getMedicinEntryID(int cpr, long diaryID) {
        try (Statement st = connection.getConnection().createStatement()) {
            String sql = "SELECT * FROM diary_entry WHERE diary_id = " + diaryID;
            rs = st.executeQuery(sql);
            while (rs.next()) {
                if (rs.getString("entry_text").contains("Medicinudlevering for: " + cpr)) {
                    return rs.getLong("diary_entry_id");
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(SensumBoosted2.Persistence.DiaryRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public String getEntryText(long entryID) {
        try (Statement st = connection.getConnection().createStatement()) {
            String sql = "SELECT entry_text FROM diary_entry WHERE diary_entry_id = " + entryID;
            rs = st.executeQuery(sql);
            while (rs.next()) {
                return rs.getString("entry_text");
            }

        } catch (SQLException ex) {
            Logger.getLogger(SensumBoosted2.Persistence.DiaryRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String getCreatorPerm(long diaryEntryID) {
        try (Statement st = connection.getConnection().createStatement()) {
            String sql = "SELECT permission FROM diary_entry WHERE diary_entry_id = " + diaryEntryID;
            rs = st.executeQuery(sql);
            while (rs.next()) {
                return rs.getString("permission").split("__")[0];
            }

        } catch (SQLException ex) {
            Logger.getLogger(SensumBoosted2.Persistence.DiaryRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
