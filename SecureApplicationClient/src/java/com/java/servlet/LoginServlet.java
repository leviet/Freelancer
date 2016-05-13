/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.servlet;

import com.java.enterprise.ejb.EmployeeSession;
import com.java.enterprise.ejb.UserRoleSession;
import com.java.enterprise.entity.MyUser;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author vietlv2
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    private static final String USER_SESSION_KEY = "MyUser";
    private static final String ROLE_SESSION_KEY = "ROLE_SESSION";
    private InitialContext ctx;
    private EmployeeSession userSession;
    private UserRoleSession roleSession;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.setContentType("text/html;charset=UTF-8");
            userSession = (EmployeeSession) request.getSession().getAttribute(USER_SESSION_KEY);
            roleSession = (UserRoleSession) request.getSession().getAttribute(ROLE_SESSION_KEY);
            if (userSession == null) {
                ctx = new InitialContext();
                userSession = (EmployeeSession) ctx.lookup("java:global/SecureApplicationClient/EmployeeSessionBean!com.java.enterprise.ejb.EmployeeSessionBean");
                roleSession = (UserRoleSession) ctx.lookup("java:global/SecureApplicationClient/EmployeeSessionBean!com.java.enterprise.ejb.EmployeeSessionBean");
                request.getSession().setAttribute(USER_SESSION_KEY, userSession);
                request.getSession().setAttribute(ROLE_SESSION_KEY, roleSession);
                System.out.println("MyUser session");
            }
            String action = request.getParameter("action");
            if("login".equals(action)){
                login(request, response);
            }else if("logout".equals(action)){
                logOut(request, response);
            }else if("list".equals(action)){
                listUser(request, response);
            }else if("addEmployee".equals(action)){
                addEmployee(request, response);
            }else if("removeEmployee".equals(action)){
                removeEmployee(request, response);
            }else if("updateEmployee".equals(action)){
                updateEmployee(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void logOut(HttpServletRequest request, HttpServletResponse response){
        PrintWriter out = null;
        try {
            out = response.getWriter();
            HttpSession session = request.getSession();
            session.setAttribute("login", null);
            session.setAttribute("user", null);
            out.print("ok");
        } catch (IOException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            out.close();
        }
    }
    
    private void login(HttpServletRequest request, HttpServletResponse response){
        PrintWriter out = null;
        try {
            String userId = request.getParameter("userId");
            String password = request.getParameter("password");
            out = response.getWriter();
            HttpSession session = request.getSession();
            if(userSession.checkLogin(userId, password)){
                MyUser user = userSession.getUserId(userId);
                user.setRoles(roleSession.getRoleByUser(userId));
                session.setAttribute("login", "ok");
                session.setAttribute("user", user);
                out.print("ok");
            }else{
                session.setAttribute("login", "false");
                out.print("fail");
            }
        } catch (IOException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            out.close();
        }
    }
    
    private void removeEmployee(HttpServletRequest request, HttpServletResponse response){
        PrintWriter out = null;
        try {
            String userId = request.getParameter("userId");
            roleSession.removeByUser(userId);
            out = response.getWriter();
            out.print("ok");
        } catch (IOException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            out.close();
        }
        
    }
    
    private void addEmployee(HttpServletRequest request, HttpServletResponse response){
        PrintWriter out = null;
        try {
            String userId = request.getParameter("userId");
            String name = request.getParameter("name");
            String address = request.getParameter("address");
            String tel = request.getParameter("tel");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            MyUser user = new MyUser();
            user.setUserId(userId);
            user.setAddress(address);
            user.setEmail(email);
            user.setName(name);
            user.setPassword(password);
            user.setTel(tel);
            userSession.addMyUser(user);
            out = response.getWriter();
            out.print("ok");
        } catch (IOException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            out.close();
        }
        
    }

    private void listUser(HttpServletRequest request, HttpServletResponse response){
        try {
            PrintWriter out = response.getWriter();
            List<MyUser> employees = userSession.getAllUser();
                for(MyUser user:employees){
                  out.print("<tr class='odd gradeX'><td><input type='checkbox' class='checkboxes' value='1'/></td><td>");
                  out.print(user.getUserId());
                  out.print("</td><td><a href='mailto:");
                  out.print(user.getEmail());
                  out.print("'>");
                  out.print(user.getEmail());
                  out.print("</a></td><td>");
                  out.print(user.getName());
                  out.print("</td><td>");
                  out.print(user.getAddress());
                  out.print("</td><td>");
                  out.print(user.getTel());
                  out.print("</td><td><a href='#' onlick='editUser(");
                  out.print(user.getUserId());
                  out.print(")'> Edit </a>");
                  out.print("<a href='#' onlick='deleteUser(");
                  out.print(user.getUserId());
                  out.print(")'> Delete </a></td></tr>");
                }
        } catch (IOException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
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

    private void updateEmployee(HttpServletRequest request, HttpServletResponse response) {
        PrintWriter out = null;
        try {
            String userId = request.getParameter("userId");
            String name = request.getParameter("name");
            String address = request.getParameter("address");
            String tel = request.getParameter("tel");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            MyUser user = new MyUser();
            user.setUserId(userId);
            user.setAddress(address);
            user.setEmail(email);
            user.setName(name);
            user.setPassword(password);
            user.setTel(tel);
            userSession.updateUser(user);
            out = response.getWriter();
            out.print("ok");
        } catch (IOException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            out.close();
        }
    }

}
