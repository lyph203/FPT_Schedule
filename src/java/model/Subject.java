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
public class Subject extends DBContext{
    private int ID, block, plan;
    private String code, name, term;

    public Subject() {
        connect();
    }

    public Subject(String code, String name) {
        this.code = code;
        this.name = name;
        connect();
    }

    public Subject(int ID, int block, int plan, String code, String name, String term) {
        this.ID = ID;
        this.block = block;
        this.plan = plan;
        this.code = code;
        this.name = name;
        this.term = term;
        connect();
    }
    
    //Khai bao cac thanh phan xu ly database
    Connection cnn;
    Statement stm; //thuc hien cac cau lenh sql
    ResultSet rs; //luu tru va xu ly du lieu
    PreparedStatement pstm;
    
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

    public int getBlock() {
        return block;
    }

    public void setBlock(int block) {
        this.block = block;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getPlan() {
        return plan;
    }

    public void setPlan(int plan) {
        this.plan = plan;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }
    
    public ArrayList<Subject> getListSubjectByTerm(String optTerm) {
        ArrayList<Subject> data = new ArrayList<>();
        try {
        String strSQL = "Select distinct * from [Subject] where termId = ?";
            pstm = cnn.prepareStatement(strSQL);
            pstm.setString(1, optTerm);
            rs = pstm.executeQuery();
            
            while(rs.next()){
                String ID = rs.getString(1);
                String code = rs.getString(2);
                String name = rs.getString(3);
                String block = rs.getString(6);
                String termID = rs.getString(5);
                String plan = rs.getString(4);
                Subject sub = new Subject(Integer.parseInt(ID), Integer.parseInt(block), 
                        Integer.parseInt(plan), code, name, String.valueOf(termID));
                data.add(sub);
            }
        } catch (Exception e) {
            System.out.println("checkGetListSubjectByTerm: " + e.getMessage());
        }
        return data;
    }

    public ArrayList<Subject> getListSubjectByClass(String classId){
        ArrayList<Subject> data = new ArrayList<>();
        try {
        String strSQL = "select distinct su.subjectName, su.subjectCode from Slot s, Class c, [Subject] su, Term t\n" +
        "where s.classId = c.classId and s.subjectId = su.subjectId and su.termId = t.termId and c.classId = '"+classId+"'";
            stm = cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE); // lay tuan tu gia tri tu tren xuong
            rs = stm.executeQuery(strSQL);
            
            while(rs.next()){
                String code = rs.getString(2);
                String name = rs.getString(1);
                Subject sub = new Subject(code, name);
                data.add(sub);
            }
        } catch (Exception e) {
            System.out.println("checkGetSjByClass" + e.getMessage());
        }
        return data;
    }

    public ArrayList<Subject> getListSubject() {
        ArrayList<Subject> data = new ArrayList<>();
        try {
        String strSQL = "Select * from [Subject]";
            pstm = cnn.prepareStatement(strSQL);
            rs = pstm.executeQuery();
            
            while(rs.next()){
                String ID = rs.getString(1);
                String code = rs.getString(2);
                String name = rs.getString(3);
                String block = rs.getString(6);
                String termID = rs.getString(5);
                String plan = rs.getString(4);
                Subject sub = new Subject(Integer.parseInt(ID), Integer.parseInt(block), 
                        Integer.parseInt(plan), code, name, String.valueOf(termID));
                data.add(sub);
            }
        } catch (Exception e) {
            System.out.println("checkGetListSubject: " + e.getMessage());
        }
        return data;
    }

    public boolean deleteSubjectById(String id) {
        try {
            String sql = "DELETE FROM [Subject] WHERE [subjectId] = ?";
            pstm = cnn.prepareStatement(sql);
            pstm.setString(1, id);
            pstm.executeQuery();
            return true;
        } catch (Exception e) {
            System.out.println("check deleteSubjectById : " + e.getMessage());
        }
        return false;
    }

    public Subject getSubByID(String parameter) {
        Subject sub = null;
        try {
            String sql = "Select * from [Subject] where [subjectId] = ?";
            pstm = cnn.prepareStatement(sql);
            pstm.setString(1, parameter);
            rs = pstm.executeQuery();
            
            while (rs.next()) {
                int id = rs.getInt(1);
                String code = rs.getString(2);
                String name = rs.getString(3);
                int plan = rs.getInt(4);
                String termID = String.valueOf(rs.getInt(5));
                int block = rs.getInt(6);
                sub = new Subject(id, block, plan, code, name, termID);
            }
        } catch (SQLException e) {
            System.out.println("check getSubByID : " + e.getMessage());
        }
        return sub;
    }

    public boolean addSubject(String name, String code, String plan, String termid, String block) {
        try {
            String strSQL = "insert into [Subject] ([subjectName], [subjectCode], [plan], [block], [termId]) values (?, ?, ?, ?, ?)";
            pstm = cnn.prepareStatement(strSQL);
            pstm.setString(1, name);
            pstm.setString(2, code);
            pstm.setString(3, plan);
            pstm.setString(4, block);
            pstm.setString(5, termid);
            pstm.execute();
            return true;

        } catch (SQLException e) {
            System.out.println("SQL addSubject: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("addSubject:" + e.getMessage());
        }
        return false;
    }

    public boolean updateSubject(String name, String code, String plan, String termid, String block, String subId) {
        try {
            String strSQL = "UPDATE [Subject] SET [subjectName] = ?, [subjectCode] = ?, [plan] = ?, [block] = ?, [termId] = ?\n" +
                            "WHERE [subjectId] = ?";
            pstm = cnn.prepareStatement(strSQL);
            pstm.setString(1, name);
            pstm.setString(2, code);
            pstm.setString(3, plan);
            pstm.setString(4, block);
            pstm.setString(5, termid);
            pstm.setString(6, subId);
            pstm.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("SQL updateSubject: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("updateSubject:" + e.getMessage());
        }
        return false;
    }
}
