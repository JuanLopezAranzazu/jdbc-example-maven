package controller;

import factory.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserControllerBeta {
    public int updateUser(Integer userId,String firstName,String lastName,Boolean isActive) {
        /*Connection conn = new ConnectionFactory().getConnection();
        Statement s = null;
        int result;
        try {
            s = conn.createStatement();
            s.execute("UPDATE users SET first_name='"+firstName+"',last_name='"+lastName+"',is_active="+isActive+" WHERE user_id="+userId);
            result=s.getUpdateCount();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;*/
        Connection conn = new ConnectionFactory().getConnection();
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
        /*Connection conn = new ConnectionFactory().getConnection();
        Statement s = null;
        int result;
        try {
            s = conn.createStatement();
            s.execute("DELETE FROM users WHERE user_id="+userId);
            result=s.getUpdateCount();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;*/
        Connection conn = new ConnectionFactory().getConnection();
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

    public List<Map<String,String>> getUsers() {
        /*Connection conn = new ConnectionFactory().getConnection();
        Statement s = null;
        List<Map<String, String>> data;
        try {
            s = conn.createStatement();
            s.execute("SELECT * FROM users");
            ResultSet rs = s.getResultSet();
            data = new ArrayList<>();
            while (rs.next()) {
                Map<String, String> row = new HashMap<>();
                row.put("user_id", String.valueOf(rs.getInt("user_id")));
                row.put("first_name", rs.getString("first_name"));
                row.put("last_name", rs.getString("last_name"));
                row.put("is_active", String.valueOf(rs.getBoolean("is_active")));
                data.add(row);
            }
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return data;*/
        Connection conn = new ConnectionFactory().getConnection();
        PreparedStatement ps = null;
        List<Map<String, String>> data;
        try {
            ps = conn.prepareStatement("SELECT * FROM users");
            ps.execute();
            ResultSet rs = ps.getResultSet();
            data = new ArrayList<>();
            while (rs.next()) {
                Map<String, String> row = new HashMap<>();
                row.put("user_id", String.valueOf(rs.getInt("user_id")));
                row.put("first_name", rs.getString("first_name"));
                row.put("last_name", rs.getString("last_name"));
                row.put("is_active", String.valueOf(rs.getBoolean("is_active")));
                data.add(row);
            }
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return data;
    }

    public void createUser(Map<String,String> newUser) {
        /*Connection conn = new ConnectionFactory().getConnection();
        Statement s = null;
        try {
            s = conn.createStatement();
            s.execute("INSERT INTO users(first_name,last_name) " +
                    "VALUES('"+newUser.get("first_name")+"','"+newUser.get("last_name")+"')",Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = s.getGeneratedKeys();
            while(rs.next()){
                System.out.println(rs.getInt(1));
            }
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }*/
        Connection conn = new ConnectionFactory().getConnection();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("INSERT INTO users(first_name,last_name) VALUES(?,?)",PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1,newUser.get("first_name"));
            ps.setString(2,newUser.get("last_name"));
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

}
