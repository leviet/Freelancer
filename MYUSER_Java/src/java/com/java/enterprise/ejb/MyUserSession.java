/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.enterprise.ejb;

import com.java.enterprise.entity.MyUser;
import javax.ejb.Local;

/**
 *
 * @author MyPC
 */
@Local
public interface MyUserSession {
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
    public MyUser getUserId(String userId, String secAns);
    /**
     * Gen random password.
     * @return 
     */
    public String genNewPassword();
    
}
