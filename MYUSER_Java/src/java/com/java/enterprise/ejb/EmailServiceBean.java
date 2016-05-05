/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.enterprise.ejb;

import com.java.enterprise.entity.MyUser;
import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

/**
 *
 * @author vietlv2
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "EmailMessageHandler"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class EmailServiceBean implements MessageListener {
    @Resource
    private MessageDrivenContext mdctx;  
    public EmailServiceBean() {
    }
    
    @Override
    public void onMessage(Message message) {
      ObjectMessage objectMessage = null;
      try {
         objectMessage = (ObjectMessage) message;
         MyUser user = (MyUser) objectMessage.getObject();
         //Send email.
         if(user != null && user.getUserId()!=null){
             String content = "Your new password is: " + user.getPassword();
             sendEmail(user.getEmail(), content);
         }
      } catch (JMSException ex) {
         mdctx.setRollbackOnly();
      }       
    }
    /**
     * Send email notify.
     * @param reciver
     * @param content 
     */
    private void sendEmail(String reciver, String content){
        String subject = "Reset password";
        EmailService emailservice = new GmailServiceImpl();
        if(emailservice.sendEmail(reciver, content, subject)){
            System.out.println("Send email success to: " + reciver);
        }else{
            System.out.println("Send email fail to: " + reciver);
        }
    }
}
