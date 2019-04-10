package com.assignmentjavaweb.model;

import com.assignmentjavaweb.data.DataConnectionHelper;
import com.assignmentjavaweb.entity.User;
import com.assignmentjavaweb.security.Security;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class UserModels {
    DataConnectionHelper data = new DataConnectionHelper();
    Security security = new Security();

    public ArrayList<User> getListUser(){
        ArrayList<User> listUsers = new ArrayList<>();
        Connection connection = data.getConnecttion();
        if (connection == null){
            return null;
        }
        try {
            Statement stmt = connection.createStatement();
            String sql = "SELECT * FROM users";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getInt("role"));
                user.setStatus(rs.getInt("status"));
                listUsers.add(user);
            }
        } catch (SQLException e) {
            security.writeLog("Sorry! Can't connect to database. Please try again!");
            System.out.println(new Date() + " - LOG : Sorry! Can't connect to database. Please try again!");
            e.printStackTrace();
        }
        return listUsers;
    }

    public User getUserByUserName(String username){
        User user = new User();

        Connection connection = data.getConnecttion();
        if (connection == null){
            return null;
        }
        try {
            Statement stmt = connection.createStatement();
            String sql = "SELECT * FROM users WHERE username = '" + username + "'";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setSalt(rs.getString("salt"));
                user.setRole(rs.getInt("role"));
                user.setStatus(rs.getInt("status"));
            }
        } catch (SQLException e) {
            security.writeLog("Sorry! Can't connect to database. Please try again!");
            System.out.println(new Date() + " - LOG : Sorry! Can't connect to database. Please try again!");
            e.printStackTrace();
        }
        return user;
    }

    public User getUserbyId(int id){
        User user = new User();
        Connection connection = data.getConnecttion();
        if (connection ==  null){
            return null;
        }
        try {
            Statement stmt = connection.createStatement();
            String sql = "SELECT * FROM users WHERE id = '" + id + "'";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setSalt(rs.getString("salt"));
                user.setRole(rs.getInt("role"));
                user.setStatus(rs.getInt("status"));
            }
        } catch (SQLException e) {
            security.writeLog("Sorry! Can't connect to database. Please try again!");
            System.out.println(new Date() + " - LOG : Sorry! Can't connect to database. Please try again!");
            e.printStackTrace();
        }
        return user;
    }

    public User updateUser(int id, User user){
        Connection connection = data.getConnecttion();
        if (connection == null){
            return null;
        }
        if (id != user.getId()){
            System.out.println(new Date() + " - LOG : USER not found!!");
            return null;
        }
        String sql = "UPDATE users SET username = ?, password = ?, role = ?, status = ? WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setInt(3, user.getRole());
            ps.setInt(4, user.getStatus());
            ps.executeUpdate();
            security.writeLog("Update " + user.getUsername() +" success!");
            System.out.println(new Date() + " - LOG : Update success!");
        } catch (SQLException e) {
            security.writeLog("Sorry! Can't connect to database. Please try again!");
            System.out.println(new Date() + " - LOG : Sorry! Can't connect to database. Please try again!");
            e.printStackTrace();
        }
        user = getUserbyId(id);
        return user;
    }

    public void deleteStudent(int id){
        Connection connection = data.getConnecttion();
        if (connection == null){
            security.writeLog("Sorry! Can't connect to database. Please try again!");
            System.out.println("LOG : Sorry! Can't connect to database. Please try again!");
        }
        if (checkUserExist(id, connection)){
            String sql = "DETETE FROM users WHERE id = ?";
            try {
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setInt(1, id);
                ps.executeUpdate();
                System.out.println(new Date() + " - LOG : Delete user has +" + id + " success!");
            } catch (SQLException e) {
                System.out.println(new Date() + " - LOG : Can't delete user has +" + id );
                e.printStackTrace();
            }
        }
    }

    public User addUser(String username, String password, int role, int status){
        Connection connection = data.getConnecttion();
        if (connection == null){
            return null;
        }
        String salt = security.eandomString();
        User user = new User();
        user.setUsername(username);
        user.setPassword(security.endcodeMd5(password) + security.endcodeMd5(salt));
        user.setSalt(security.endcodeMd5(salt));
        user.setRole(role);
        user.setStatus(status);

        String sql = "INSERT INTO users(username, password, salt, role, status) VALUE (?,?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getSalt());
            ps.setInt(4, user.getRole());
            ps.setInt(5, user.getStatus());
            ps.executeUpdate();
            security.writeLog("Add user " + user.getUsername() + " success!");
            System.out.println(new Date() + " - LOG : Add user " + user.getUsername() + " success!");
        } catch (SQLException e) {
            System.out.println(new Date() + " - LOG : Sorry! Can't connect to database. Please try again!");
            e.printStackTrace();
        }
        return user;
    }

    private boolean checkUserExist(int id, Connection connection){
        if (connection == null){
            return false;
        }
        try {
            Statement stmt = connection.createStatement();
            String sql = "SELECT * FROM users WHERE id = '" + id + "'";
            ResultSet rs = stmt.executeQuery(sql);
            return (rs.first());
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public User check(String username, String password){
        Security security = new Security();
        User user = getUserByUserName(username);
        String newPassword = security.endcodeMd5(password) + user.getSalt();
        if (newPassword.equals(user.getPassword())){
            return user;
        }
        return null;
    }
}
