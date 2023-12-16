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
public class Teacher extends DBContext {

    private int teacherID, accountID;
    private String name, code, email;

    public Teacher() {
        connect();
    }

    public Teacher(int teacherID, int accountID, String name, String code, String email) {
        this.teacherID = teacherID;
        this.accountID = accountID;
        this.name = name;
        this.code = code;
        this.email = email;
        connect();
    }

    public int getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(int teacherID) {
        this.teacherID = teacherID;
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
        if (cnn != null) {
            System.out.println("Connect Successfully");
        } else {
            System.out.println("Connect fail: ");
        }
    }

    public ArrayList<Teacher> getListTeacher() {
        ArrayList<Teacher> listTeacher = new ArrayList<>();
        try {
            String strSQL = "select * from [Teacher]";
            stm = cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE); // lay tuan tu gia tri tu tren xuong
            rs = stm.executeQuery(strSQL);

            while (rs.next()) {
                String ID = rs.getString(1);
                String name = rs.getString(2);
                String code = rs.getString(3);
                String email = "none";
                    if(rs.getString(4) != null){
                        email = rs.getString(4);
                    }
                String accountID = rs.getString(5);
                Teacher teacher = new Teacher(Integer.parseInt(ID), Integer.parseInt(accountID), name, code, email);
                listTeacher.add(teacher);
            }
        } catch (Exception e) {
            System.out.println("checkGETTeacher: " + e.getMessage());
        }
        return listTeacher;
    }
    
    public Teacher getTeacherbyaId(String aId) {
        Teacher teacher = null;
        try {
            String sql = "select * from [Teacher] where [accountId] = ?";
            pstm = cnn.prepareStatement(sql);
            pstm.setString(1, aId);
            rs = pstm.executeQuery();

            while (rs.next()) {
                String ID = rs.getString(1);
                String name = rs.getString(2);
                String code = rs.getString(3);
                String email = "none";
                    if(rs.getString(4) != null){
                        email = rs.getString(4);
                    }
                String accountID = rs.getString(5);
                teacher = new Teacher(Integer.parseInt(ID), Integer.parseInt(accountID), name, code, email);
            }
        } catch (Exception e) {
            System.out.println("checkGETTeacher: " + e.getMessage());
        }
        return teacher;
    }

    public boolean deleteTeacherById(String id) {
        try {
            String sql = "DELETE FROM [Teacher] WHERE [teacherId] = ?";
            pstm = cnn.prepareStatement(sql);
            pstm.setString(1, id);
            pstm.executeQuery();
            return true;
        } catch (Exception e) {
            System.out.println("check deleteTeacherById : " + e.getMessage());
        }
        return false;
    }

    public Teacher getTeacherByID(String parameter) {
        Teacher tea = null;
        try {
            String sql = "select * from [Teacher] where [teacherId] = ?";
            pstm = cnn.prepareStatement(sql);
            pstm.setString(1, parameter);
            rs = pstm.executeQuery();
            
            while (rs.next()) {
                String ID = rs.getString(1);
                String name = rs.getString(2);
                String code = rs.getString(3);
                String email = "none";
                    if(rs.getString(4) != null){
                        email = rs.getString(4);
                    }
                String accountID = rs.getString(5);
                tea = new Teacher(Integer.parseInt(ID), Integer.parseInt(accountID), name, code, email);
            }
        } catch (Exception e) {
            System.out.println("check getTeacherByID : " + e.getMessage());
        }
        return tea;
    }

    public boolean addTeacher(String name, String code, String email, String accId) {
        try {
            String strSQL = "Insert into [Teacher] ([teacherName], [teacherCode], [email], [accountId]) values (?, ?, ?, ?)";
            pstm = cnn.prepareStatement(strSQL);
            pstm.setString(1, name);
            pstm.setString(2, code);
            pstm.setString(3, email);
            pstm.setString(4, accId);
            pstm.execute();
            return true;

        } catch (SQLException e) {
            System.out.println("SQL addTeacher: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("addTeacher:" + e.getMessage());
        }
        return false;
    }

    public boolean updateTeacher(String name, String code, String email, String accId, String teachId) {
        try {
            String strSQL = "UPDATE [Teacher] set [teacherName] = ?, [teacherCode] = ?, [email] = ?, [accountId] = ?\n" +
                            "where [teacherId] = ?";
            pstm = cnn.prepareStatement(strSQL);
            pstm.setString(1, name);
            pstm.setString(2, code);
            pstm.setString(3, email);
            pstm.setString(4, accId);
            pstm.setString(5, teachId);
            pstm.execute();
            return true;

        } catch (SQLException e) {
            System.out.println("SQL updateTeacher: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("updateTeacher:" + e.getMessage());
        }
        return false;
    }
}
