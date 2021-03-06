package com.java.servlet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.java.enterprise.ejb.MyUserSession;
import com.java.enterprise.entity.MyUser;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author MyPC
 */
public class MyUserServlet extends HttpServlet {
    private static final String USER_SESSION_KEY = "MyUser";
    private InitialContext ctx;
    @Resource(name = "mail/NewGmailMessageSession")
    private Session mailSession;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Hello from servlet");
        MyUserSession userSession = (MyUserSession) request.getSession().getAttribute(USER_SESSION_KEY);
        if (userSession == null) {
            try {
                ctx = new InitialContext();
                userSession = (MyUserSession) ctx.lookup("java:global/MYUSER_ClientApplication/MyUserBean!com.java.enterprise.ejb.MyUserBean");
                request.getSession().setAttribute(USER_SESSION_KEY, userSession);
                System.out.println("MyUser created");
            } catch (NamingException e) {
                throw new ServletException(e);
            }
        }
        String userId = request.getParameter("userId");
        String secAns = request.getParameter("secAns");
        MyUser user = userSession.getUserId(userId, secAns);
        if(user!=null && (user.getUserId() != null)){
            try {
                String newPassword = userSession.genNewPassword();
                user.setPassword(newPassword);
                userSession.updateUser(user);
                sendEmailResetPassword(user);
                response.setContentType("text/html;charset=UTF-8");
                try (PrintWriter out = response.getWriter()) {
                    /* TODO output your page here. You may use following sample code. */
                    out.println("Your password have been reset. Please check your email: " + user.getEmail() + " to get new password.");
                }
            }   catch (NamingException ex) {
                Logger.getLogger(MyUserServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (JMSException ex) {
                Logger.getLogger(MyUserServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            try (PrintWriter out = response.getWriter()) {
                /* TODO output your page here. You may use following sample code. */
                out.println("Do not found any user");
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void sendEmailResetPassword(MyUser user) throws NamingException, JMSException{
        try {
            Message message = new MimeMessage(mailSession);
            message.setFrom(new InternetAddress("adviet.com@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(user.getEmail()));
            message.setSubject("Reset password");
            message.setText("Hi, You just order change your password for UserId: " + user.getUserId() + "\nYour new password is: " + user.getPassword());
            Transport.send(message);
        } catch (MessagingException ex) {
            Logger.getLogger(MyUserServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
