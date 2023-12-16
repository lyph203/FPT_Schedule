/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import dal.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Pham Huong Ly
 */
public class Attendance extends DBContext{
    private int studentID, slotID, teacherID;
    private String isAbsent;
    private String comment, teacherName, studentName;

    public Attendance() {
        connect();
    }

    public Attendance(int studentID, int slotID, int teacherID, String isAbsent, String comment) {
        this.studentID = studentID;
        this.slotID = slotID;
        this.teacherID = teacherID;
        this.isAbsent = isAbsent;
        this.comment = comment;
        connect();
    }

    public Attendance(int slotID, String isAbsent, String comment) {
        this.slotID = slotID;
        this.isAbsent = isAbsent;
        this.comment = comment;
        connect();
    }
    
    public Attendance(String teacherName, int studentID, int slotID, String isAbsent, String comment) {
        this.teacherName = teacherName;
        this.studentID = studentID;
        this.slotID = slotID;
        this.isAbsent = isAbsent;
        this.comment = comment;
        connect();
    }

    public Attendance(String isAbsent, String comment, String studentName, int studentID) {
        this.isAbsent = isAbsent;
        this.comment = comment;
        this.studentName = studentName;
        this.studentID = studentID;
        connect();
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public int getSlotID() {
        return slotID;
    }

    public void setSlotID(int slotID) {
        this.slotID = slotID;
    }

    public String getisAbsent() {
        return isAbsent;
    }

    public void setIsAbsent(String isAbsent) {
        this.isAbsent = isAbsent;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(int teacherID) {
        this.teacherID = teacherID;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
    
    //Khai bao cac thanh phan xu ly database
    Connection cnn;
    ResultSet rs; //luu tru va xu ly du lieu
    PreparedStatement pstm;//thuc hien cac cau lenh sql
    
    private void connect() {
//        try {
//            cnn = (new DBContext().getConnection());
//            System.out.println("Connect Successfully");
//        } catch (Exception e) {
//            System.out.println("Connect fail: " + e.getMessage());
//        }
       cnn = this.connection;
       if(cnn != null){
           System.out.println("Connect Successfully");
       } else{
           System.out.println("Connect fail: ");
       }
    }

    public ArrayList<Attendance> getAttendanceBySID(String optTerm, String optClassroom, String studentId) {
        ArrayList<Attendance> listAtt = new ArrayList<>();
        try {
            String sql = "select s.slotId, s.slotNumber, su.subjectCode, r.roomName, s.date, t.teacherName, a.isAbsent \n" +
"from [Slot] s, [Teacher] t, [Room] r, [Attendance] a, [Subject] su\n" +
"where s.slotId = a.slotId and s.teacherId = t.teacherId and s.roomId = r.roomId and a.studentId = ? and s.subjectId = su.subjectId";
            pstm = cnn.prepareStatement(sql);
            pstm.setString(1, studentId);
            rs = pstm.executeQuery();
            
            while (rs.next()) {
                String slotID = rs.getString(1);
                String isAbsent = rs.getInt(2) == 1 ? "Absent" : "Present";
                String comment = rs.getString(3);
                Attendance att = new Attendance(Integer.parseInt(slotID), isAbsent, comment);
                listAtt.add(att);
            }
        } catch (SQLException e) {
            System.out.println("checkGetAttListByClass : " + e.getMessage());
        }
        return listAtt;
    }
    
    public ArrayList<Attendance> getAttendanceBycID(String classID, String teacherID, String slotId) {
        ArrayList<Attendance> listAtt = new ArrayList<>();
        try {
            String sql = "select st.fullName, a.isAbsent, a.comment from [Attendance] a, [Student] st \n" +
            "where a.studentId = st.studentId and a.teacherId = 5 and a.slotId = ";
            pstm = cnn.prepareStatement(sql);
            pstm.setString(1, classID);
            pstm.setString(2, teacherID);
            rs = pstm.executeQuery();
            
            while (rs.next()) {
                String slotID = rs.getString(1);
                String isAbsent = rs.getInt(2) == 1 ? "Absent" : "Present";
                String comment = rs.getString(3);
                Attendance att = new Attendance(Integer.parseInt(slotID), isAbsent, comment);
                listAtt.add(att);
            }
        } catch (SQLException e) {
            System.out.println("checkGetAttListByClass : " + e.getMessage());
        }
        return listAtt;
    }

    public ArrayList<Attendance> getListAttendance() {
        ArrayList<Attendance> listAtt = new ArrayList<>();
        try {
            String sql = "select * from [Attendance]";
            pstm = cnn.prepareStatement(sql);
            rs = pstm.executeQuery();
            
            while (rs.next()) {
                int slotID = rs.getInt(5);
                String isAbsent = rs.getInt(3) == 1 ? "Absent" : "Present";
                String comment = rs.getString(4);
                int teacherID = rs.getInt(1);
                int studentID = rs.getInt(2);
                Attendance att = new Attendance(studentID, slotID, teacherID, isAbsent, comment);
                listAtt.add(att);
            }
        } catch (SQLException e) {
            System.out.println("checkGetAttList : " + e.getMessage());
        }
        return listAtt;
    }
    
    public ArrayList<Attendance> getListAttendance(String cId, String slotId) {
        ArrayList<Attendance> listAtt = new ArrayList<>();
        try {
            String sql = "select st.fullName, a.studentId, a.isAbsent, a.comment from [Attendance] a, [Student] st, Study s\n" +
            "where a.studentId = st.studentId and s.classId = ? and st.studentId = s.studentId and a.slotId = ?";
            pstm = cnn.prepareStatement(sql);
            pstm.setString(1, cId);
            pstm.setString(2, slotId);
            rs = pstm.executeQuery();
            
            while (rs.next()) {
                String name = rs.getString(1);
                String id = rs.getString(2);
                String isAbsent = rs.getInt(3) == 1 ? "1" : "0";
                String comment = rs.getString(4);
                Attendance att = new Attendance(isAbsent, comment, name, Integer.parseInt(id));
                listAtt.add(att);
            }
        } catch (SQLException e) {
            System.out.println("checkGetAttList : " + e.getMessage());
        }
        return listAtt;
    }

    public void editAttendance(int studentID, String slotId, String attend, String comment) {
        try {
            String sql = "UPDATE [Attendance] SET [isAbsent] = ?, [comment] = ?\n" +
                        "WHERE [slotId] = ? and [studentId] = ?";
            pstm = cnn.prepareStatement(sql);
            pstm.setString(1, attend);
            pstm.setString(2, comment);
            pstm.setString(3, slotId);
            pstm.setInt(4, studentID);
            pstm.execute();
            
        } catch (SQLException e) {
            System.out.println("check editAttendance : " + e.getMessage());
        }
    }

    public Attendance getAttendanceBySS(int slotID, int studentID) {
        Attendance att = null;
        try {
            String sql = "select * from [Attendance] where [slotId] = ? and studentId = ?";
            pstm = cnn.prepareStatement(sql);
            pstm.setInt(1, slotID);
            pstm.setInt(2, studentID);
            rs = pstm.executeQuery();
            
            while (rs.next()) {
                int slotId = rs.getInt(5);
                String isAbsent = rs.getInt(3) == 1 ? "Absent" : "Present";
                String comment = "";
                if(rs.getString(4) != null) comment = rs.getString(4);
                int teacherID = rs.getInt(1);
                int studentId = rs.getInt(2);
                att = new Attendance(studentId, slotId, teacherID, isAbsent, comment);
            }
        } catch (SQLException e) {
            System.out.println("check getAttendanceBySS : " + e.getMessage());
        }
        if(att != null){
            return att;
        }
            return new Attendance();
    }

    public boolean addAtt(String teacher, String student, String ab, String comment, String slotId) {
        try {
            String strSQL = "insert into [Attendance] ([teacherId], [studentId], [comment], [isAbsent], [slotId]) values (?, ?, ?, ?, ?)";
            pstm = cnn.prepareStatement(strSQL);
            pstm.setString(1, teacher);
            pstm.setString(2, student);
            pstm.setString(3, comment);
            pstm.setString(4, ab);
            pstm.setString(5, slotId);
            pstm.execute();
            return true;

        } catch (SQLException e) {
            System.out.println("SQL addAtt: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("addAtt:" + e.getMessage());
        }
        return false;
    }

    public boolean updateAtt(String teacher, String student, String ab, String comment, String slotId, String lastTeacher, String lastStudent, String lastSlotId) {
        try {
            String strSQL = "UPDATE [Attendance] set [teacherId] = ?, [studentId] = ?, [comment] = ?, [isAbsent] = ?, [slotId] = ?\n" +
                            "where [teacherId] = ? and [studentId] = ? and [slotId] = ?";
            pstm = cnn.prepareStatement(strSQL);
            pstm.setString(1, teacher);
            pstm.setString(2, student);
            pstm.setString(3, comment);
            pstm.setString(4, ab);
            pstm.setString(5, slotId);
            pstm.setString(6, lastTeacher);
            pstm.setString(7, lastStudent);
            pstm.setString(8, lastSlotId);
            pstm.execute();
            return true;

        } catch (SQLException e) {
            System.out.println("SQL updateAtt: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("updateAtt:" + e.getMessage());
        }
        return false;
    }

    public boolean deleteAtt(String teacher, String student, String slotId) {
        try {
            String sql = "DELETE FROM [Attendance] WHERE [teacherId] = ? and [studentId] = ? and [slotId] = ?";
            pstm = cnn.prepareStatement(sql);
            pstm.setString(1, teacher);
            pstm.setString(2, student);
            pstm.setString(3, slotId);
            pstm.executeQuery();
            return true;
        } catch (Exception e) {
            System.out.println("check deleteAtt : " + e.getMessage());
        }
        return false;
    }

    public Attendance getAttByInf(String teacher, String student, String slotId) {
        Attendance att = null;
        try {
            String sql = "select * from [Attendance] where [teacherId] = ? and [studentId] = ? and [slotId] = ?";
            pstm = cnn.prepareStatement(sql);
            pstm.setString(1, teacher);
            pstm.setString(2, student);
            pstm.setString(3, slotId);
            rs = pstm.executeQuery();
            
            while (rs.next()) {
                int slotID = rs.getInt(5);
                String isAbsent = rs.getInt(3) == 1 ? "Absent" : "Present";
                String comment = rs.getString(4);
                int teacherID = rs.getInt(1);
                int studentID = rs.getInt(2);
                att = new Attendance(studentID, slotID, teacherID, isAbsent, comment);
            }
        } catch (Exception e) {
            System.out.println("check getAttByInf : " + e.getMessage());
        }
        return att;
    }
}

