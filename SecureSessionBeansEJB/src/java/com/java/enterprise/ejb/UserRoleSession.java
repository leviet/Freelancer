/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.enterprise.ejb;

import com.java.enterprise.entity.Role;
import java.util.List;

/**
 *
 * @author MyPC
 */
public interface UserRoleSession {
    /**
     * List all role of user.
     * @param userId
     * @return 
     */
    public List<Role> getRoleByUser(String userId);
    /**
     * Remove all role of user.
     * @param userId 
     */
    public void removeByUser(String userId);
}
