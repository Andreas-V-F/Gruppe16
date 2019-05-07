package sensumboosted.Domain;

public class UserAccount {

    int userid;
    String username;
    String password;
    String usertype;

    public UserAccount(int userid, String username, String password, String usertype) {
        this.userid = userid;
        this.username = username;
        this.password = password;
        this.usertype = usertype;
    }

    public UserAccount(int userid, String username) {
        this.userid = userid;
        this.username = username;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

}
