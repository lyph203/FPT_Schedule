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
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;

/**
 *
 * @author Pham Huong Ly
 */
public class Slot extends DBContext{
    private int slotID, classID, slotNum, roomID, teacherID, subjectID;
    private String teacherName, roomName, subjectCode, date;

    public Slot() {
        connect();
    }

    public Slot(int slotID, int slotNum, String teacherName, String roomName, String subjectCode, String date) {
        this.slotID = slotID;
        this.slotNum = slotNum;
        this.teacherName = teacherName;
        this.roomName = roomName;
        this.subjectCode = subjectCode;
        this.date = date;
        connect();
    }

    public Slot(int slotID, int classID, int slotNum, int roomID, int teacherID, int subjectID, String date) {
        this.slotID = slotID;
        this.classID = classID;
        this.slotNum = slotNum;
        this.roomID = roomID;
        this.teacherID = teacherID;
        this.subjectID = subjectID;
        this.date = date;
        connect();
    }

    public Slot(int slotID, int classID, int slotNum, String teacherName, String roomName, String subjectCode, String date) {
        this.slotID = slotID;
        this.classID = classID;
        this.slotNum = slotNum;
        this.teacherName = teacherName;
        this.roomName = roomName;
        this.subjectCode = subjectCode;
        this.date = date;
        connect();
    }

    public Slot(int slotID, int classID, int slotNum, int roomID, int teacherID, String subjectCode, String date) {
        this.slotID = slotID;
        this.classID = classID;
        this.slotNum = slotNum;
        this.roomID = roomID;
        this.teacherID = teacherID;
        this.subjectCode = subjectCode;
        this.date = date;
        connect();
    }

    public int getSlotID() {
        return slotID;
    }

    public void setSlotID(int slotID) {
        this.slotID = slotID;
    }

    public int getClassID() {
        return classID;
    }

    public void setClassID(int classID) {
        this.classID = classID;
    }

    public int getSlotNum() {
        return slotNum;
    }

