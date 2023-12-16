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
import model.Attendance;
import model.Classroom;
import model.Slot;
import model.Subject;
import model.Term;

/**
 *
 * @author Pham Huong Ly
 */
public class ctimeController extends HttpServlet {
   
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
        //get list term
        Term term = new Term();
        ArrayList<Term> listTerm = term.getListTerm();
        request.setAttribute("listTerm", listTerm);
        
        //get term form client
        String optTerm = "1";
        if (request.getParameter("optTerm") != null) {
            optTerm = request.getParameter("optTerm");
        }

        request.setAttribute("optLastTerm", optTerm);
        
        String optClassroom = request.getParameter("optClassroom");
        request.setAttribute("optLastClassroom", optClassroom);
        
        //get list class by term
        Classroom exClass = new Classroom();
        ArrayList<Classroom> listClassroomBegin = exClass.getListClassroomByTerm(optTerm);
        request.setAttribute("listClassroomBegin", listClassroomBegin);
        
        request.getRequestDispatcher("ctime.jsp").forward(request, response);
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
        //get list term
        Term term = new Term();
        ArrayList<Term> listTerm = term.getListTerm();
        request.setAttribute("listTerm", listTerm);
        
        //get term form client
        String optTerm = request.getParameter("optLastTerm");
        
        //get class form client
        String optClassroom = request.getParameter("optLastClassroom");
        
        System.out.println("term " + optTerm);
        System.out.println("Classroom " + optClassroom);
        
        //get list class by term
        Classroom exClass = new Classroom();
        ArrayList<Classroom> listClassroomBegin = exClass.getListClassroomByTerm(optTerm);
        request.setAttribute("listClassroomBegin", listClassroomBegin);
        
        Subject subject = new Subject();
        ArrayList<Subject> listSubject = subject.getListSubjectByClass(optClassroom);
        request.setAttribute("listSubject", listSubject);
        
        //get studentId
        String studentId = "1";
        
        //get list slot from dtb by class and term
        Slot slot = new Slot();
        ArrayList<Slot> listSlotByClass = slot.getSlotByClass(optTerm, optClassroom, studentId);
        request.setAttribute("listSlotByClass", listSlotByClass);
        
        //get list attendance from dtb
        Attendance att = new Attendance();
        ArrayList<Attendance> listAttendance = att.getAttendanceBySID(optTerm, optClassroom, studentId);
        request.setAttribute("listAttendance", listAttendance);
        
        request.getRequestDispatcher("ctime.jsp").forward(request, response);
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
