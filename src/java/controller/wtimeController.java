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
import java.time.LocalDate;
import java.util.ArrayList;
import model.Accounts;
import model.Attendance;
import model.Slot;
import model.Student;
import model.Teacher;

/**
 *
 * @author Pham Huong Ly
 */
public class wtimeController extends HttpServlet {

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
        //get list year
        //lay danh sach year tu database
        Slot slot = new Slot();
        ArrayList<String> listYear = slot.getListYear();
        request.setAttribute("listYear", listYear);

        //get week, year from client
        String nowWeek = LocalDate.now().toString();
        int year = LocalDate.now().getYear();

        nowWeek = slot.getWeek(nowWeek);
        request.setAttribute("nowWeek", nowWeek);

        //get list week
        ArrayList<String> listWeek = slot.getListWeek(year);
        request.setAttribute("listWeek", listWeek);

        //get list day
        ArrayList<String> listDayOfWeek = slot.listDayOfWeek(nowWeek, String.valueOf(year));
        request.setAttribute("listDayOfWeek", listDayOfWeek);

        //get acc
        String aName = request.getParameter("aName");
        request.setAttribute("aName", aName);
        Accounts acc = new Accounts(aName);
        acc = acc.getAccByUsername();
        
        //get slot by id
        if (acc.getRoleNum() == 3) {
            //get slot split by role teacher
            Teacher teach = new Teacher();
            System.out.println("aid = "+ acc.getID());
            teach = teach.getTeacherbyaId(String.valueOf(acc.getID()));
            System.out.println("teacher = "+ teach.getCode());
            
            //get slot from dtb by week origin by role
            ArrayList<Slot> listSlot = slot.getSlotByWeekTeach(nowWeek, String.valueOf(year), teach.getTeacherID());
            request.setAttribute("listSlot", listSlot);
        } else if (acc.getRoleNum() == 2) {
            request.getRequestDispatcher("home.jsp").forward(request, response);
        } else {
            //get slot split by role student
            Student stu = new Student();
            stu = stu.getStudentByaID(String.valueOf(acc.getID()));

            //get slot from dtb by week origin by role
            ArrayList<Slot> listSlot = slot.getSlotByWeekStu(nowWeek, String.valueOf(year), stu.getStudentID());
            request.setAttribute("listSlot", listSlot);

            //get list attendance by slot and studentid
            Attendance att = new Attendance();
            ArrayList<Attendance> listAttendance = null;
            for (Slot slot1 : listSlot) {
                att = att.getAttendanceBySS(slot1.getSlotID(), stu.getStudentID());
                if (att == null) {
                    att = new Attendance();
                }
                listAttendance.add(att);
            }
            request.setAttribute("listAttendance", listAttendance);
        }
        request.getRequestDispatcher("wtime.jsp").forward(request, response);
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
        //update slot Num
        if(request.getParameter("updateSlotNum") != null){
            String slotNum = request.getParameter("setSlotNum");
            String slotId = request.getParameter("slotId");
            System.out.println("demo" + slotNum + "demo" + slotId);
            Slot slot = new Slot();
            slot.setSlotNum(Integer.parseInt(slotNum));
            if(slot.updateSlotNumById(slotId)){
                doGet(request, response);
            }
        }
        
        //get list year
        //lay danh sach year tu database
        Slot slot = new Slot();
        ArrayList<String> listYear = slot.getListYear();
        request.setAttribute("listYear", listYear);

        //get week, year from client
        String nowWeek = request.getParameter("week");
        request.setAttribute("nowWeek", nowWeek);

        int year = Integer.parseInt(request.getParameter("year"));
        request.setAttribute("year", year);

        //get list week
        ArrayList<String> listWeek = slot.getListWeek(year);
        request.setAttribute("listWeek", listWeek);

        //get list day of week
        ArrayList<String> listDayOfWeek = slot.listDayOfWeek(nowWeek, String.valueOf(year));
        request.setAttribute("listDayOfWeek", listDayOfWeek);

        //get acc
        String aName = request.getParameter("aName");
        request.setAttribute("aName", aName);
        Accounts acc = new Accounts(aName);
        acc = acc.getAccByUsername();
        if (acc.getRoleNum() == 3) {
            //get slot split by role teacher
            Teacher teach = new Teacher();
            teach = teach.getTeacherbyaId(String.valueOf(acc.getID()));

            //get slot from dtb by week origin by role
            ArrayList<Slot> listSlot = slot.getSlotByWeekTeach(nowWeek, String.valueOf(year), teach.getTeacherID());
            request.setAttribute("listSlot", listSlot);
        } else {
            //get slot split by role student
            Student stu = new Student();
            stu = stu.getStudentByaID(String.valueOf(acc.getID()));

            //get slot from dtb by week origin by role
            ArrayList<Slot> listSlot = slot.getSlotByWeekStu(nowWeek, String.valueOf(year), stu.getStudentID());
            request.setAttribute("listSlot", listSlot);

            //get list attendance by slot and studentid
            Attendance att = new Attendance();
            ArrayList<Attendance> listAttendance = att.getListAttendance();
            listAttendance.clear();
            if(!listSlot.isEmpty()){
                for (Slot slot1 : listSlot) {
                att = att.getAttendanceBySS(slot1.getSlotID(), stu.getStudentID());
                listAttendance.add(att);
            }
            request.setAttribute("listAttendance", listAttendance);
            }
        }

        request.getRequestDispatcher("wtime.jsp").forward(request, response);
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
