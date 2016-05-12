/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.enterprise.ejb;

import com.java.enterprise.entity.MyUser;
import com.java.enterprise.entity.Role;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateful;
import javax.ejb.LocalBean;
import javax.ejb.StatefulTimeout;

/**
 *
 * @author MyPC
 */
@Stateful
@LocalBean
@StatefulTimeout(unit = TimeUnit.MINUTES, value = 20)
public class EmployeeSessionBean implements EmployeeSession, UserRoleSession{
String host = "jdbc:derby://localhost:1527/Week1";
    String uName = null, uPass = null;
    Connection con = null;
    PreparedStatement stmt = null;
    
    @Override
    public void updateUser(MyUser user) {
        String statment = "UPDATE MYUSER "
                + " SET PASSWORD = ? "
                + " WHERE USER_ID = ?";
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            con = DriverManager.getConnection(host, uName, uPass);
            stmt = con.prepareStatement(statment);
//            stmt.setString(1, user.getPassword());
            stmt.setString(2, user.getUserId().trim());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeSessionBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EmployeeSessionBean.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                stmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(EmployeeSessionBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public MyUser getUserId(String userId) {
        MyUser user = new MyUser();
        String statment = "SELECT USER_ID, NAME, EMAIL, ADDRESS, TEL FROM MYUSER WHERE "
                + " USER_ID = ?";
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            con = DriverManager.getConnection(host, uName, uPass);
            stmt = con.prepareStatement(statment);
            stmt.setString(1, userId);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                user.setUserId(rs.getObject(1).toString().trim());
                user.setName(rs.getObject(2).toString().trim());
                user.setEmail(rs.getObject(3).toString().trim());
                user.setAddress(rs.getObject(4).toString().trim());
                user.setTel(rs.getObject(5).toString().trim());
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeSessionBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EmployeeSessionBean.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                stmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(EmployeeSessionBean.class.getName()).log(Level.SEVERE, null, ex);
            }
            return user;
        }
    }

    @Override
    public String genNewPassword() {
        String subset = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMOPQRSTUVWXYZ";

        Random r = new Random();
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < 6; i++) {
            int index = r.nextInt(subset.length());
            char c = subset.charAt(index);
            sb.append(c);
        }
        return sb.toString();
    }

    @Override
    public List<Role> getRoleByUser(String userId) {
        List<Role> roles = new LinkedList<Role>();
        String statment = "SELECT ROLE_ID, NAME, CODE FROM ROLE "
                + " WHERE USER_ID = ?";
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            con = DriverManager.getConnection(host, uName, uPass);
            stmt = con.prepareStatement(statment);
            stmt.setString(1, userId);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Role role = new Role();
                role.setRoleId(rs.getObject(1).toString().trim());
                role.setName(rs.getObject(2).toString().trim());
                role.setCode(rs.getObject(3).toString().trim());
                roles.add(role);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeSessionBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EmployeeSessionBean.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                stmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(EmployeeSessionBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return roles;
    }

    @Override
    public void addMyUser(MyUser user) {
        String statment = "INSERT INTO MYUSER (USER_ID, NAME, TEL, EMAIL, ADDRESS, PASSWORD)"
                + " VALUES (?, ?, ?, ?, ?, ?)";
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            con = DriverManager.getConnection(host, uName, uPass);
            stmt = con.prepareStatement(statment);
            stmt.setString(1, user.getUserId());
            stmt.setString(2, user.getName());
            stmt.setString(3, user.getTel());
            stmt.setString(4, user.getEmail());
            stmt.setString(5, user.getAddress());
            stmt.setString(6, user.getPassword());
            stmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeSessionBean.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EmployeeSessionBean.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                stmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(EmployeeSessionBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void removeMyUser(MyUser user) {
        String statment = "DELETE FROM MYUSER WHERE USER_ID = ?";
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            con = DriverManager.getConnection(host, uName, uPass);
            stmt = con.prepareStatement(statment);
            stmt.setString(1, user.getUserId());
            stmt.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeSessionBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EmployeeSessionBean.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                stmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(EmployeeSessionBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void removeByUser(String userId) {
        String statment = "DELETE FROM USER_ROLE WHERE USER_ID = ?";
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            con = DriverManager.getConnection(host, uName, uPass);
            stmt = con.prepareStatement(statment);
            stmt.setString(1, userId);
            stmt.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeSessionBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EmployeeSessionBean.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                stmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(EmployeeSessionBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public List<MyUser> getAllUser() {
        List<MyUser> employees = new LinkedList<MyUser>();
        String statment = "SELECT USER_ID, NAME, EMAIL, ADDRESS, TEL FROM MYUSER ";
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            con = DriverManager.getConnection(host, uName, uPass);
            stmt = con.prepareStatement(statment);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                MyUser user = new MyUser();
                user.setUserId(rs.getObject(1).toString().trim());
                user.setName(rs.getObject(2).toString().trim());
                user.setEmail(rs.getObject(3).toString().trim());
                user.setAddress(rs.getObject(4).toString().trim());
                user.setTel(rs.getObject(5).toString().trim());
                employees.add(user);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeSessionBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EmployeeSessionBean.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                stmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(EmployeeSessionBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return employees;
    }
}
