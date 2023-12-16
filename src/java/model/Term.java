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
public class Term extends DBContext{
    String ID, name;

    public Term() {
        connect();
    }

    public Term(String ID, String name) {
        this.ID = ID;
        this.name = name;
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

    public ArrayList<Term> getListTerm() {
        ArrayList<Term> data = new ArrayList<>();
        try {
        String strSQL = "select * from Term";
            stm = cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE); // lay tuan tu gia tri tu tren xuong
            rs = stm.executeQuery(strSQL);
            
            while(rs.next()){
                String ID = rs.getString(1);
                String termName = rs.getString(2);
                Term term = new Term(ID, termName);
                data.add(term);
            }
        } catch (Exception e) {
            System.out.println("getListTerm" + e.getMessage());
        }
        return data;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean deleteTermById(String id) {
        try {
            String sql = "DELETE FROM [Term] WHERE [termId] = ?";
            pstm = cnn.prepareStatement(sql);
            pstm.setString(1, id);
            pstm.executeQuery();
            return true;
        } catch (SQLException e) {
            System.out.println("check deleteTermById : " + e.getMessage());
        }
        return false;
    }

    public Term getTermByID(String parameter) {
        Term term = null;
        try {
            String sql = "select * from [Term] where [termId] = ?";
            pstm = cnn.prepareStatement(sql);
            pstm.setString(1, parameter);
            rs = pstm.executeQuery();
            
            while (rs.next()) {
                String ID = rs.getString(1);
                String name = rs.getString(2);
                term = new Term(ID, name);
            }
        } catch (Exception e) {
            System.out.println("check getTermByID : " + e.getMessage());
        }
        return term;
    }

    public boolean addTerm(String name) {
        try {
            String strSQL = "insert into [Term] ([termName]) values (?)";
            pstm = cnn.prepareStatement(strSQL);
            pstm.setString(1, name);
            pstm.execute();
            return true;

        } catch (SQLException e) {
            System.out.println("SQL addTerm: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("addTerm:" + e.getMessage());
        }
        return false;
    }

    public boolean updateTerm(String termId, String name) {
        try {
            String strSQL = "UPDATE [Term] SET [termName] = ?\n" +
                            "WHERE [termId] = ?";
            pstm = cnn.prepareStatement(strSQL);
            pstm.setString(1, name);
            pstm.setString(2, termId);
            pstm.execute();
            return true;

        } catch (SQLException e) {
            System.out.println("SQL updateTerm: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("updateTerm:" + e.getMessage());
        }
        return false;
    }
    
}
