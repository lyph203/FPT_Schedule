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
public class Accounts extends DBContext{
    private int ID, roleNum;
    private String username, password;

    public Accounts() {
        connect();
    }

    public Accounts(String username) {
        this.username = username;
        connect();
    }

    public Accounts(String username, String password) {
        this.username = username;
        this.password = password;
        connect();
    }

    public Accounts(int ID, int roleNum, String username, String password) {
        this.ID = ID;
        this.roleNum = roleNum;
        this.username = username;
        this.password = password;
        connect();
    }
    
    //Khai bao cac thanh phan xu ly database
    Connection cnn;
    Statement stm; //thuc hien cac cau lenh sql
    ResultSet rs; //luu tru va xu ly du lieu
    PreparedStatement pstm; //thuc hien cac cau lenh sql
    
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
    
    public int getRoleNum() {
        return roleNum;
    }

    public void setRoleNum(int roleNum) {
        this.roleNum = roleNum;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean checkLogin() {
        try {
            String strSQL = "select * from Account where username=? and password=?";
            pstm = cnn.prepareStatement(strSQL);
            pstm.setString(1, username);
            pstm.setString(2, password);
            rs = pstm.executeQuery();
            
            while(rs.next()){
                //rs chua gia tri
                //tuc la cau lenh select tra ve gia tri khac null
                //tuc la ton tai 1 user co account va password nhap tren form
                return true;
            }
        } catch (Exception e) {
            System.out.println("checkLogin: " + e.getMessage());
        }
        return false;
    }
    
    public Accounts getAccByUsername() {
        try {
        String strSQL = "select * from Account where username = ?";
            pstm = cnn.prepareStatement(strSQL);
            pstm.setString(1, username);
            rs = pstm.executeQuery();
            
            while(rs.next()){
                int aId = rs.getInt(1);
                String name = rs.getString(2);
                String password = rs.getString(3);
                int role = rs.getInt(4);
                Accounts a = new Accounts(aId, role, name, password);
                return a;
            }
        } catch (SQLException e) {
            System.out.println("checkGetAccByUsername: " + e.getMessage());
        }
        return null;
    }

    public ArrayList<Accounts> getListAccounts() {
        ArrayList<Accounts> listAccounts = new ArrayList<>();
        try {
        String strSQL = "select * from Account";
            pstm = cnn.prepareStatement(strSQL);
            rs = pstm.executeQuery();
            
            while(rs.next()){
                int aId = rs.getInt(1);
                String name = rs.getString(2);
                String password = rs.getString(3);
                int role = rs.getInt(4);
                Accounts a = new Accounts(aId, role, name, password);
                listAccounts.add(a);
            }
        } catch (SQLException e) {
            System.out.println("checkGetAcc: " + e.getMessage());
        }
        return listAccounts;
    }

    public boolean deleteAccountById(String id) {
        try {
            String sql = "DELETE FROM [Account] WHERE [accountId] = ?";
            pstm = cnn.prepareStatement(sql);
            pstm.setString(1, id);
            pstm.executeQuery();
            return true;
        } catch (Exception e) {
            System.out.println("check deleteAccountById : " + e.getMessage());
        }
        return false;
    }

    public Accounts getAccByID(String parameter) {
        Accounts acc = null;
        try {
            String sql = "select * from [Account] where [accountId] = ?";
            pstm = cnn.prepareStatement(sql);
            pstm.setString(1, parameter);
            rs = pstm.executeQuery();
            
            while (rs.next()) {
                String ID = rs.getString(1);
                String name = rs.getString(2);
                String password = rs.getString(3);
                String roleNum = rs.getString(4);
                acc = new Accounts(Integer.parseInt(ID), Integer.parseInt(roleNum), name, password);
            }
        } catch (Exception e) {
            System.out.println("check getAccByID : " + e.getMessage());
        }
        return acc;
    }

    public boolean addAcc(String name, String password, String role) {
        try {
            String strSQL = "insert into [Account] ([username], [password], [roleNum]) values (?, ?, ?)";
            pstm = cnn.prepareStatement(strSQL);
            pstm.setString(1, name);
            pstm.setString(2, password);
            pstm.setString(3, role);
            pstm.execute();
            return true;

        } catch (SQLException e) {
            System.out.println("SQL addAcc: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("addAcc:" + e.getMessage());
        }
        return false;
    }

    public boolean updateAcc(String accId, String name, String password, String role) {
        try {
            String strSQL = "UPDATE [Account] set [username] = ?, [password] = ?, [roleNum] = ?\n" +
                            "where [accountId] = ?";
            pstm = cnn.prepareStatement(strSQL);
            pstm.setString(1, name);
            pstm.setString(2, password);
            pstm.setString(3, role);
            pstm.setString(4, accId);
            pstm.execute();
            return true;

        } catch (SQLException e) {
            System.out.println("SQL updateAcc: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("updateAcc:" + e.getMessage());
        }
        return false;
    }
}
