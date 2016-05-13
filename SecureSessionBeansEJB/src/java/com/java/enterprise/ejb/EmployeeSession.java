/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.enterprise.ejb;

import com.java.enterprise.entity.MyUser;
import java.util.List;

/**
 *
 * @author MyPC
 */
public interface EmployeeSession {
    /**
     * Update Myuser.
     * @param user 
     */
    public void updateUser(MyUser user);
    /**
     * Get user by userId.
     * @param userId
     * @param secAns
     * @return 
     */
    public MyUser getUserId(String userId);
    /**
     * Gen random password.
     * @return 
     */
    public String genNewPassword();
    /**
     * Add employee.
     * @param user 
     */
    public void addMyUser(MyUser user);
    /**
     * Remove an employee.
     * @param user 
     */
    public void removeMyUser(MyUser user);
    /**
     * List all employee.
     * @return 
     */
    public List<MyUser> getAllUser();
    /**
     * Check login.
     * @param userId
     * @param password
     * @return 
     */
    public boolean checkLogin(String userId, String password);
}
