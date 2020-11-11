package dao;
import modelo.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

public class UserDao implements Serializable{
    
    public UserDao() {
        
    }
        
    public static void insertUser (User userobj) {
        Connection con = null;
        PreparedStatement st = null;
        
        try {
            con = ConnectionFactory.getConnection();
            st = con.prepareStatement("insert into user (login, email, passwd) values (?, ?, ?)");
            st.setString(1, userobj.getLogin());
            st.setString(2, userobj.getEmail());
            st.setString(3, userobj.getPasswd());
            st.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException (e);
        } finally {
            if (st != null)
                try {st.close();} catch (Exception e){}
            if (con!=null)
                try {con.close();} catch (Exception e){}
        }
    }
    
    
     public static void deleteUserByID (int user_id) {
        Connection con = null;
        PreparedStatement st = null;
        
        try {
            con = ConnectionFactory.getConnection();
            st = con.prepareStatement("DELETE FROM user WHERE user_id=?");
            st.setInt(1, user_id);
            st.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException (e);
        } finally {
            if (st != null)
                try {st.close();} catch (Exception e){}
            if (con!=null)
                try {con.close();} catch (Exception e){}
        }
    }
     
     public static void deleteUserByLogin (String user_login) {
        Connection con = null;
        PreparedStatement st = null;
        
        try {
            con = ConnectionFactory.getConnection();
            st = con.prepareStatement("DELETE FROM user WHERE login=?");
            st.setString(1, user_login);
            st.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException (e);
        } finally {
            if (st != null)
                try {st.close();} catch (Exception e){}
            if (con!=null)
                try {con.close();} catch (Exception e){}
        }
    }
     
     
     public static User  selectUserByID (int user_id) {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        
        try {
            con = ConnectionFactory.getConnection();
            st = con.prepareStatement("SELECT * FROM user WHERE user_id=?");
            st.setInt(1, user_id);
            rs =  st.executeQuery();
            if (rs.next()) {
                User u = new User();
                u.setUser_id( rs.getInt("user_id"));
                u.setLogin( rs.getString("login"));
                u.setPasswd( rs.getString("passwd"));
                u.setEmail( rs.getString("email"));
                u.setLast_login( rs.getString("last_login"));
                return u;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new RuntimeException (e);
        } finally {
            if (st != null)
                try {st.close();} catch (Exception e){}
            if (con!=null)
                try {con.close();} catch (Exception e){}
            if (rs!=null)
                try {rs.close();} catch (Exception e){}
        }
    }
      
     public static User  selectUserByLogin (String user_login) {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        
        try {
            con = ConnectionFactory.getConnection();
            st = con.prepareStatement("SELECT * FROM user WHERE login=?");
            st.setString(1, user_login);
            rs =  st.executeQuery();
            if (rs.next()) {
                User u = new User();
                u.setUser_id( rs.getInt("user_id"));
                u.setLogin( rs.getString("login"));
                u.setPasswd( rs.getString("passwd"));
                u.setEmail( rs.getString("email"));
                u.setLast_login( rs.getString("last_login"));
                return u;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new RuntimeException (e);
        } finally {
            if (st != null)
                try {st.close();} catch (Exception e){}
            if (con!=null)
                try {con.close();} catch (Exception e){}
            if (rs!=null)
                try {rs.close();} catch (Exception e){}
        }
    }
    
     public static User  selectUserByEmail (String user_email) {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        
        try {
            con = ConnectionFactory.getConnection();
            st = con.prepareStatement("SELECT * FROM user WHERE email=?");
            st.setString(1, user_email);
            rs =  st.executeQuery();
            if (rs.next()) {
                User u = new User();
                u.setUser_id( rs.getInt("user_id"));
                u.setLogin( rs.getString("login"));
                u.setPasswd( rs.getString("passwd"));
                u.setEmail( rs.getString("email"));
                u.setLast_login( rs.getString("last_login"));
                return u;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new RuntimeException (e);
        } finally {
            if (st != null)
                try {st.close();} catch (Exception e){}
            if (con!=null)
                try {con.close();} catch (Exception e){}
            if (rs!=null)
                try {rs.close();} catch (Exception e){}
        }
    }
     
     
     public static void updateUserColumnByID (int user_id, User userobj, String column) {
        Connection con = null;
        PreparedStatement st = null;
        
        try {
            con = ConnectionFactory.getConnection();
            switch (column) {
                case "login":
                    st = con.prepareStatement("UPDATE user SET login=? WHERE user_id=?");
                    st.setString(1, userobj.getLogin());
                    st.setInt(2, user_id);
                    st.executeUpdate();
                    break;
                case "passwd":
                    st = con.prepareStatement("UPDATE user SET passwd=? WHERE user_id=?");
                    st.setString(1, userobj.getPasswd());
                    st.setInt(2, user_id);
                    st.executeUpdate();
                    break;
                case "email":
                    st = con.prepareStatement("UPDATE user SET email=? WHERE user_id=?");
                    st.setString(1, userobj.getEmail());
                    st.setInt(2, user_id);
                    st.executeUpdate();
                    break;
                 case "last_login":
                    st = con.prepareStatement("UPDATE user SET last_login=now() WHERE user_id=?");
                    st.setInt(1, user_id);
                    st.executeUpdate();
                    break;
                default:
                    throw new IllegalArgumentException("Invalid column: " + column);
            }
        } catch (Exception e) {
            throw new RuntimeException (e);
        } finally {
            if (st != null)
                try {st.close();} catch (Exception e){}
            if (con!=null)
                try {con.close();} catch (Exception e){}
        }
    }
     
     public static void updateUserColumnByLogin (String user_login, User userobj, String column) {
        Connection con = null;
        PreparedStatement st = null;
        
        try {
            con = ConnectionFactory.getConnection();
            switch (column) {
                case "login":
                    st = con.prepareStatement("UPDATE user SET login=? WHERE login=?");
                    st.setString(1, userobj.getLogin());
                    st.setString(2, user_login);
                    st.executeUpdate();
                    break;
                case "passwd":
                    st = con.prepareStatement("UPDATE user SET passwd=? WHERE login=?");
                    st.setString(1, userobj.getPasswd());
                    st.setString(2, user_login);
                    st.executeUpdate();
                    break;
                case "email":
                    st = con.prepareStatement("UPDATE user SET email=? WHERE login=?");
                    st.setString(1, userobj.getEmail());
                    st.setString(2, user_login);
                    st.executeUpdate();
                    break;
                case "last_login":
                    st = con.prepareStatement("UPDATE user SET last_login=now() WHERE login=?");
                    st.setString(1, user_login);
                    st.executeUpdate();
                    break;
                default:
                    throw new IllegalArgumentException("Invalid column: " + column);
            }
        } catch (Exception e) {
            throw new RuntimeException (e);
        } finally {
            if (st != null)
                try {st.close();} catch (Exception e){}
            if (con!=null)
                try {con.close();} catch (Exception e){}
        }
    }

     
     public static List<User> selectAll () {
            List<User> user = new ArrayList<>();
            Connection con = null;
            PreparedStatement st = null;
            ResultSet rs = null;

            try {
                con = ConnectionFactory.getConnection();
                st = con.prepareStatement("SELECT * FROM user");
                rs = st.executeQuery();
                while (rs.next()) {
                    User user2 = new User();
                    user2.setUser_id( rs.getInt("user_id"));
                    user2.setLogin( rs.getString("login"));
                    user2.setPasswd( rs.getString("passwd"));
                    user2.setEmail( rs.getString("email"));
                    user2.setLast_login( rs.getString("last_login"));
                    user.add(user2);
                }
                return user;
            } catch (Exception e) {
                throw new RuntimeException (e);
            } finally {
                if (st != null)
                    try {st.close();} catch (Exception e){}
                if (con!=null)
                    try {con.close();} catch (Exception e){}
                if (rs!=null)
                    try {rs.close();} catch (Exception e){}
            }
     }
}