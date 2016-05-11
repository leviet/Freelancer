/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.enterprise.ejb;

import java.util.Properties;
import javax.annotation.Resource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author vietlv2
 */
public class GmailServiceImpl implements EmailService{
    private Properties mailServerProperties;
    private static final String USER_SENDER = "adviet.com@gmail.com";
    private static final String PASSWORD_SENDER = "levanviet";
    @Resource(name = "mail/NewGmailMessageSession")
    private Session mailSession;
    
    public GmailServiceImpl() {
    }
    
    @Override
    public boolean sendEmail(String reciver, String content, String subject) {
        try {
            Message message = new MimeMessage(mailSession);
            message.setFrom(new InternetAddress(USER_SENDER));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(reciver));
            message.setSubject(subject);
            message.setText(content);
            Transport.send(message);
            return true;
        } catch (MessagingException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
