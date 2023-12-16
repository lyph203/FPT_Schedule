<%-- 
    Document   : attEditAdmin
    Created on : Jul 12, 2023, 9:34:56 AM
    Author     : Pham Huong Ly
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="admin" method="POST">
            <select name="teacher">
                <c:forEach items="${listTeacher}" var="optTeacher">
                    <option value="${att.getTeacherID()}" ${optTeacher.getTeacherID() eq att.getTeacherID() ? 'selected="selected"' : ''}>${optTeacher.getCode()}</option>
                </c:forEach>
            </select><br>
            <select name="student">
                <c:forEach items="${listStudent}" var="optStu">
                    <option value="${att.getStudentID()}" ${optStu.getStudentID() eq att.getStudentID() ? 'selected="selected"' : ''}>${optStu.getFullname()}</option>
                </c:forEach>
            </select><br>
            <input type="radio" id="m" name="isAb" value="1" ${att.getisAbsent() eq "Absent" ? 'checked="checked"' : ''} checked>
            <label for="m">Absent</label>
            <input type="radio" id="f" name="isAb" value="0" ${att.getisAbsent() eq "Present" ? 'checked="checked"' : ''}>
            <label for="f">Present</label><br>
            Comment: <input type="text" name="comment" value="${att.getComment()}"><br>
            Slot: <input type="text" name="slot" value="${att.getSlotID()}"><br>
            <input type="hidden" value="${att.getTeacherID()}" name="lastTeaId">
            <input type="hidden" value="${att.getStudentID()}" name="lastStuId">
            <input type="hidden" value="${att.getSlotID()}" name="lastSlot">
            <input type="submit" value="Add" name="addAttbtn">  
            <input type="submit" value="Update" name="updateAttbtn">   
        </form>
    </body>
</html>
