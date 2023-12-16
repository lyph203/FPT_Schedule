/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Accounts;

/**
 *
 * @author Pham Huong Ly
 */
public class loginController extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        if(request.getParameter("logout") != null){
            HttpSession session = request.getSession();
            session.invalidate();
            request.getRequestDispatcher("loginFAP.jsp").forward(request, response);
        }
        
        if(request.getParameter("home") != null){
            //get account by username
            String aName = request.getParameter("aName");
            Accounts acc = new Accounts(aName);
            acc = acc.getAccByUsername();
            
            request.setAttribute("aName", aName);
            request.setAttribute("acc", acc);
            request.getRequestDispatcher("home.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("loginFAP.jsp").forward(request, response);
        }
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        //nhan thong tin tu client
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        // xu ly thong tin
        Accounts account = new Accounts(username, password);
        boolean check = account.checkLogin(); 
        if(check){
            //get account by username
            Accounts acc = account.getAccByUsername();
            
            //send info split by role
            if(acc.getRoleNum() == 2){
                HttpSession session = request.getSession();
                session.setAttribute("aName", acc.getUsername());
                request.setAttribute("aName", acc.getUsername());
                request.getRequestDispatcher("admin").forward(request, response);
            } else {
                HttpSession session = request.getSession();
                session.setAttribute("aName", acc.getUsername());
                request.setAttribute("aName", acc.getUsername());
                request.setAttribute("acc", acc);
                //login Success - > di chuyen den home.jsp
                request.getRequestDispatcher("home.jsp").forward(request, response);
            }
        } else {
            //login fail -> di chuyen ve home.jsp
            request.getRequestDispatcher("loginFAP.jsp").forward(request, response);
        }
        //tra ket qua ve client
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
