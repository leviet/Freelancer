/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.java.enterprise.ejb.EmailService;
import com.java.enterprise.ejb.GmailServiceImpl;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author vietlv2
 */
public class NewEmptyJUnitTest {
    
    public NewEmptyJUnitTest() {
    }
    
    @Test
    public void test() {
        EmailService service = new GmailServiceImpl();
        assertTrue(service.sendEmail("leviet@vnexit.com", "Test", "Subject"));
    }
    
}
