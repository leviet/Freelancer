/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.enterprise.ejb;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author MyPC
 */
public class GmailServiceImplTest {
    
    public GmailServiceImplTest() {
    }
    
    

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
     @Test
     public void hello() {
         EmailService emailservice = new GmailServiceImpl();
         assertTrue(emailservice.sendEmail("adviet.com@gmail.com", "Tesst", "Tesst"));
     }
}
