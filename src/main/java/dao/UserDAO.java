package dao;

import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    private Connection conn;

    public UserDAO(Connection conn){
        this.conn=conn;
    }

    public void createUser(User newUser) {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("INSERT INTO users(first_name,last_name) VALUES(?,?)",PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1,newUser.getFirstName());
            ps.setString(2,newUser.getLastName());
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            while(rs.next()){
                System.out.println(rs.getInt(1));
            }
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getUsers() {
        PreparedStatement ps = null;
        List<User> data;
        try {
            ps = conn.prepareStatement("SELECT * FROM users");
            ps.execute();
            ResultSet rs = ps.getResultSet();
            data = new ArrayList<>();
            while (rs.next()) {
                User user=new User();
                user.setUserId(rs.getInt("user_id"));
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                user.setActive(rs.getBoolean("is_active"));
                data.add(user);
            }
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return data;
    }

    public int updateUser(Integer userId,String firstName,String lastName,Boolean isActive) {
        PreparedStatement ps = null;
        int result;
        try {
            ps = conn.prepareStatement("UPDATE users SET first_name=?,last_name=?,is_active=? WHERE user_id=?");
            ps.setString(1,firstName);
            ps.setString(2,lastName);
            ps.setBoolean(3,isActive);
            ps.setInt(4,userId);
            ps.execute();
            result=ps.getUpdateCount();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public int deleteUser(Integer userId) {
        PreparedStatement ps = null;
        int result;
        try {
            ps = conn.prepareStatement("DELETE FROM users WHERE user_id=?");
            ps.setInt(1,userId);
            ps.execute();
            result=ps.getUpdateCount();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
