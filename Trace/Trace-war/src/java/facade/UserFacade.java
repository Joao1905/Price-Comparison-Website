package facade;
import modelo.User;
import dao.UserDao;
import java.io.Serializable;
import java.util.List;

public class UserFacade implements Serializable {
    
    public UserFacade() {
        
    }
    
    public static void insertUser (User userobj) {
        UserDao.insertUser(userobj);
    }
    
    
    public static void deleteUserByID (int user_id) {
        UserDao.deleteUserByID(user_id);
    }
     
    public static void deleteUserByLogin (String user_login) {
        UserDao.deleteUserByLogin(user_login);
    }
     
     
    public static User selectUserByID (int user_id) {
        return UserDao.selectUserByID(user_id);
    }
      
    public static User selectUserByLogin (String user_login) {
        return UserDao.selectUserByLogin(user_login);
    }
    
    public static User selectUserByEmail (String user_email) {
        return UserDao.selectUserByEmail(user_email);
    }
         
      
    public static void updateUserColumnByID (int user_id, User userobj, String column) {
        UserDao.updateUserColumnByID (user_id, userobj, column);
    }
       
    public static void updateUserColumnByLogin (String user_login, User userobj, String column) {
        UserDao.updateUserColumnByLogin (user_login, userobj, column);
    }
       
       
    public static List<User> selectAll () {
           return UserDao.selectAll();
     }
}