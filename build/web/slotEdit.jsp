<%-- 
    Document   : slotEdit
    Created on : Jun 27, 2023, 8:15:12 AM
    Author     : Pham Huong Ly
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="admin" method="POST">
            Slot Number: <input type="text" name="slotNum" value="${slot.getSlotNum()}"><br>
            Class:<select name="class">
                <c:forEach items="${listClassroom}" var="optClass">
                    <option ${optClass.getID() eq slot.getClassID() ? 'selected="selected"' : ''} value="${optClass.getID()}">${optClass.getName()}</option>
                </c:forEach></select><br>
            Date: <input type="date" name="date"><br>
            Teacher:<select name="teach">
                <c:forEach items="${listTeacher}" var="optTea">
                    <option ${optTea.getTeacherID() eq slot.getTeacherID() ? 'selected="selected"' : ''} value="${optTea.getTeacherID()}">${optTea.getCode()}</option>        
                </c:forEach></select><br>
            Room: <select name="room">
                <c:forEach items="${listRoom}" var="optRoom">
                    <option ${optRoom.getID() eq slot.getRoomID() ? 'selected="selected"' : ''} value="${optRoom.getID()}">${optRoom.getName()}</option>
                </c:forEach></select><br>   
            Subject: <select name="subject">
                <c:forEach items="${listSubject}" var="optSub">
                    <c:forEach items="${listTerm}" var="optTerm">
                        <c:if test="${optSub.getTerm() == optTerm.getID()}">
                            <option ${optSub.getID() eq slot.getSubjectID() ? 'selected="selected"' : ''} value="${optSub.getID()}">${optTerm.getName()} : ${optSub.getName()}</option>        
                        </c:if>
                    </c:forEach>
                </c:forEach></select><br>
            <input type="hidden" value="${slot.getSlotID()}" name="slotId">
            <input type="submit" value="Add" name="addSlotbtn">  
            <input type="submit" value="Update" name="updateSlotbtn">   
        </form>
    </body>
</html>
