/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * Mikkel har lavet denne linje som test og skal fjernes igen
 */
package sensumboosted.Persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
//import org.postgresql.core.QueryExecutor;
import sensumboosted.Domain.Encryption;

/**
 *
 * @author Mikkel Hoeyberg
 */
public class DatabaseController {

    private final String url = "jdbc:postgresql://balarama.db.elephantsql.com:5432/rsrrjzno";
    private final String userDB = "rsrrjzno";
    private final String passDB = "afVcwMqs2zGaNtod0axmHcsrAuy5u7uD";
    private Encryption encrypt;
    private int count;
    private String hasUserInformation;
    private String hasUser;

    public DatabaseController() {
    }

    private ResultSet rs = null;
    private Connection connection = null;

    public Connection connect() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (java.lang.ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            connection = DriverManager.getConnection(url, userDB, passDB);
            System.out.println("Connected to the PostgreSQL server successfully.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }

    public String checkLogin(String user, String pass) {
        System.out.println("Checking login in progress, please wait.");
        try {
            Statement st = connection.createStatement();
            String sql = "SELECT username,password FROM users WHERE username='" + user + "' AND password='" + pass + "'";
            rs = st.executeQuery(sql);
            int count = 0;
            while (rs.next()) {
                count++;
            }
//          If there is no user found in the database
            if (count == 0) {
                System.out.println("No user found");  // Delete this when we hand in our code
                return "Username or Password is wrong!";
            }
//          If there is only 1 of the user in the database
            if (count == 1) {
                System.out.println("User found");  // Delete this when we hand in our code
                return "Succesful login";
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return "Login check failed!";
    }

    public void createCase(int userID, String text) {
        closeAllCases(userID);
        int sagsId = (int) (Math.random() * 1000);
        try {
            Statement st = connection.createStatement();
            String sql = "INSERT INTO sager (sags_id,user_id,isopen,text) VALUES ('" + sagsId + "','" + userID + "','true','" + text + "');";
            rs = st.executeQuery(sql);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        createDiary(sagsId);
        createDiaryEntry(getDiaryId(sagsId), "");
    }

    public Long getCaseId(int userId) {
        Long id = null;
        try (Statement st = connection.createStatement()) {
            String sql = "SELECT sags_id FROM sager where user_id = " + userId;

            rs = st.executeQuery(sql);
            while (rs.next()) {
                id = rs.getLong("sags_id");

            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

    public void closeAllCases(int userID) {
        try {
            Statement st = connection.createStatement();
            String sql = "UPDATE sager SET isopen = 'false' WHERE user_id = '" + userID + "';";
            rs = st.executeQuery(sql);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public int findCaseID(int userID) {
        try {
            Statement st = connection.createStatement();
            String sql = "SELECT sags_id FROM sager WHERE user_id='" + userID + "' AND isopen='true';";
            rs = st.executeQuery(sql);
            rs.next();
            return rs.getInt(1);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return -1;
    }

    public boolean hasOpenCase(int userID) {
        try {
            Statement st = connection.createStatement();
            String sql = "SELECT user_id FROM sager WHERE user_id='" + userID + "' AND isopen='true';";
            rs = st.executeQuery(sql);
            rs.next();
            if (rs.getInt(1) == userID) {
                return true;
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    public void saveCase(int caseID, int userID, String text) {
        try {
            Statement st = connection.createStatement();
            String sql = "UPDATE sager SET text = '" + text + "' WHERE user_id ='" + userID + "' AND isopen ='true';";
            rs = st.executeQuery(sql);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void createUser(String username, String password, String userType) {
        try {
            Statement st = connection.createStatement();
            String sql = "INSERT INTO users "
                    + "(user_id,username,password,user_type)"
                    + " VALUES "
                    + "('" + username + "'" + ',' + "'" + password + "'" + ',' + "'" + userType + "')";
            st.execute(sql);
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void createUserInformation(String firstname, String middlename, String lastname,
            int cpr, String address, int postalcode, String city, String email) {
        try {
            Statement st = connection.createStatement();
            String sql = "INSERT INTO user_information "
                    + "(user_id,firstname,middlename,lastname,cpr,address,"
                    + "postal_code,city,email)"
                    + " VALUES (" + ",'" + firstname + "','" + middlename
                    + "','" + lastname + "','" + cpr + "','" + address + "'," + postalcode
                    + ",'" + city + "','" + email + "')";
            st.execute(sql);
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//Database should increment on its own by adding SERIAL to user ID
    public int getUserIDCount() {
        try (Statement st = connection.createStatement()) {
            String sql = "SELECT COUNT(*) AS user_id FROM Users";
            rs = st.executeQuery(sql);
            while (rs.next()) {
                count = rs.getInt("user_id");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

    public String[] getUserInformation() {
        try (Statement st = connection.createStatement()) {
            String sql = "SELECT * FROM USER_INFORMATION";

            rs = st.executeQuery(sql);
            while (rs.next()) {
                int userid = rs.getInt("user_id");
                String getFirstname = rs.getString("firstname");
                String getMiddlename = rs.getString("middlename");
                String getLastname = rs.getString("lastname");
                int getCPR = rs.getInt("cpr");
                String getAddress = rs.getString("address");
                int getPostalCode = rs.getInt("postal_code");
                String getCity = rs.getString("city");
                String getEmail = rs.getString("email");

                String[] info = {Integer.toString(userid), getFirstname, getMiddlename, getLastname, Integer.toString(getCPR), getAddress, Integer.toString(getPostalCode), getCity, getEmail};

                hasUserInformation = Integer.toString(userid) + "\t|" + getFirstname + "\t|" + getMiddlename
                        + "\t|" + getLastname + "\t|" + Integer.toString(getCPR)
                        + "\t|" + getAddress + "\t|" + Integer.toString(getPostalCode)
                        + "\t|" + getCity + "\t|" + getEmail;

                System.out.println(hasUserInformation);
                return info;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return null;
    }

    public void getUsers() {
        try (Statement st = connection.createStatement()) {
            String sql = "SELECT * FROM USERS";

            rs = st.executeQuery(sql);
            while (rs.next()) {
                int userid = rs.getInt("user_id");
                String getUsername = rs.getString("username");
                String getPassword = rs.getString("password");
                String getUsertype = rs.getString("user_type");

                hasUser = Integer.toString(userid) + "\t|" + getUsername + "\t|"
                        + getPassword + "\t" + getUsertype;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String[] getInformationStrings(long userId) {
        try (Statement st = connection.createStatement()) {
            String sql = "SELECT * FROM USER_INFORMATION WHERE user_id = " + userId;

            rs = st.executeQuery(sql);
            while (rs.next()) {
                String getFirstname = rs.getString("firstname");
                String getMiddlename = rs.getString("middlename");
                String getLastname = rs.getString("lastname");
                int getCPR = rs.getInt("cpr");
                String getAddress = rs.getString("address");
                int getPostalCode = rs.getInt("postal_code");
                String getCity = rs.getString("city");
                String getEmail = rs.getString("email");

                String[] info = {getFirstname, getMiddlename, getLastname, Integer.toString(getCPR), getAddress, Integer.toString(getPostalCode), getCity, getEmail};

                return info;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return null;
    }

    public int getUserId(String username) {
        int id = 0;
        try (Statement st = connection.createStatement()) {
            String sql = "SELECT user_id FROM users where username = " + username;

            rs = st.executeQuery(sql);
            while (rs.next()) {
                id = rs.getInt("user_id");

            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

    public Long createDiary(int sagsId) {
        try {
            Statement st = connection.createStatement();
            Long id = System.currentTimeMillis();
            String sql = "INSERT INTO diary "
                    + "(sags_id, diary_id)"
                    + " VALUES "
                    + "(" + sagsId + ',' + id + ")";
            st.execute(sql);
            st.close();
            return id;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void createDiaryEntry(long diaryID, String text) {
        try {
            Statement st = connection.createStatement();
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
            Logger.getLogger(DatabaseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void editDiaryEntry(long diaryEntryID, String text) {
        try {
            Statement st = connection.createStatement();
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
            Logger.getLogger(DatabaseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Long getDiaryId(long sagsId) {
        Long id = null;
        try (Statement st = connection.createStatement()) {
            String sql = "SELECT diary_id FROM diary where sags_id = " + sagsId;

            rs = st.executeQuery(sql);
            while (rs.next()) {
                id = rs.getLong("diary_id");

            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

    public List<String> getDiaryEntries(long diaryID) {
        List<String> entries = new ArrayList<String>();
        long timestamp = 0;

        try (Statement st = connection.createStatement()) {
            String sql = "SELECT entry_text, create_timestamp FROM diary_entry WHERE diary_id = " + diaryID + "order by create_timestamp DESC";

            rs = st.executeQuery(sql);
            while (rs.next()) {
                System.out.println("");
                entries.add(rs.getString("entry_text"));
                //timestamp = rs.getLong("create_timestamp");

            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return entries;
    }

    public String saveDiary(int userid, String text) {
        long caseid = getCaseId(userid);
        long diaryID = getDiaryId(caseid);

        createDiaryEntry(diaryID, text);

        return text;
    }

    public String editDiary(long diaryID, String text) {
        editDiaryEntry(diaryID, text);

        return text;
    }

    public int getCount(String tableName) {
        int cnt = 0;
        try (Statement st = connection.createStatement()) {
            String sql = "SELECT COUNT(*) AS cnt FROM " + tableName;

            rs = st.executeQuery(sql);
            while (rs.next()) {
                cnt = rs.getInt("cnt");

            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cnt;
    }

    public void deleteDiaryEntry(long diaryEntryId) {
        //long id = getDiaryId(getCaseId(diaryEntryId));
        //System.out.print(id);

        try (Statement st = connection.createStatement()) {
            String sql = "DELETE FROM public.diary_entry WHERE diary_entry_id = " + diaryEntryId;
            st.execute(sql);

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteUser(int userID) {
        try (Statement st = connection.createStatement()) {
            String sql = "DELETE FROM public.users WHERE user_id = " + userID;
            st.execute(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
