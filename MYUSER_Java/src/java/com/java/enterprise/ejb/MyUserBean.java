/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.enterprise.ejb;

import com.java.enterprise.entity.MyUser;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
public class MyUserBean implements MyUserSession {
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
            stmt.setString(1, user.getPassword());
            stmt.setString(2, user.getUserId().trim());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MyUserBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MyUserBean.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                stmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(MyUserBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public MyUser getUserId(String userId, String secAns) {
        MyUser user = new MyUser();
        String statment = "SELECT USER_ID, SECANS, EMAIL FROM MYUSER WHERE "
                + " USER_ID = ?"
                + " AND SECANS = ?";
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            con = DriverManager.getConnection(host, uName, uPass);
            stmt = con.prepareStatement(statment);
            stmt.setString(1, userId);
            stmt.setString(2, secAns);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                user.setUserId(rs.getObject(1).toString().trim());
                user.setSecAns(rs.getObject(2).toString().trim());
                user.setEmail(rs.getObject(3).toString().trim());
            }
        } catch (SQLException ex) {
            Logger.getLogger(MyUserBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MyUserBean.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                stmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(MyUserBean.class.getName()).log(Level.SEVERE, null, ex);
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
}
