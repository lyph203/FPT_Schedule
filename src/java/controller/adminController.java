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
import model.Accounts;
import model.Attendance;
import model.Classroom;
import model.Room;
import model.Slot;
import model.Student;
import model.Subject;
import model.Teacher;
import model.Term;

/**
 *
 * @author Pham Huong Ly
 */
public class adminController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Slot slot = new Slot();
        if(request.getParameter("delSlot") != null){
            String id = request.getParameter("slotId");
            if(slot.deleteSlotById(id)){
                System.out.println("done delete");
                doGet(request, response);
            }
        }
        ArrayList<Slot> listSlot = slot.getListSlot();
        request.setAttribute("listSlot", listSlot);
        
        Accounts acc = new Accounts();
        if(request.getParameter("delAccount") != null){
            String id = request.getParameter("accId");
            if(acc.deleteAccountById(id)){
                System.out.println("done delete acc");
                doGet(request, response);
            }
        }
        ArrayList<Accounts> listAccounts = acc.getListAccounts();
        request.setAttribute("listAccounts", listAccounts);
        
        Attendance att = new Attendance();
        if(request.getParameter("delAttendance") != null){
            String teacher = request.getParameter("teacher");
            String student = request.getParameter("student");
            String slotId = request.getParameter("slot");
            if(att.deleteAtt(teacher, student, slotId)){
                System.out.println("done delete att");
                doGet(request, response);
            }
        }
        ArrayList<Attendance> listAttendance = att.getListAttendance();
        request.setAttribute("listAttendance", listAttendance);
        
        Classroom cla = new Classroom();
        if(request.getParameter("delClass") != null){
            String id = request.getParameter("classId");
            if(cla.deleteClassroomById(id)){
                System.out.println("done delete Classroom");
                doGet(request, response);
            }
        }
        ArrayList<Classroom> listClassroom = cla.getListClassroom();
        request.setAttribute("listClassroom", listClassroom);
        
        Room room = new Room();
        if(request.getParameter("delRoom") != null){
            String id = request.getParameter("roomId");
            if(room.deleteRoomById(id)){
                System.out.println("done delete Room");
                doGet(request, response);
            }
        }
        ArrayList<Room> listRoom = room.getListRoom();
        request.setAttribute("listRoom", listRoom);
        
        Student stu = new Student();
        if(request.getParameter("delStudent") != null){
            String id = request.getParameter("stuId");
            if(stu.deleteStudentById(id)){
                System.out.println("done delete student");
                doGet(request, response);
            }
        }
        ArrayList<Student> listStudent = stu.getListStudent();
        request.setAttribute("listStudent", listStudent);
        
        Subject sub = new Subject();
        if(request.getParameter("delSubject") != null){
            String id = request.getParameter("subId");
            if(sub.deleteSubjectById(id)){
                System.out.println("done delete Subject");
                doGet(request, response);
            }
        }
        ArrayList<Subject> listSubject = sub.getListSubject();
        request.setAttribute("listSubject", listSubject);
        
        Teacher tea = new Teacher();
        if(request.getParameter("delTeacher") != null){
            String id = request.getParameter("teachId");
            if(tea.deleteTeacherById(id)){
                System.out.println("done delete tea");
                doGet(request, response);
            }
        }
        ArrayList<Teacher> listTeacher = tea.getListTeacher();
        request.setAttribute("listTeacher", listTeacher);
        
        Term term = new Term();
        if(request.getParameter("delTerm") != null){
            String id = request.getParameter("termId");
            if(term.deleteTermById(id)){
                System.out.println("done delete Term");
                doGet(request, response);
            }
        }
        ArrayList<Term> listTerm = term.getListTerm();
        request.setAttribute("listTerm", listTerm);
                
        //slot edit
        if(request.getParameter("addSlot") != null){
            request.getRequestDispatcher("slotEdit.jsp").forward(request, response);
        } else if (request.getParameter("updateSlot") != null){
            slot = slot.getSlotByID(request.getParameter("slotId"));
            request.setAttribute("slot", slot);
            request.getRequestDispatcher("slotEdit.jsp").forward(request, response);
        }
        
        //student edit
        if(request.getParameter("addStudent") != null){
            request.getRequestDispatcher("studentEdit.jsp").forward(request, response);
        } else if (request.getParameter("updateStudent") != null){
            stu = stu.getStudentByID(request.getParameter("stuId"));
            request.setAttribute("stu", stu);
            request.getRequestDispatcher("studentEdit.jsp").forward(request, response);
        }
        
        //class edit
        if(request.getParameter("addClass") != null){
            request.getRequestDispatcher("classEdit.jsp").forward(request, response);
        } else if (request.getParameter("updateClass") != null){
            cla = cla.getClassByID(request.getParameter("classId"));
            request.setAttribute("cla", cla);
            request.getRequestDispatcher("classEdit.jsp").forward(request, response);
        }
        
        //room edit
        if(request.getParameter("addRoom") != null){
            request.getRequestDispatcher("roomEdit.jsp").forward(request, response);
        } else if (request.getParameter("updateRoom") != null){
            room = room.getRoomByID(request.getParameter("roomId"));
            request.setAttribute("room", room);
            request.getRequestDispatcher("roomEdit.jsp").forward(request, response);
        }
        
        //subject edit
        if(request.getParameter("addSubject") != null){
            request.getRequestDispatcher("subjectEdit.jsp").forward(request, response);
        } else if (request.getParameter("updateSubject") != null){
            sub = sub.getSubByID(request.getParameter("subId"));
            request.setAttribute("sub", sub);
            request.getRequestDispatcher("subjectEdit.jsp").forward(request, response);
        }
        
        //term edit
        if(request.getParameter("addTerm") != null){
            request.getRequestDispatcher("termEdit.jsp").forward(request, response);
        } else if (request.getParameter("updateTerm") != null){
            term = term.getTermByID(request.getParameter("termId"));
            request.setAttribute("term", term);
            request.getRequestDispatcher("termEdit.jsp").forward(request, response);
        }
        
        //account edit
        if(request.getParameter("addAccount") != null){
            request.getRequestDispatcher("accEdit.jsp").forward(request, response);
        } else if (request.getParameter("updateAccount") != null){
            acc = acc.getAccByID(request.getParameter("accId"));
            request.setAttribute("acc", acc);
            request.getRequestDispatcher("accEdit.jsp").forward(request, response);
        }
        
        //attendance edit
        if(request.getParameter("addAttendance") != null){
            request.getRequestDispatcher("attEditAdmin.jsp").forward(request, response);
        } else if (request.getParameter("updateAttendance") != null){
            String teacher = request.getParameter("teaId");
            String student = request.getParameter("stuId");
            String slotId = request.getParameter("slot");
            att = att.getAttByInf(teacher, student, slotId);
            request.setAttribute("att", att);
            request.getRequestDispatcher("attEditAdmin.jsp").forward(request, response);
        }
        
        //teacher edit
        if(request.getParameter("addTeacher") != null){
            request.getRequestDispatcher("teacherEdit.jsp").forward(request, response);
        } else if (request.getParameter("updateTeacher") != null){
            tea = tea.getTeacherByID(request.getParameter("teachId"));
            request.setAttribute("tea", tea);
            request.getRequestDispatcher("teacherEdit.jsp").forward(request, response);
        }
        
        request.getRequestDispatcher("adminControl.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Slot slot = new Slot();
        ArrayList<Slot> listSlot = slot.getListSlot();
        request.setAttribute("listSlot", listSlot);
        
        Accounts acc = new Accounts();
        ArrayList<Accounts> listAccounts = acc.getListAccounts();
        request.setAttribute("listAccounts", listAccounts);
        
        Attendance att = new Attendance();
        ArrayList<Attendance> listAttendance = att.getListAttendance();
        request.setAttribute("listAttendance", listAttendance);
        
        Classroom cla = new Classroom();
        ArrayList<Classroom> listClassroom = cla.getListClassroom();
        request.setAttribute("listClassroom", listClassroom);
        
        Room room = new Room();
        ArrayList<Room> listRoom = room.getListRoom();
        request.setAttribute("listRoom", listRoom);
        
        Student stu = new Student();
        ArrayList<Student> listStudent = stu.getListStudent();
        request.setAttribute("listStudent", listStudent);
        
        Subject sub = new Subject();
        ArrayList<Subject> listSubject = sub.getListSubject();
        request.setAttribute("listSubject", listSubject);
        
        Teacher tea = new Teacher();
        ArrayList<Teacher> listTeacher = tea.getListTeacher();
        request.setAttribute("listTeacher", listTeacher);
        
        Term term = new Term();
        ArrayList<Term> listTerm = term.getListTerm();
        request.setAttribute("listTerm", listTerm);
        
        //slot edit
        if(request.getParameter("addSlotbtn") != null){
            String slotNum = request.getParameter("slotNum");
            String classroom = request.getParameter("class");
            String date = request.getParameter("date");
            System.out.println(date);
            String teach = request.getParameter("teach");
            String sroom = request.getParameter("room");
            String subject = request.getParameter("subject");
            if(slot.addSlot(slotNum, classroom, date, teach, sroom, subject)){
                System.out.println("doneAdd");
                doGet(request, response);
            }
        }
        
        if(request.getParameter("updateSlotbtn") != null){
            String slotId = request.getParameter("slotId");
            String slotNum = request.getParameter("slotNum");
            String classroom = request.getParameter("class");
            String date = request.getParameter("date");
            System.out.println(date);
            String teach = request.getParameter("teach");
            String sroom = request.getParameter("room");
            String subject = request.getParameter("subject");
            System.out.println("o4 update " + slotId + slotNum + classroom + date + teach + sroom + subject);
            if(slot.updateSlotById(slotId, slotNum, classroom, date, teach, sroom, subject)){
                System.out.println("done update Slotbtn");
                doGet(request, response);
            }
        }
        
        //student edit
        if(request.getParameter("addStudentbtn") != null){
            String name = request.getParameter("name");
            String gender = request.getParameter("gender");
            String dob = request.getParameter("dob");
            String address = request.getParameter("address");
            String aId = request.getParameter("aId");
            if(stu.addStudent(name, gender, dob, address, aId)){
                System.out.println("doneAdd stu");
                doGet(request, response);
            }
        }
        
        if(request.getParameter("updateStudentbtn") != null){
            String stuId = request.getParameter("stuId");
            String name = request.getParameter("name");
            String gender = request.getParameter("gender");
            String dob = request.getParameter("dob");
            String address = request.getParameter("address");
            String aId = request.getParameter("aId");
            if(stu.updateStudent(stuId, name, gender, dob, address, aId)){
                System.out.println("done update stu");
                doGet(request, response);
            }
        }
        
        //class edit
        if(request.getParameter("addClassbtn") != null){
            String name = request.getParameter("name");
            if(cla.addClass(name)){
                System.out.println("doneAdd Class");
                doGet(request, response);
            }
        }
        
        if(request.getParameter("updateClassbtn") != null){
            String name = request.getParameter("name");
            String classId = request.getParameter("classId");
            if(cla.updateClass(classId, name)){
                System.out.println("done update Class");
                doGet(request, response);
            }
        }
        
        //room edit
        if(request.getParameter("addRoombtn") != null){
            String name = request.getParameter("name");
            if(room.addRoom(name)){
                System.out.println("doneAdd Room");
                doGet(request, response);
            }
        }
        
        if(request.getParameter("updateRoombtn") != null){
            String name = request.getParameter("name");
            String roomId = request.getParameter("roomId");
            if(room.updateRoom(roomId, name)){
                System.out.println("done update Room");
                doGet(request, response);
            }
        }
        
        //subject edit
        if(request.getParameter("addSubbtn") != null){
            String name = request.getParameter("name");
            String code = request.getParameter("code");
            String plan = request.getParameter("plan");
            String termid = request.getParameter("termid");
            String block = request.getParameter("block");
            if(sub.addSubject(name, code, plan, termid, block)){
                System.out.println("doneAdd subject");
                doGet(request, response);
            }
        }
        
        if(request.getParameter("updateSubbtn") != null){
            String subId = request.getParameter("subId");
            String name = request.getParameter("name");
            String code = request.getParameter("code");
            String plan = request.getParameter("plan");
            String termid = request.getParameter("termid");
            String block = request.getParameter("block");
            if(sub.updateSubject(name, code, plan, termid, block, subId)){
                System.out.println("done update subject");
                doGet(request, response);
            }
        }
        
        //term edit
        if(request.getParameter("addTermbtn") != null){
            String name = request.getParameter("name");
            if(term.addTerm(name)){
                System.out.println("doneAdd Term");
                doGet(request, response);
            }
        }
        
        if(request.getParameter("updateTermbtn") != null){
            String name = request.getParameter("name");
            String termId = request.getParameter("termId");
            if(term.updateTerm(termId, name)){
                System.out.println("done update Term");
                doGet(request, response);
            }
        }
        
        //account edit
        if(request.getParameter("addAccbtn") != null){
            String name = request.getParameter("name");
            String password = request.getParameter("password");
            String role = request.getParameter("role");
            if(acc.addAcc(name, password, role)){
                System.out.println("doneAdd Acc");
                doGet(request, response);
            }
        }
        
        if(request.getParameter("updateAccbtn") != null){
            String name = request.getParameter("name");
            String password = request.getParameter("password");
            String role = request.getParameter("role");
            String accId = request.getParameter("accId");
            if(acc.updateAcc(accId, name, password, role)){
                System.out.println("done update acc");
                doGet(request, response);
            }
        }
        
        //attendance edit
        if(request.getParameter("addAttbtn") != null){
            String teacher = request.getParameter("teacher");
            String student = request.getParameter("student");
            String isAb = request.getParameter("isAb");
            String comment = request.getParameter("comment");
            String slotId = request.getParameter("slot");
            if(att.addAtt(teacher, student, isAb, comment, slotId)){
                System.out.println("doneAdd attendance");
                doGet(request, response);
            }
        }
        
        if(request.getParameter("updateAttbtn") != null){
            String teacher = request.getParameter("teacher");
            String student = request.getParameter("student");
            String isAb = request.getParameter("isAb");
            String comment = request.getParameter("comment");
            String slotId = request.getParameter("slot");
            String lastTeacher = request.getParameter("lastTeaId");
            String lastStudent = request.getParameter("lastStuId");
            String lastSlotId = request.getParameter("lastSlot");
            if(att.updateAtt(teacher, student, isAb, comment, slotId, lastTeacher, lastStudent, lastSlotId)){
                System.out.println("done update attendance");
                doGet(request, response);
            }
        }
        
        //teacher edit
        if(request.getParameter("addTeachbtn") != null){
            String name = request.getParameter("name");
            String code = request.getParameter("code");
            String email = request.getParameter("email");
            String accId = request.getParameter("acc");
            if(tea.addTeacher(name, code, email, accId)){
                System.out.println("doneAdd teacher");
                doGet(request, response);
            }
        }
        
        if(request.getParameter("updateTeachbtn") != null){
            String name = request.getParameter("name");
            String code = request.getParameter("code");
            String email = request.getParameter("email");
            String accId = request.getParameter("acc");
            String teachId = request.getParameter("teachId");
            if(tea.updateTeacher(name, code, email, accId, teachId)){
                System.out.println("done update acc");
                doGet(request, response);
            }
        }
        
        request.getRequestDispatcher("adminControl.jsp").forward(request, response);
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
