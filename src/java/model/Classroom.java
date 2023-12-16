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
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Pham Huong Ly
 */
public class Classroom extends DBContext{
    private int ID, teachId;
    private String name;

    public Classroom() {
        connect();
    }

    public Classroom(int ID, String name) {
        this.ID = ID;
        this.name = name;
        connect();
    }

    public Classroom(int ID, int teachId, String name) {
        this.ID = ID;
        this.teachId = teachId;
        this.name = name;
        connect();
    }
    
    //Khai bao cac thanh phan xu ly database
    Connection cnn;
    Statement stm; //thuc hien cac cau lenh sql
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

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTeachId() {
        return teachId;
    }

    public void setTeachId(int teachId) {
        this.teachId = teachId;
    }
    
    public ArrayList<Classroom> getListClassroomBySub(String term, String subject) {
        ArrayList<Classroom> data = new ArrayList<>();
        try {
        String strSQL = "  Select distinct c.classId, className, s.teacherId from [Slot] s, [Class] c, [Subject] su\n" +
"where s.subjectId = ?  and su.termId = ? and  s.subjectId = su.subjectId and s.classId = c.classId";
            pstm = cnn.prepareStatement(strSQL);
            pstm.setString(1, subject);
            pstm.setString(2, term);
            rs = pstm.executeQuery();
            
            while(rs.next()){
                String ID = rs.getString(1);
                String name = rs.getString(2);
                String teachID = rs.getString(3);
                Classroom exClass = new Classroom(Integer.parseInt(ID), Integer.parseInt(teachID), name);
                data.add(exClass);
            }
        } catch (Exception e) {
            System.out.println("getListClassroomBySub" + e.getMessage());
        }
        return data;
    }
    
    public ArrayList<Classroom> getListClassroomByTerm(String optTerm) {
        ArrayList<Classroom> data = new ArrayList<>();
        try {
        String strSQL = "Select distinct c.classId, className from [Slot] s, [Class] c, [Subject] su\n" +
        "where s.subjectId = su.subjectId and s.classId = c.classId and su.termId = ?";
        pstm = cnn.prepareStatement(strSQL);
        pstm.setString(1, optTerm);
        rs = pstm.executeQuery();
        
            while(rs.next()){
                String ID = rs.getString(1);
                String name = rs.getString(2);
                Classroom exClass = new Classroom(Integer.parseInt(ID), name);
                data.add(exClass);
            }
        } catch (Exception e) {
            System.out.println("getListClassroomByTerm" + e.getMessage());
        }
        return data;
    }
    
    public ArrayList<Classroom> getListClassroomByTeach(String teacherId) {
        ArrayList<Classroom> data = new ArrayList<>();
        try {
        String strSQL = "select c.classId, c.className from [Class] c, [Teach] t\n" +
                        "where c.classId = t.classId and t.teacherId = ?";
            pstm = cnn.prepareStatement(strSQL);
            pstm.setString(1, teacherId);
            rs = pstm.executeQuery();
            
            while(rs.next()){
                String ID = rs.getString(1);
                String name = rs.getString(2);
                Classroom exClass = new Classroom(Integer.parseInt(ID), name);
                data.add(exClass);
            }
        } catch (Exception e) {
            System.out.println("checkGET ListClassroomByTeach: " + e.getMessage());
        }
        return data;
    }

    public ArrayList<Classroom> getListClassroom() {
        ArrayList<Classroom> data = new ArrayList<>();
        try {
        String strSQL = "select * from Class";
            pstm = cnn.prepareStatement(strSQL);
            rs = pstm.executeQuery();
            
            while(rs.next()){
                String ID = rs.getString(1);
                String name = rs.getString(2);
                Classroom exClass = new Classroom(Integer.parseInt(ID), name);
                data.add(exClass);
            }
        } catch (Exception e) {
            System.out.println("checkGET ListClassroom: " + e.getMessage());
        }
        return data;
    }

    public Classroom getClassByID(String parameter) {
        Classroom cla = null;
        try {
            String sql = "select * from [Class] where [classId] = ?";
            pstm = cnn.prepareStatement(sql);
            pstm.setString(1, parameter);
            rs = pstm.executeQuery();
            
            while (rs.next()) {
                String ID = rs.getString(1);
                String name = rs.getString(2);
                cla = new Classroom(Integer.parseInt(ID), name);
            }
        } catch (Exception e) {
            System.out.println("check getClassByID : " + e.getMessage());
        }
        return cla;
    }

    public boolean addClass(String name) {
        try {
            String strSQL = "insert into [Class] ([className]) values (?)";
            pstm = cnn.prepareStatement(strSQL);
            pstm.setString(1, name);
            pstm.execute();
            return true;

        } catch (SQLException e) {
            System.out.println("SQL addClass: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("addClass:" + e.getMessage());
        }
        return false;
    }

    public boolean updateClass(String classId, String name) {
        try {
            String strSQL = "UPDATE [Class] SET [className] = ?\n" +
                            "WHERE [classId] = ?";
            pstm = cnn.prepareStatement(strSQL);
            pstm.setString(1, name);
            pstm.setString(2, classId);
            pstm.execute();
            return true;

        } catch (SQLException e) {
            System.out.println("SQL updateClass: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("updateClass:" + e.getMessage());
        }
        return false;
    }

    public boolean deleteClassroomById(String id) {
        try {
            String sql = "DELETE FROM [Class] WHERE [classId] = ?";
            pstm = cnn.prepareStatement(sql);
            pstm.setString(1, id);
            pstm.executeQuery();
            return true;
        } catch (Exception e) {
            System.out.println("check deleteClassroomById : " + e.getMessage());
        }
        return false;
    }
}
