package modelo;
import java.io.Serializable;

public class User implements Serializable {

    private int user_id;
    private String login;
    private String email;
    private String passwd;
    private String last_login;
    
    public User() {
    }
    
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
    
    public int getAccess() {
        return access;
    }

    public void setAccess(int access) {
        this.access = access;
    }
    private int access;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getLast_login() {
        return last_login;
    }

    public void setLast_login(String last_login) {
        this.last_login = last_login;
    }
    
}
