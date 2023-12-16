/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.Attendance;
import model.Classroom;
import model.Slot;
import model.Student;
import model.Teacher;

/**
 *
 * @author Pham Huong Ly
 */
public class attendanceController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
        System.out.println("get");
        Classroom exClass = new Classroom();
        //get list classroom by teacherId
        String aId = request.getParameter("aId");
        request.setAttribute("aId", aId);
        System.out.println("aid" + aId);

        if (request.getParameter("mod1") != null) {
            System.out.println("inmod1");
            //get list Slot by class Id
            String cId = request.getParameter("cId");

            Slot slot = new Slot();
            ArrayList<Slot> listSlot = slot.getListSlotByClassId(cId);
            request.setAttribute("listSlot", listSlot);
            request.setAttribute("cId", cId);

            request.getRequestDispatcher("viewAtt.jsp").forward(request, response);
        }

        if (request.getParameter("mod2") != null) {
            System.out.println("inmod2");
            //get list Slot by class Id
            String cId = request.getParameter("cId");

            Slot slot = new Slot();
            ArrayList<Slot> listSlot = slot.getListSlotByClassId(cId);
            request.setAttribute("listSlot", listSlot);
            request.setAttribute("cId", cId);

            request.getRequestDispatcher("editAtt.jsp").forward(request, response);
        }
        
        String teacherId = "";
        String teachCode = "";
        Teacher teac = new Teacher();
        ArrayList<Teacher> listTeacher = teac.getListTeacher();

        try {
            for (Teacher teacher : listTeacher) {
                if (teacher.getAccountID() == Integer.parseInt(aId)) {
                    teacherId = String.valueOf(teacher.getTeacherID());
                    teachCode = teacher.getCode();
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("NumberFormatException" + e.getMessage());
        }

        request.setAttribute("teachCode", teachCode);

        ArrayList<Classroom> listClassroom = exClass.getListClassroomByTeach(teacherId);
        request.setAttribute("listClassroom", listClassroom);

        request.getRequestDispatcher("takeAtt.jsp").forward(request, response);
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
        System.out.println("post");
        //get list classroom by teacherId
        String aId = request.getParameter("aId");
        request.setAttribute("aId", aId);
        System.out.println("aid" + aId);
        
        Student stu = new Student();
        Attendance att = new Attendance();

        //get parameter from client
        String slotId = request.getParameter("slotId");
        System.out.println(slotId);
        String cId = request.getParameter("cId");
        System.out.println(cId);
        request.setAttribute("cId", cId);

        //get list Slot by class Id
        Slot slot = new Slot();
        ArrayList<Slot> listSlot = slot.getListSlotByClassId(cId);
        request.setAttribute("listSlot", listSlot);

        //get list student by class id
        ArrayList<Student> listStudent = stu.getListStudentbyClass(cId);
        request.setAttribute("listStudent", listStudent);
        
        //get list Attendance
        ArrayList<Attendance> listAttendance = att.getListAttendance(cId, slotId);
        request.setAttribute("listAttendance", listAttendance);
        if (request.getParameter("search") != null) {
            System.out.println("searchin");
            if (request.getParameter("mod1") != null) {
                System.out.println("viewin");
                request.getRequestDispatcher("viewAtt.jsp").forward(request, response);
            } else {
                System.out.println("editin");
                request.getRequestDispatcher("editAtt.jsp").forward(request, response);
            }
        }

        if (request.getParameter("edit") != null) {
            System.out.println("inedit");
            for (Attendance attendance : listAttendance) {
                if(!request.getParameter(String.valueOf(attendance.getStudentID())).equals(attendance.getisAbsent()) || !request.getParameter(attendance.getStudentID()+"comment").equals(attendance.getComment())){
                    String comment = request.getParameter(attendance.getStudentID()+"comment");
                    System.out.println(comment);
                    String attend = request.getParameter(String.valueOf(attendance.getStudentID()));
                    
                    attendance.editAttendance(attendance.getStudentID(), slotId, attend, comment);
                }
            }
            doGet(request, response);
        }
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

}
