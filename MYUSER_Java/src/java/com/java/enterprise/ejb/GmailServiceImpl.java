/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.enterprise.ejb;

import java.util.Properties;
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
    private Session session;
    
    public GmailServiceImpl() {
        mailServerProperties = new Properties();
        mailServerProperties.put("mail.smtp.host", "smtp.gmail.com");
        mailServerProperties.put("mail.smtp.socketFactory.port", "465");
        mailServerProperties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        mailServerProperties.put("mail.smtp.auth", "true");
        mailServerProperties.put("mail.smtp.port", "465");
        session = Session.getDefaultInstance(mailServerProperties,
        new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(USER_SENDER,PASSWORD_SENDER);
                }
        });
    }
    
    @Override
    public boolean sendEmail(String reciver, String content, String subject) {
        try {
            Message message = new MimeMessage(session);
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