    public void setSlotNum(int slotNum) {
        this.slotNum = slotNum;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public int getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(int teacherID) {
        this.teacherID = teacherID;
    }

    public int getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(int subjectID) {
        this.subjectID = subjectID;
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

    public ArrayList<String> getListYear() {
        ArrayList<String> listYear = new ArrayList<>();
        try {
            String strSQL = "select distinct YEAR(date) from [Slot]";
            pstm = cnn.prepareStatement(strSQL);
            rs = pstm.executeQuery();
            
            while(rs.next()){
                String date = rs.getString(1);
                listYear.add(date);
            }
        } catch (Exception e) {
            System.out.println("checkLogin" + e.getMessage());
        }
        return listYear;
    }

    public ArrayList<String> getListWeek(int year) {
        ArrayList<String> listWeek = new ArrayList<>();
        LocalDate startOfYear = LocalDate.of(year, 1, 1);
        LocalDate endOfYear = LocalDate.of(year + 1, 1, 1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM");
        while (startOfYear.isBefore(endOfYear)) {
            LocalDate startOfWeek = startOfYear.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
            LocalDate endOfWeek = startOfYear.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
            String week = startOfWeek.format(formatter) + " To " + endOfWeek.format(formatter);
            listWeek.add(week);
            startOfYear = startOfYear.plusWeeks(1);
        }
        return listWeek;
    }
    
    public String getWeek(String day) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd/MM");
        LocalDate localDate = LocalDate.parse(day, formatter);
        DayOfWeek firstDayOfWeek = DayOfWeek.MONDAY;
        LocalDate startOfCurrentWeek = localDate.with(TemporalAdjusters.previousOrSame(firstDayOfWeek));
        DayOfWeek lastDayOfWeek = DayOfWeek.SUNDAY;
        LocalDate endOfCurrentWeek = localDate.with(TemporalAdjusters.nextOrSame(lastDayOfWeek));
        
        String firstDay = startOfCurrentWeek.format(outputFormatter);
        String lastDay = endOfCurrentWeek.format(outputFormatter);
        
        return firstDay + " To " + lastDay;
    }

    public ArrayList<Slot> getSlotByWeekStu(String week, String year, int studentId) {
        ArrayList<Slot> listSlot = new ArrayList<>();
        String[] weekDay = week.split(" ");
        Date fisrtDay = dateConverter(weekDay[0], year);
        Date lastDay = dateConverter(weekDay[2], year);
        
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd/MM");
        
        try {
            String sql = "select s.slotId, s.classId, s.slotNumber, t.teacherName, su.subjectCode, s.date, r.roomName from Slot s, Teacher t, Room r, Subject su, Study st\n" +
            "where date >= ? and date <= ? and s.roomId = r.roomId and s.teacherId = t.teacherId and s.subjectId = su.subjectId and s.classId = st.classId and st.studentId = ?";
            pstm = cnn.prepareStatement(sql);
            pstm.setDate(1, fisrtDay);
            pstm.setDate(2, lastDay);
            pstm.setString(3, String.valueOf(studentId));
            rs = pstm.executeQuery();
            
            while (rs.next()) {
                int id = rs.getInt(1);
                int classId = rs.getInt(2);
                int slotNum = rs.getInt(3);
                String teacherName = rs.getString(4);
                String subjectCode = rs.getString(5);
                String date = rs.getDate(6) + "";
                LocalDate localDate = LocalDate.parse(date, inputFormatter);
                date = localDate.format(outputFormatter);
                System.out.println(date);
                String roomName = rs.getString(7);
                Slot slot = new Slot(id, classId, slotNum, teacherName, roomName, subjectCode, date);
                listSlot.add(slot);
            }
        } catch (SQLException e) {
            System.out.println("checkGetSlotList: " + e.getMessage());
        }
        return listSlot;
    }
    
    public ArrayList<Slot> getSlotByWeekTeach(String week, String year, int teacherId) {
        ArrayList<Slot> listSlot = new ArrayList<>();
        String[] weekDay = week.split(" ");
        Date fisrtDay = dateConverter(weekDay[0], year);
        Date lastDay = dateConverter(weekDay[2], year);
        
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd/MM");
        
        try {
            String sql = "select s.slotId, s.classId, s.slotNumber, t.teacherName, su.subjectCode, s.date, r.roomName from Slot s, Teacher t, Room r, Subject su\n" +
"where date >= ? and date <= ? and s.roomId = r.roomId and s.teacherId = t.teacherId and s.subjectId = su.subjectId and t.teacherId = ?";
            pstm = cnn.prepareStatement(sql);
            pstm.setDate(1, fisrtDay);
            pstm.setDate(2, lastDay);
            pstm.setString(3, String.valueOf(teacherId));
            rs = pstm.executeQuery();
            
            while (rs.next()) {
                int id = rs.getInt(1);
                int classId = rs.getInt(2);
                int slotNum = rs.getInt(3);
                String teacherName = rs.getString(4);
                String subjectCode = rs.getString(5);
                String date = rs.getDate(6) + "";
                LocalDate localDate = LocalDate.parse(date, inputFormatter);
                date = localDate.format(outputFormatter);
                System.out.println(date);
                String roomName = rs.getString(7);
                Slot slot = new Slot(id, classId, slotNum, teacherName, roomName, subjectCode, date);
                listSlot.add(slot);
            }
        } catch (SQLException e) {
            System.out.println("checkGetSlotList: " + e.getMessage());
        }
        return listSlot;
    }
    
    public Date dateConverter(String weekDay, String year){
        String inputDate = weekDay + "/" + year;
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dateConvert = LocalDate.parse(inputDate, inputFormatter);
        Date date = Date.valueOf(dateConvert);
        return date;
    }
    
    public ArrayList<String> listDayOfWeek(String week, String year){
        ArrayList<String> listDayOfWeek = new ArrayList<>();
        String[] weekDay = week.split(" ");
        String fisrtDay = weekDay[0]+ "/" + year;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.parse(fisrtDay, formatter);
        
        DayOfWeek firstDayOfWeek = DayOfWeek.MONDAY;
        LocalDate startOfCurrentWeek = localDate.with(TemporalAdjusters.previousOrSame(firstDayOfWeek));
        DayOfWeek lastDayOfWeek = DayOfWeek.SUNDAY;
        LocalDate endOfCurrentWeek = localDate.with(TemporalAdjusters.nextOrSame(lastDayOfWeek));

        DateTimeFormatter dmFormatter = DateTimeFormatter.ofPattern("dd/MM");
        for (LocalDate date = startOfCurrentWeek; !date.isAfter(endOfCurrentWeek); date = date.plusDays(1)) {
            listDayOfWeek.add(date.format(dmFormatter));
        }
        return listDayOfWeek;
    }

    public ArrayList<Slot> getSlotByClass(String optTerm, String optClassroom, String studentId) {
        ArrayList<Slot> listSlot = new ArrayList<>();
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("EEE dd/MM/yyyy");
        
        try {
            String sql = "select s.slotId, s.slotNumber, su.subjectCode, r.roomName, s.date, t.teacherCode, a.isAbsent from [Slot] s, [Teacher] t, [Room] r, [Attendance] a, [Subject] su\n" +
"where s.slotId = a.slotId and s.teacherId = t.teacherId and s.roomId = r.roomId and a.studentId = ? and s.subjectId = su.subjectId and su.termId = ?";
            pstm = cnn.prepareStatement(sql);
            pstm.setString(1, studentId);
            pstm.setString(2, optTerm);
            rs = pstm.executeQuery();
            
            while (rs.next()) {
                String id = rs.getString(1);
                String slotNum = rs.getString(2);
                String subjectCode = rs.getString(3);
                String roomName = rs.getString(4);
                String date = rs.getDate(5) + "";
                LocalDate localDate = LocalDate.parse(date, inputFormatter);
                date = localDate.format(outputFormatter);
                String teacherName = rs.getString(6);
                Slot slot = new Slot(Integer.parseInt(id), Integer.parseInt(slotNum), teacherName, roomName, subjectCode, date);
                listSlot.add(slot);
            }
        } catch (SQLException e) {
            System.out.println("checkGetSlotListByClass : " + e.getMessage());
        }
        return listSlot;
    }

    public ArrayList getListSlot() {
        ArrayList<Slot> listSlot = new ArrayList<>();
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            String sql = "select * from [Slot]";
            pstm = cnn.prepareStatement(sql);
            rs = pstm.executeQuery();
            
            while (rs.next()) {
                String id = String.valueOf(rs.getInt(1));
                String slotNum = String.valueOf(rs.getInt(2));
                String date = rs.getDate(3) + "";
                LocalDate localDate = LocalDate.parse(date, inputFormatter);
                date = localDate.format(outputFormatter);
                String classID = String.valueOf(rs.getInt(4));
                String teacherID = String.valueOf(rs.getInt(5));
                String roomID = String.valueOf(rs.getInt(6));
                String subjectID = String.valueOf(rs.getInt(7));
                Slot slot = new Slot(Integer.parseInt(id), Integer.parseInt(classID), Integer.parseInt(slotNum), Integer.parseInt(roomID), Integer.parseInt(teacherID), Integer.parseInt(subjectID), date);
                listSlot.add(slot);
            }
        } catch (SQLException e) {
            System.out.println("checkGetgetListSlot : " + e.getMessage());
        }
        return listSlot;
    }

    public boolean deleteSlotById(String parameter) {
        try {
            String sql = "DELETE FROM [Slot] WHERE slotId = ?";
            pstm = cnn.prepareStatement(sql);
            pstm.setString(1, parameter);
            pstm.executeQuery();
            return true;
        } catch (Exception e) {
            System.out.println("check deleteSlotById : " + e.getMessage());
        }
        return false;
    }

    public Slot getSlotByID(String parameter) {
        Slot slot = null;
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            String sql = "Select * from [Slot] where [slotId] = ?";
            pstm = cnn.prepareStatement(sql);
            pstm.setString(1, parameter);
            rs = pstm.executeQuery();
            
            while (rs.next()) {
                String id = String.valueOf(rs.getInt(1));
                String slotNum = String.valueOf(rs.getInt(2));
                String date = rs.getDate(3) + "";
                LocalDate localDate = LocalDate.parse(date, inputFormatter);
                date = localDate.format(outputFormatter);
                String classID = String.valueOf(rs.getInt(4));
                String teacherID = String.valueOf(rs.getInt(5));
                String roomID = String.valueOf(rs.getInt(6));
                String subjectID = String.valueOf(rs.getInt(7));
                slot = new Slot(Integer.parseInt(id), Integer.parseInt(classID), Integer.parseInt(slotNum), Integer.parseInt(roomID), Integer.parseInt(teacherID), Integer.parseInt(subjectID), date);
                }
        } catch (SQLException e) {
            System.out.println("checkGetgetSlot : " + e.getMessage());
        }
        return slot;
    }

