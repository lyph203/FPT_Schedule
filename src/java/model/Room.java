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
public class Room extends DBContext {

    private int ID;
    private String name;

    public Room() {
        connect();
    }

    public Room(int ID, String name) {
        this.ID = ID;
        this.name = name;
        connect();
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
        if (cnn != null) {
            System.out.println("Connect Successfully");
        } else {
            System.out.println("Connect fail: ");
        }
    }

    public ArrayList<Room> getListRoom() {
        ArrayList<Room> data = new ArrayList<>();
        try {
            String strSQL = "select * from Room";
            pstm = cnn.prepareStatement(strSQL);
            rs = pstm.executeQuery();

            while (rs.next()) {
                String ID = rs.getString(1);
                String name = rs.getString(2);
                Room room = new Room(Integer.parseInt(ID), name);
                data.add(room);
            }
        } catch (Exception e) {
            System.out.println("checkGET ListRoom: " + e.getMessage());
        }
        return data;
    }

    public Room getRoomByID(String parameter) {
        Room room = null;
        try {
            String sql = "Select * from [Room] where [roomId] = ?";
            pstm = cnn.prepareStatement(sql);
            pstm.setString(1, parameter);
            rs = pstm.executeQuery();

            while (rs.next()) {
                String ID = rs.getString(1);
                String name = rs.getString(2);
                room = new Room(Integer.parseInt(ID), name);
            }
        } catch (Exception e) {
            System.out.println("check getRoomByID : " + e.getMessage());
        }
        return room;
    }

    public boolean deleteRoomById(String id) {
        try {
            String sql = "DELETE FROM [Room] WHERE [roomId] = ?";
            pstm = cnn.prepareStatement(sql);
            pstm.setString(1, id);
            pstm.executeQuery();
            return true;
        } catch (Exception e) {
            System.out.println("check deleteClassroomById : " + e.getMessage());
        }
        return false;
    }

    public boolean addRoom(String name) {
        try {
            String strSQL = "insert into [Room] ([roomName]) values (?)";
            pstm = cnn.prepareStatement(strSQL);
            pstm.setString(1, name);
            pstm.execute();
            return true;

        } catch (SQLException e) {
            System.out.println("SQL addRoom: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("addRoom:" + e.getMessage());
        }
        return false;
    }

    public boolean updateRoom(String roomId, String name) {
        try {
            String strSQL = "UPDATE [Room] SET [roomName] = ?\n" +
                            "WHERE [roomId] = ?";
            pstm = cnn.prepareStatement(strSQL);
            pstm.setString(1, name);
            pstm.setString(2, roomId);
            pstm.execute();
            return true;

        } catch (SQLException e) {
            System.out.println("SQL updateRoom: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("updateRoom:" + e.getMessage());
        }
        return false;
    }
}
