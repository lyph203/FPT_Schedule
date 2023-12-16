/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import dal.DBContext;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 *
 * @author Pham Huong Ly
 */
public class Student extends DBContext{
    private int studentID, accountID;
    private String address, fullname, gender;
    private String date;

    public Student() {
        connect();
    }

    public Student(int studentID, String address, String fullname, String gender, String date) {
        this.studentID = studentID;
        this.address = address;
        this.fullname = fullname;
        this.gender = gender;
        this.date = date;
        connect();
    }
    
    public Student(int studentID, int accountID, String address, String fullname, String date, String gender) {
        this.studentID = studentID;
        this.accountID = accountID;
        this.address = address;
        this.fullname = fullname;
        this.date = date;
        this.gender = gender;
        connect();
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String isGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public ArrayList<Student> getListStudent() {
        ArrayList<Student> data = new ArrayList<>();
        try {
        String strSQL = "select * from Student";
            pstm = cnn.prepareStatement(strSQL);
            rs = pstm.executeQuery();
            
            while(rs.next()){
                String ID = rs.getString(1);
                String name = rs.getString(2);
                String gender = String.valueOf(rs.getInt(3));
                gender = gender.equals("1")? "Nam" : "Nữ";
                Date ddob = null;
                String dob = "none";
                if(rs.getDate(4) != null){
                    ddob = rs.getDate(4);
                    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                    dob = format.format(ddob);
                }
                String address = rs.getString(5);
                Student stu;
                if(rs.getString(6) != null){
                    String aId = rs.getString(6);
                    stu = new Student(Integer.parseInt(ID), Integer.parseInt(aId), address, name, dob, gender);
                } else {
                    stu = new Student(Integer.parseInt(ID), address, name, gender, dob);
                }
                
                data.add(stu);
            }
        } catch (Exception e) {
            System.out.println("checkGET getlistStudent: " + e.getMessage());
        }
        return data;
    }

    public boolean deleteStudentById(String id) {
        try {
            String sql = "DELETE FROM [Student] WHERE [studentId] = ?";
            pstm = cnn.prepareStatement(sql);
            pstm.setString(1, id);
            pstm.executeQuery();
            return true;
        } catch (Exception e) {
            System.out.println("check deleteStudentById : " + e.getMessage());
        }
        return false;
    }

    public Student getStudentByID(String parameter) {
        Student stu = null;
        try {
            String sql = "Select * from [Student] where [studentId] = ?";
            pstm = cnn.prepareStatement(sql);
            pstm.setString(1, parameter);
            rs = pstm.executeQuery();
            
            while (rs.next()) {
                String ID = rs.getString(1);
                String name = rs.getString(2);
                String gender = String.valueOf(rs.getInt(3));
                Date ddob = null;
                String dob = "none";
                if(rs.getDate(4) != null){
                    ddob = rs.getDate(4);
                    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                    dob = format.format(ddob);
                }
                String address = rs.getString(5);
                if(rs.getString(6) != null){
                    String aId = rs.getString(6);
                    stu = new Student(Integer.parseInt(ID), Integer.parseInt(aId), address, name, dob, gender);
                } else {
                    stu = new Student(Integer.parseInt(ID), address, name, gender, dob);
                }
            }
        } catch (Exception e) {
            System.out.println("checkgetStudentByID : " + e.getMessage());
        }
        return stu;
    }
    
    public Student getStudentByaID(String parameter) {
        Student stu = null;
        try {
            String sql = "Select * from [Student] where [accountId] = ?";
            pstm = cnn.prepareStatement(sql);
            pstm.setString(1, parameter);
            rs = pstm.executeQuery();
            
            while (rs.next()) {
                String ID = rs.getString(1);
                String name = rs.getString(2);
                String gender = String.valueOf(rs.getInt(3));
                Date ddob = null;
                String dob = "none";
                if(rs.getDate(4) != null){
                    ddob = rs.getDate(4);
                    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                    dob = format.format(ddob);
                }
                String address = rs.getString(5);
                if(rs.getString(6) != null){
                    String aId = rs.getString(6);
                    stu = new Student(Integer.parseInt(ID), Integer.parseInt(aId), address, name, dob, gender);
                } else {
                    stu = new Student(Integer.parseInt(ID), address, name, gender, dob);
                }
            }
        } catch (Exception e) {
            System.out.println("checkgetStudentByaID : " + e.getMessage());
        }
        return stu;
    }

    public ArrayList<Student> getListStudentbyClass(String id) {
        ArrayList<Student> data = new ArrayList<>();
        try {
        String strSQL = "select * from [Student] s, [Study] st\n" +
            "  where s.studentId = st.studentId and st.classId = ?\n" +
            "  order by s.studentId";
            pstm = cnn.prepareStatement(strSQL);
            pstm.setString(1, id);
            rs = pstm.executeQuery();
            
            while(rs.next()){
                String ID = rs.getString(1);
                String name = rs.getString(2);
                String gender = String.valueOf(rs.getInt(3));
                gender = gender.equals("1")? "Nam" : "Nữ";
                Date ddob = null;
                String dob = "none";
                if(rs.getDate(4) != null){
                    ddob = rs.getDate(4);
                    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                    dob = format.format(ddob);
                }
                String address = rs.getString(5);
                Student stu;
                if(rs.getString(6) != null){
                    String aId = rs.getString(6);
                    stu = new Student(Integer.parseInt(ID), Integer.parseInt(aId), address, name, dob, gender);
                } else {
                    stu = new Student(Integer.parseInt(ID), address, name, gender, dob);
                }
                
                data.add(stu);
            }
        } catch (Exception e) {
            System.out.println("checkGET getListStudentbyClass: " + e.getMessage());
        }
        return data;
    }

    public boolean addStudent(String name, String gender, String dob, String address, String aId) {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(dob, outputFormatter);
        Date sqlDate = Date.valueOf(localDate);
        try {
            String strSQL = "insert into [Student] ([fullName], [gender], [dob], [address]) values(?, ?, ?, ?)";
            pstm = cnn.prepareStatement(strSQL);
            pstm.setString(1, name);
            pstm.setString(2, gender);
            pstm.setDate(3, sqlDate);
            pstm.setString(4, address);
            pstm.execute();
            return true;

        } catch (SQLException e) {
            System.out.println("SQL addStudent: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("addStudents:" + e.getMessage());
        }
        return false;
    }

    public boolean updateStudent(String stuId, String name, String gender, String dob, String address, String aId) {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(dob, outputFormatter);
        Date sqlDate = Date.valueOf(localDate);
        try {
            String strSQL = "UPDATE [Student] SET [fullName] = ?, [gender] = ?, [dob] = ?, [address] = ?, [accountId] = ?\n" +
                            "WHERE [studentId] = ?";
            pstm = cnn.prepareStatement(strSQL);
            pstm.setString(1, name);
            pstm.setString(2, gender);
            pstm.setDate(3, sqlDate);
            pstm.setString(4, address);
            pstm.setString(5, aId);
            pstm.setString(6, stuId);
            pstm.execute();
            return true;

        } catch (SQLException e) {
            System.out.println("SQL updateStudent: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("updateStudent:" + e.getMessage());
        }
        return false;
    }
}


