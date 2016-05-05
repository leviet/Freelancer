/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.enterprise.ejb;

/**
 *
 * @author vietlv2
 */
public interface EmailService {
    /**
     * Send email.
     * @param reciver
     * @param content
     * @return 
     */
    public boolean sendEmail(String reciver, String content, String subject);
}
