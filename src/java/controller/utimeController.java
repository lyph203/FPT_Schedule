/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.Classroom;
import model.Subject;
import model.Teacher;
import model.Term;

/**
 *
 * @author Pham Huong Ly
 */
public class utimeController extends HttpServlet {
   
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
        //lay danh sach term tu database
        Term term = new Term();
        ArrayList<Term> listTerm = term.getListTerm();
        request.setAttribute("listTerm", listTerm);

        //get term form client
        String optTerm = "1";
        if (request.getParameter("optTerm") != null && !request.getParameter("optTerm").trim().isEmpty()) {
            optTerm = request.getParameter("optTerm");
        }
        request.setAttribute("optLastTerm", optTerm);
        
        String optSub = "1";
        if (request.getParameter("optSub") != null) {
            optSub = request.getParameter("optSub");
        }
        request.setAttribute("optLastSub", optSub);
        
        System.out.println("term " + optTerm);
        System.out.println("Subject " + optSub);

        //lay danh sach subject tu database
        Subject sub = new Subject();
        ArrayList<Subject> listSubject = sub.getListSubjectByTerm(optTerm);
        request.setAttribute("listSubject", listSubject);
        
        request.getRequestDispatcher("utime.jsp").forward(request, response);
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
        try(PrintWriter out = response.getWriter()){
            //hien thi list term va subject
            //lay danh sach term tu database
            Term term = new Term();
            ArrayList<Term> listTerm = term.getListTerm();
            request.setAttribute("listTerm", listTerm);
            
            //nhan thong tin tu client
            //get term form client
            String optTerm = "1";
            if (request.getParameter("optLastTerm") != null) {
                optTerm = request.getParameter("optLastTerm");
            }

            //get subject form client
            String optSub = "1";
            if (request.getParameter("optLastSub") != null) {
                optSub = request.getParameter("optLastSub");
            }
            
            System.out.println("term " + optTerm);
            System.out.println("Subject " + optSub);
            
            //lay danh sach subject tu database
            Subject sub = new Subject();
            ArrayList<Subject> listSubject = sub.getListSubjectByTerm(optTerm);
            request.setAttribute("listSubject", listSubject);
            
            //lay danh sach class
            Classroom exClass = new Classroom();
            ArrayList<Classroom> listClassroom = exClass.getListClassroomBySub(optTerm, optSub);
            request.setAttribute("listClassroom", listClassroom);
            
            //lay danh sach teacher
            Teacher teacher = new Teacher();
            ArrayList<Teacher> listTeacher = teacher.getListTeacher();
            request.setAttribute("listTeacher", listTeacher);
            
            //get plan info of subject
            for (Subject subj : listSubject) {
                if(subj.getID() == Integer.parseInt(optSub)){
                    String plan = String.valueOf(subj.getPlan());
                    request.setAttribute("plan", plan);
                }
            }
            
            request.getRequestDispatcher("utime.jsp").forward(request, response);
        }
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