    public boolean addSlot(String slotNum, String classroom, String date, String teach, String sroom, String subject) {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(date, outputFormatter);
        Date sqlDate = Date.valueOf(localDate);
        try {
            String strSQL = "insert into [Slot] ([slotNumber], [classId], [date], [teacherId], [roomId], [subjectId]) values(?, ?, ?, ?, ?, ?)";
            pstm = cnn.prepareStatement(strSQL);
            pstm.setString(1, slotNum);
            pstm.setString(2, classroom);
            pstm.setDate(3, sqlDate);
            pstm.setString(4, teach);
            pstm.setString(5, sroom);
            pstm.setString(6, subject);
            pstm.execute();
            return true;

        } catch (SQLException e) {
            System.out.println("SQL addSlot: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("addSlot:" + e.getMessage());
        }
        return false;
    }

    public boolean updateSlotById(String slotId, String slotNum, String classroom, String date, String teach, String sroom, String subject) {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(date, outputFormatter);
        Date sqlDate = Date.valueOf(localDate);
        try {
            String strSQL = "UPDATE [Slot] SET [slotNumber] = ?, [classId] = ?, [teacherId] = ?, [roomId] = ?, [subjectId] = ?, [date] = ? \n" +
                            "WHERE [slotId] = ?";
            pstm = cnn.prepareStatement(strSQL);
            pstm.setString(1, slotNum);
            pstm.setString(2, classroom);
            pstm.setString(3, teach);
            pstm.setString(4, sroom);
            pstm.setString(5, subject);
            pstm.setDate(6, sqlDate);
            pstm.setString(7, slotId);
            pstm.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("SQL updateSlotById: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("updateSlotById:" + e.getMessage());
        }
        return false;
    }

    public ArrayList<Slot> getListSlotByClassId(String classId) {
        ArrayList<Slot> listSlot = new ArrayList<>();
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            String sql = "select * from [Slot] s, Subject su\n" +
                        "  where s.classId = ? and su.subjectId = s.subjectId";
            pstm = cnn.prepareStatement(sql);
            pstm.setString(1, classId);
            rs = pstm.executeQuery();
            
            while (rs.next()) {
                String id = String.valueOf(rs.getInt(1));
                String slotNum = String.valueOf(rs.getInt(2));
                String date = rs.getDate(3) + "";
                LocalDate localDate = LocalDate.parse(date, inputFormatter);
                date = localDate.format(outputFormatter);
                String classID = String.valueOf(rs.getInt(4));
                String teacherID = String.valueOf(rs.getInt(5));
                String roomID = String.valueOf(rs.getInt(6));
                String subjectCode = rs.getString(9);
                Slot slot = new Slot(Integer.parseInt(id), Integer.parseInt(classID), Integer.parseInt(slotNum), Integer.parseInt(roomID), Integer.parseInt(teacherID), subjectCode, date);
                listSlot.add(slot);
            }
        } catch (SQLException e) {
            System.out.println("checkGetgetListSlot : " + e.getMessage());
        }
        return listSlot;
    }

    public boolean updateSlotNumById(String slotId) {
        try {
            String strSQL = "UPDATE [Slot] SET [slotNumber] = ?\n" +
                            "WHERE [slotId] = ?";
            pstm = cnn.prepareStatement(strSQL);
            pstm.setInt(1, this.slotNum);
            pstm.setString(2, slotId);
            pstm.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("SQL updateSlotNumById: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("updateSlotNumById:" + e.getMessage());
        }
        return false;
    }
}
